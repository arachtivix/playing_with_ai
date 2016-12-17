package com.wernerware.words.featurizers;

import java.util.List;

public interface StringFeaturizer {
	
	public double[] featurize(String str, int wordSpaceAllocated);

}
