package com.wernerware.words.featurizers;

import java.util.HashMap;

import org.junit.Assert;
import org.junit.Test;

import com.wernerware.words.TrainingContext;

public class LetterComboFrequencyFeaturizerTest {

	@Test
	public void test() {
		
		HashMap<String,Integer> freqs = new HashMap<String,Integer>();
		freqs.put("abc", 100);
		freqs.put("bcd", 1);
		freqs.put("cdc", 50);
		freqs.put("cda", 2);
		
		TrainingContext tc = new TrainingContext();
		tc.setMaxLength(8);
		
		double result[] = new LetterComboFrequencyFeaturizer(freqs).featurize("abcda", tc);

		Assert.assertEquals(5, result.length);
		Assert.assertEquals(1, result[0], .001);
		Assert.assertEquals(.505, result[1], .001);
		Assert.assertEquals(.51, result[2], .001);
		Assert.assertEquals(0, result[3], .001);
		Assert.assertEquals(0, result[4], .001);
		
	}

	@Test
	public void testUnseenCombos() {
		
		HashMap<String,Integer> freqs = new HashMap<String,Integer>();
		freqs.put("abc", 100);
		freqs.put("bcd", 1);
		freqs.put("cdc", 50);
		freqs.put("cda", 2);
		
		TrainingContext tc = new TrainingContext();
		tc.setMaxLength(8);
		
		double result[] = new LetterComboFrequencyFeaturizer(freqs).featurize("xabco", tc);

		Assert.assertEquals(5, result.length);
		Assert.assertEquals(.25, result[0], .001);
		Assert.assertEquals(1, result[1], .001); 
		Assert.assertEquals(.25, result[2], .001);
		Assert.assertEquals(0, result[3], .001);
		Assert.assertEquals(0, result[4], .001);
		
	}

}
