package com.wernerware.words.featurizers;

import com.wernerware.words.TrainingContext;

public class MaxStringOnlyVowelsOrConsonants extends StringFeaturizer {
	
	private static String vowels = "AEIOUYaeiouy";
	private static String consonants = "BCDFGHJKLMNPQRSTVWXZbcdfghjklmnpqrstvwxz";
	
	private int threshold;
	
	public MaxStringOnlyVowelsOrConsonants(int threshold){
		this.threshold = threshold;
	}

	@Override
	public double[] featurize(String str, TrainingContext tc) {
		
		int vowelsConsecutive = 0, mostVowelsConsecutive = 0;
		int consonantsConsecutive = 0, mostConsonantsConsecutive = 0;
		
		for( int i = 0; i < str.length(); i++ ){
			Character c = str.charAt(i);
			if( vowels.contains(c + "") ){
				vowelsConsecutive++;
				mostVowelsConsecutive = vowelsConsecutive > mostVowelsConsecutive ? vowelsConsecutive : mostVowelsConsecutive;
			} else {
				vowelsConsecutive = 0;
			}
			if( consonants.contains(c + "") ) {
				consonantsConsecutive++;
				mostConsonantsConsecutive = consonantsConsecutive > mostConsonantsConsecutive ? consonantsConsecutive : mostConsonantsConsecutive;
			} else {
				consonantsConsecutive = 0;
			}
		}
		
		double retval[] = new double[1];
		retval[0] = mostVowelsConsecutive > threshold || mostConsonantsConsecutive > threshold ? 1 : 0;
		
		return retval;
	}

}
