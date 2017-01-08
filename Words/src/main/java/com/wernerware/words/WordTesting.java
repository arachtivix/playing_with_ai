package com.wernerware.words;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.encog.ml.data.MLData;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.neural.networks.BasicNetwork;

import com.wernerware.words.featurizers.StringFeaturizer;

public class WordTesting {

	public static void main(String args[]) throws IOException, ClassNotFoundException{

		File in = new File("C:\\files\\save1483901386213.obj");
		FileInputStream fis = new FileInputStream(in);
		ObjectInputStream ois = new ObjectInputStream(fis);
		HashMap<String,Object> saveStructure = (HashMap<String,Object>)ois.readObject();
		ois.close();
		
		TrainingContext tc = (TrainingContext)saveStructure.get(Training.TRAINING_CONTEXT);
		List<StringFeaturizer> featurizers = (List<StringFeaturizer>)saveStructure.get(Training.FEATURIZERS);
		BasicNetwork network = (BasicNetwork)saveStructure.get(Training.NETWORK);
		
		String highScorer = null;
		double highScore = 0;
		for( int i = 0; i < 3000; i++ ){
			String go = Util.generateRandomLowercaseCharacters((int)(Math.random()*tc.getMaxLength()));
			MLData fkerfkie = new BasicMLData(Util.encodeAndMarkup(go, tc, featurizers));
			MLData computed = network.compute(fkerfkie);
			if( computed.getData(0) > highScore ){
				highScore = computed.getData(0);
				highScorer = go;
			}
			if( computed.getData(0) > .95 ){
				System.out.println(go + " at " + computed.getData(0));
			}
		}
		
		System.out.println("High scorer = " + highScorer + " at " + highScore);

		System.out.println("Score for 'cat': " + network.compute(new BasicMLData(Util.encodeAndMarkup("cat", tc, featurizers))).getData(0));
		System.out.println("Score for 'potato': " + network.compute(new BasicMLData(Util.encodeAndMarkup("potato", tc, featurizers))).getData(0));
		System.out.println("Score for 'stream': " + network.compute(new BasicMLData(Util.encodeAndMarkup("stream", tc, featurizers))).getData(0));
		System.out.println("Score for 'sripe': " + network.compute(new BasicMLData(Util.encodeAndMarkup("barrel", tc, featurizers))).getData(0));
		System.out.println("Score for 'drive': " + network.compute(new BasicMLData(Util.encodeAndMarkup("drive", tc, featurizers))).getData(0));
		
		System.out.println("Analyzing dictionary file for the least convincing real words");
		
		String dictionaryFilePath = "c:\\files\\words.txt";
		ArrayList<String> rawWords = Util.getWords(dictionaryFilePath);
		final HashMap<String,Double> outcomes = new HashMap<String,Double>();
		
		for( String word : rawWords ){
			MLData encodedWord = new BasicMLData(Util.encodeAndMarkup(word, tc, featurizers));
			outcomes.put(word, network.compute(encodedWord).getData(0));
		}
		
		class ResultsComparator implements Comparator<String> {
			public int compare(String str1, String str2) {
				double doubleDiff = outcomes.get(str1) - outcomes.get(str2);
				int intDiff = (int)(100.0*(doubleDiff));
				if( intDiff * intDiff > 0 ){
					return intDiff;
				} else if( doubleDiff > .0000001 ) {
					return 1;
				} else if( doubleDiff < .0000001 ) {
					return -1;
				} else {
					return 0;
				}
			}
		}
		
		Collections.sort(rawWords,new ResultsComparator());
		
		for( int i = 0; i < 10; i++ ){
			String word = rawWords.get(i);
			System.out.println(word + ": " + outcomes.get(word));
		}
		
	}
	
}
