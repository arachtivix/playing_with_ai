package com.wernerware.words.featurizers;

import java.util.HashSet;

import com.wernerware.words.TrainingContext;

public class VowelHeatmap implements StringFeaturizer {
	
	public static HashSet<Character> vowels = new HashSet<Character>();
	
	static {
		vowels.add('a');
		vowels.add('e');
		vowels.add('i');
		vowels.add('o');
		vowels.add('u');
		vowels.add('y');
	}
	
	public double[] featurize(String str, TrainingContext tc){
		int wordSpaceAllocated = tc.getMaxLength();
		double retval[] = new double[wordSpaceAllocated];
		
		for( int i = 0; i < retval.length && i < str.length(); i++ ){
			if( vowels.contains(str.charAt(i)) ){
				retval[i] = 1.0;
			} else if( i > 0 ) {
				retval[i] = retval[i-1] / 2.0;
			}
		}
		
		for( int i = retval.length - 2; i >= 0; i-- ){
			if( retval[i] < retval[i+1] / 2.0 ){
				retval[i] = retval[i+1] / 2.0;
			}
		}
		
		for( int i = str.length(); i < wordSpaceAllocated; i++ ){
			retval[i] = 0;
		}
		
		return retval;
	}

}
