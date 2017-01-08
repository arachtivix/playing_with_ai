package com.wernerware.words;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.ml.train.MLTrain;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;

import com.wernerware.words.featurizers.ContainsMixedCaps;
import com.wernerware.words.featurizers.LetterComboFrequencyFeaturizer;
import com.wernerware.words.featurizers.Size;
import com.wernerware.words.featurizers.StringFeaturizer;
import com.wernerware.words.featurizers.VowelCount;
import com.wernerware.words.featurizers.VowelHeatmap;
import com.wernerware.words.featurizers.VowelHeatmapMetrics;

public class Training {

	public static final String TRAINING_CONTEXT = "trainingContext";
	public static final String NETWORK = "network";
	public static final String FEATURIZERS = "featurizers";

	public static void main(String[] args) throws IOException {
		
		HashMap<String,Object> saveStructure = new HashMap<String,Object>();
		
		TrainingContext tc = new TrainingContext();
		saveStructure.put(TRAINING_CONTEXT, tc);
		
		List<StringFeaturizer> featurizers = new LinkedList<StringFeaturizer>();
		saveStructure.put(FEATURIZERS, featurizers);
		featurizers.add(new VowelCount());
		featurizers.add(new Size());
		featurizers.add(new VowelHeatmap());
		featurizers.add(new VowelHeatmapMetrics());
		featurizers.add(new ContainsMixedCaps());
//		featurizers.add(new StringEncoder());

		String dictionaryFilePath = "c:\\files\\words.txt";
		ArrayList<String> rawWords = Util.getWords(dictionaryFilePath);
		
		HashMap<String,Integer> freqs = new LetterComboFrequencyExtractor().extract(rawWords);
		featurizers.add(new LetterComboFrequencyFeaturizer(freqs));

		String words[] = new String[rawWords.size()];
		Set<String> wordsSet = new HashSet<String>();
		int index = 0;
		while( rawWords.size() > 0 ){
			words[index] = rawWords.remove(0);
			wordsSet.add(words[index++]);
		}
		int maxLength = 0;
		for( String word : words ){
			maxLength = word.length() > maxLength ? word.length() : maxLength;
		}
		tc.setMaxLength(maxLength);
		int numScenarios = words.length * 2;
		tc.setNumScenarios(numScenarios);

		double input[][] = new double[numScenarios][];
		double expected[][] = new double[numScenarios][1];
		
		for( int i = 0; i < words.length; i++ ){
			String randChars;
			do {
				randChars = Util.generateRandomLowercaseCharacters((int)(maxLength*Math.random()*Math.random()));
			} while( wordsSet.contains(randChars) );
			
			input[i*2] = Util.encodeAndMarkup(words[i], tc, featurizers);
			input[i*2+1] = Util.encodeAndMarkup(randChars, tc, featurizers);
			expected[i*2][0] = 1.0;
			expected[i*2+1][0] = 0.0;
		}
		
		MLDataSet trainingSet = new BasicMLDataSet(input,expected);
		BasicNetwork network = new BasicNetwork();
		saveStructure.put(NETWORK, network);
		network.addLayer(new BasicLayer(null,true,input[0].length));
		network.addLayer(new BasicLayer(new ActivationSigmoid(),true,input[0].length));
		network.addLayer(new BasicLayer(new ActivationSigmoid(),false,1));
		network.getStructure().finalizeStructure();
		network.reset();
		MLTrain train = new ResilientPropagation(network,trainingSet);
		
		int epoc = 0;
		do{
			train.iteration();
			System.out.println("EPOC = " + epoc++ + " err = " + train.getError());
		} while(train.getError() > .01 && epoc < 100 );
		
		File saveFile = new File("C:\\files\\save" + System.currentTimeMillis() + ".obj");
		FileOutputStream fos = new FileOutputStream(saveFile);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		System.out.println("Saving file: " + saveFile.getName());
		
		oos.writeObject(saveStructure);
		oos.close();
		
		System.out.println("Done");
		
	}
	
}
