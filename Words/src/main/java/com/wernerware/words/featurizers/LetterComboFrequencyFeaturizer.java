package com.wernerware.words.featurizers;

import java.util.HashMap;
import java.util.List;

import com.wernerware.words.LetterComboFrequencyExtractor;
import com.wernerware.words.TrainingContext;

public class LetterComboFrequencyFeaturizer implements StringFeaturizer {
	
	HashMap<String,Integer> freqs = new HashMap<String,Integer>();
	Double mostCommonComboFrequency;
	
	public LetterComboFrequencyFeaturizer(HashMap<String,Integer> freqs){
		this.freqs = freqs;
		
		mostCommonComboFrequency = 0.0;
		
		for( String str : freqs.keySet() ){
			Integer freq = freqs.get(str);
			if( freq != null && freq > mostCommonComboFrequency ){
				mostCommonComboFrequency = freq.doubleValue();
			}
		}
	}

	public double[] featurize(String str, TrainingContext tc) {
		
		Integer retvalSize = tc.getMaxLength() - 3;
		double retval[] = new double[retvalSize];
		
		for( int i = 0; i < retvalSize; i++ ){
			if( i + 3 <= str.length() ){
				String substr = str.substring(i,i+3);
				Integer freq = freqs.get(substr);
				if( freq != null ){
					retval[i] = .5 + (freq.doubleValue() / (2 * mostCommonComboFrequency));
				} else {
					retval[i] = .25;
				}
			} else {
				retval[i] = 0.0;
			}
		}
		
		return retval;
		
	}

}
