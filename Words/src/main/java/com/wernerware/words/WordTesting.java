package com.wernerware.words;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.List;

import org.encog.ml.data.MLData;
import org.encog.ml.data.basic.BasicMLData;
import org.encog.neural.networks.BasicNetwork;

import com.wernerware.words.featurizers.StringFeaturizer;

public class WordTesting {

	public static void main(String args[]) throws IOException, ClassNotFoundException{

		File in = new File("C:\\files\\save1483895523311.obj");
		FileInputStream fis = new FileInputStream(in);
		ObjectInputStream ois = new ObjectInputStream(fis);
		HashMap<String,Object> saveStructure = (HashMap<String,Object>)ois.readObject();
		ois.close();
		
		TrainingContext tc = (TrainingContext)saveStructure.get(Training.TRAINING_CONTEXT);
		List<StringFeaturizer> featurizers = (List<StringFeaturizer>)saveStructure.get(Training.FEATURIZERS);
		BasicNetwork network = (BasicNetwork)saveStructure.get(Training.NETWORK);
		
		String highScorer = null;
		double highScore = 0;
		for( int i = 0; i < 300; i++ ){
			String go = Util.generateRandomLowercaseCharacters((int)(Math.random()*tc.getMaxLength()));
			MLData fkerfkie = new BasicMLData(Util.encodeAndMarkup(go, tc, featurizers));
			MLData computed = network.compute(fkerfkie);
			if( computed.getData(0) > highScore ){
				highScore = computed.getData(0);
				highScorer = go;
			}
		}
		
		System.out.println("High scorer = " + highScorer + " at " + highScore);

		System.out.println("Score for 'cat': " + network.compute(new BasicMLData(Util.encodeAndMarkup("cat", tc, featurizers))).getData(0));
		System.out.println("Score for 'potato': " + network.compute(new BasicMLData(Util.encodeAndMarkup("potato", tc, featurizers))).getData(0));
		System.out.println("Score for 'stream': " + network.compute(new BasicMLData(Util.encodeAndMarkup("stream", tc, featurizers))).getData(0));
		System.out.println("Score for 'sripe': " + network.compute(new BasicMLData(Util.encodeAndMarkup("barrel", tc, featurizers))).getData(0));
		System.out.println("Score for 'drive': " + network.compute(new BasicMLData(Util.encodeAndMarkup("drive", tc, featurizers))).getData(0));
		
	}
	
}
