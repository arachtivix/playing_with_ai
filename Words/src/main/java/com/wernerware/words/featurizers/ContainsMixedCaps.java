package com.wernerware.words.featurizers;

import java.util.HashSet;
import java.util.Set;

import com.wernerware.words.TrainingContext;

public class ContainsMixedCaps extends StringFeaturizer {
	
	private static String caps = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static Set<Character> capsChars = new HashSet<Character>();
	
	static {
		for( int i = 0; i < caps.length(); i++ ){
			capsChars.add(caps.charAt(i));
		}
	}

	public double[] featurize(String str, TrainingContext tc) {
		double retval[] = { 0 };
		
		for( int i = 1; i < str.length(); i++ ){
			if( capsChars.contains(str.charAt(i)) ){
				retval[0] = 1;
			}
		}
		
		return retval;
	}

}
