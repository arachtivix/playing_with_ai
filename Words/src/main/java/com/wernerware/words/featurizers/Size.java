package com.wernerware.words.featurizers;

public class Size implements StringFeaturizer {

	public double[] featurize(String str, int wordSpaceAllocated) {
		double retval[] = {str.length() / 250.0};
		return retval;
	}

}
