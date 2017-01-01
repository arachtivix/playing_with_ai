package com.wernerware.words;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LetterComboFrequencyExtractor {

	public static void main(String args[]) throws FileNotFoundException, IOException{

		String dictionaryFilePath = "c:\\files\\words.txt";
		ArrayList<String> rawWords = Util.getWords(dictionaryFilePath);

		HashMap<String,Integer> results = new LetterComboFrequencyExtractor().extract(rawWords);
		
		System.out.println("Number of combinations: " + results.size());
		
		String mostCommonCombo = null;
		Integer mostCommonComboFrequency = 0;
		
		for( String str : results.keySet() ){
			Integer freq = results.get(str);
			if( freq != null && freq > mostCommonComboFrequency ){
				mostCommonComboFrequency = freq;
				mostCommonCombo = str;
			}
		}
		
		System.out.println("Combo: " + mostCommonCombo + ":" + mostCommonComboFrequency);
		
	}
	
	public HashMap<String,Integer> extract(List<String> in){
		
		HashMap<String,Integer> retval = new HashMap<String,Integer>();
		
		for( String str : in ){
			for( int i = 0; i < str.length() - 2; i++ ){
				String combo = str.substring(i, i+3);
				Integer count = retval.get(combo);
				if( count == null ){
					retval.put(combo, 1);
				} else {
					Integer newVal = count + 1;
					retval.put(combo, newVal);
				}
			}
		}
		
		return retval;
		
	}
}
