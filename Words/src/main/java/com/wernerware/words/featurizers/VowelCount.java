package com.wernerware.words.featurizers;

import java.util.HashSet;

import com.wernerware.words.TrainingContext;

public class VowelCount implements StringFeaturizer {
	
	public static HashSet<Character> vowels = new HashSet<Character>();
	
	static {
		vowels.add('a');
		vowels.add('e');
		vowels.add('i');
		vowels.add('o');
		vowels.add('u');
		vowels.add('y');
	}

	public double[] featurize(String str, TrainingContext tc) {
		double numChars = str.length();
		double vowelCount = 0;
		double retval[] = new double[1];
		
		for( int i = 0; i < str.length(); i++ ){
			if( vowels.contains(str.charAt(i))){
				vowelCount++;
			}
		}
		
		if( numChars > 0 ){
			retval[0] = vowelCount / tc.getMaxLength();
		} else {
			retval[0] = 0;
		}
		
		return retval;
	}

}
