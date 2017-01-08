package com.wernerware.words.featurizers;

import static org.junit.Assert.*;

import org.junit.Test;

public class MaxStringOnlyVowelsOrConsonantsTest {

	@Test
	public void testVariousInputs() {

		MaxStringOnlyVowelsOrConsonants featurizer = new MaxStringOnlyVowelsOrConsonants(3);
		double pale[] = featurizer.featurize("pale", null);
		double liar[] = featurizer.featurize("liar", null);
		double tchotchke[] = featurizer.featurize("tchotchke", null);
		double queues[] = featurizer.featurize("queues", null);
		double ffffff[] = featurizer.featurize("ffffff", null);
		double aaaaaa[] = featurizer.featurize("aaaaaa", null);

		MaxStringOnlyVowelsOrConsonants featurizer2 = new MaxStringOnlyVowelsOrConsonants(4);
		double pale2[] = featurizer2.featurize("pale", null);
		double liar2[] = featurizer2.featurize("liar", null);
		double tchotchke2[] = featurizer2.featurize("tchotchke", null);
		double queues2[] = featurizer2.featurize("queues", null);
		double ffffff2[] = featurizer2.featurize("ffffff", null);
		double aaaaaa2[] = featurizer2.featurize("aaaaaa", null);

		assertEquals("",0,pale[0], .001);
		assertEquals("",0,liar[0], .001);
		assertEquals("",1,tchotchke[0], .001);
		assertEquals("",1,queues[0], .001);
		assertEquals("",1,ffffff[0], .001);
		assertEquals("",1,aaaaaa[0], .001);

		assertEquals("",0,pale2[0], .001);
		assertEquals("",0,liar2[0], .001);
		assertEquals("",0,tchotchke2[0], .001);
		assertEquals("",0,queues2[0], .001);
		assertEquals("",1,ffffff2[0], .001);
		assertEquals("",1,aaaaaa2[0], .001);
		
	}

}
