package com.wernerware.words;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.wernerware.words.featurizers.StringFeaturizer;

public class Util {

	public static ArrayList<String> getWords(String dictionaryFilePath)
			throws FileNotFoundException, IOException {
		File file = new File(dictionaryFilePath);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		ArrayList<String> rawWords = new ArrayList<String>();
		while( br.ready() ){
			rawWords.add(br.readLine().trim());
		}
		
		br.close();
		return rawWords;
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
		
		double retval[] = new double[markups.size()];
		for( int i = 0; i < markups.size(); i++ ){
			retval[i] = markups.get(i);
		}
		
		return retval;
	}
	
}
