package com.wernerware.words.featurizers;

import java.io.Serializable;

import com.wernerware.words.TrainingContext;

public abstract class StringFeaturizer implements Serializable {
	
	public abstract double[] featurize(String str, TrainingContext tc);

}
