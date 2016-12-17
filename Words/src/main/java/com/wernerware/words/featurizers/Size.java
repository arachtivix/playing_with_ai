package com.wernerware.words.featurizers;

import com.wernerware.words.TrainingContext;

public class Size implements StringFeaturizer {

	public double[] featurize(String str, TrainingContext tc) {
		double retval[] = {str.length() / tc.getMaxLength()};
		return retval;
	}

}
