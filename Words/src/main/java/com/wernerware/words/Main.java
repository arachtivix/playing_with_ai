package com.wernerware.words;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.ml.train.MLTrain;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;

import com.wernerware.words.featurizers.Size;
import com.wernerware.words.featurizers.StringFeaturizer;
import com.wernerware.words.featurizers.VowelCount;
import com.wernerware.words.featurizers.VowelHeatmap;
import com.wernerware.words.featurizers.VowelHeatmapMetrics;

public class Main {

	public static void main(String[] args) throws IOException {
		
		TrainingContext tc = new TrainingContext();
		
		List<StringFeaturizer> featurizers = new LinkedList<StringFeaturizer>();
		featurizers.add(new VowelCount());
		featurizers.add(new Size());
		featurizers.add(new VowelHeatmap());
		featurizers.add(new VowelHeatmapMetrics());

		String dictionaryFilePath = "c:\\files\\words.txt";
		ArrayList<String> rawWords = Util.getWords(dictionaryFilePath);

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
				randChars = generateRandomLowercaseCharacters((int)(maxLength*Math.random()*Math.random()));
			} while( wordsSet.contains(randChars) );
			
			input[i*2] = encodeAndMarkup(words[i], tc, featurizers);
			input[i*2+1] = encodeAndMarkup(randChars, tc, featurizers);
			expected[i*2][0] = 1.0;
			expected[i*2+1][0] = 0.0;
		}
		
		MLDataSet trainingSet = new BasicMLDataSet(input,expected);
		BasicNetwork network = new BasicNetwork();
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
		} while(train.getError() > .01 && epoc < 70 );
		
		
		String highScorer = null;
		double highScore = 0;
		for( int i = 0; i < 30; i++ ){
			String go = generateRandomCharacters((int)(Math.random()*maxLength));
			MLData fkerfkie = new BasicMLData(encodeAndMarkup(go, tc, featurizers));
			MLData computed = network.compute(fkerfkie);
			System.out.println(go + " at " + computed.getData(0));
			if( computed.getData(0) > highScore ){
				highScore = computed.getData(0);
				highScorer = go;
			}
		}
		
		System.out.println("High scorer = " + highScorer + " at " + highScore);

		System.out.println("Score for 'cat': " + network.compute(new BasicMLData(encodeAndMarkup("cat", tc, featurizers))).getData(0));
		System.out.println("Score for 'potato': " + network.compute(new BasicMLData(encodeAndMarkup("potato", tc, featurizers))).getData(0));
		System.out.println("Score for 'stream': " + network.compute(new BasicMLData(encodeAndMarkup("stream", tc, featurizers))).getData(0));
		System.out.println("Score for 'fuck': " + network.compute(new BasicMLData(encodeAndMarkup("fuck", tc, featurizers))).getData(0));
		System.out.println("Score for 'drive': " + network.compute(new BasicMLData(encodeAndMarkup("drive", tc, featurizers))).getData(0));
		
	}
	
	public static String printFloatArray(double in[]){
		String retval = "[";
		for( int i = 0; i < in.length; i++ ){
			if( i < in.length - 1 ){
				retval += in[i] + ",";
			} else {
				retval += in[i] + "]";
			}
		}
		return retval;
	}
	
	public static String generateRandomCharacters(int length){
		String alpha = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		char retvalArr[] = new char[length];
		
		for( int i = 0; i < length; i++ ){
			retvalArr[i] = alpha.charAt((int)(Math.random()*length));
		}
		
		return new String(retvalArr);
	}
	
	public static String generateRandomLowercaseCharacters(int length){
		String alpha = "abcdefghijklmnopqrstuvwxyz";
		char retvalArr[] = new char[length];
		
		for( int i = 0; i < length; i++ ){
			retvalArr[i] = alpha.charAt((int)(Math.random()*alpha.length()));
		}
		
		return new String(retvalArr);
	}
	
	public static double[] encodeAndMarkup(String str, TrainingContext tc, List<StringFeaturizer> featurizers){
		List<Double> markups = new LinkedList<Double>();
		for( StringFeaturizer sf : featurizers ){
			for( double d : sf.featurize(str, tc) ){
				markups.add(d);
			}
		}
		
		int wordSpaceAllocated = tc.getMaxLength();
		
		int retvalSize = wordSpaceAllocated+markups.size();
		double retval[] = new double[retvalSize];
		double strEncoded[] = encode(str,wordSpaceAllocated);
		for( int i = 0; i < wordSpaceAllocated; i++ ){
			retval[i] = strEncoded[i];
		}
		for( int i = 0; i < markups.size(); i++ ){
			retval[wordSpaceAllocated+i] = markups.get(i);
		}
		
		return retval;
	}
	
	public static double[] encode(String str, int size){
		double retval[] = new double[size];
		
		for( int j = 0; j < size; j++ ){
			if( j < str.length() ){
				retval[j] = encode(str.charAt(j));
			} else {
				retval[j] = 1.0;
			}
		}
		
		return retval;
	}
	
	public static double encode(char c){
		double retval = (double)c;
		
		retval = retval - 65.0;
		
		if( retval < 0 || retval > 57 ){
			retval = 58;
		}
		
		retval = retval / 58.0;
		
		return retval;
	}
	
}
