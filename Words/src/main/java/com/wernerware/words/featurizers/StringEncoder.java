package com.wernerware.words.featurizers;

import com.wernerware.words.TrainingContext;

public class StringEncoder implements StringFeaturizer {

	public double[] featurize(String str, TrainingContext tc) {
		int wordSpaceAllocated = tc.getMaxLength();
		double retval[] = new double[wordSpaceAllocated];
		
		double strEncoded[] = encode(str,wordSpaceAllocated);

		for( int i = 0; i < wordSpaceAllocated; i++ ){
			retval[i] = strEncoded[i];
		}
		
		return retval;
	}
	
	public double[] encode(String str, int size){
		double retval[] = new double[size];
		
		for( int j = 0; j < size; j++ ){
			if( j < str.length() ){
				retval[j] = encode(str.charAt(j));
			} else {
				retval[j] = 0.0;
			}
		}
		
		return retval;
	}
	
	public double encode(char c){
		double retval = (double)c;
		
		retval = retval - 65.0;
		
		if( retval < 0 || retval > 57 ){
			retval = 58;
		}
		
		retval = .5 + retval / 116.0;
		
		return retval;
	}

}
