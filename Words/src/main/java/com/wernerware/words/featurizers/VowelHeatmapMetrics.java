package com.wernerware.words.featurizers;

import com.wernerware.words.TrainingContext;

public class VowelHeatmapMetrics extends StringFeaturizer {

	public double[] featurize(String str, TrainingContext tc) {
		double heatMapResults[] = new VowelHeatmap().featurize(str, tc);
		
		double numLettersNotAdjacentToAVowel = 0;
		for( double d : heatMapResults ){
			if( d < .5 ){
				numLettersNotAdjacentToAVowel++;
			}
		}
		
		double retval[] = new double[1];
		if( str.length() > 0 ){
			retval[0] = numLettersNotAdjacentToAVowel/str.length();
		} else {
			retval[0] = 0;
		}
		return retval;
	}

}
