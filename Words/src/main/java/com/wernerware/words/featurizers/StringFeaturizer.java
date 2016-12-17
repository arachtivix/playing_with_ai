package com.wernerware.words.featurizers;

import com.wernerware.words.TrainingContext;

public interface StringFeaturizer {
	
	public double[] featurize(String str, TrainingContext tc);

}
