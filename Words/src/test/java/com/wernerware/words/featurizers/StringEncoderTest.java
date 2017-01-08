package com.wernerware.words.featurizers;

import org.junit.Assert;
import org.junit.Test;

import com.wernerware.words.TrainingContext;

public class StringEncoderTest {

	private double a = .5 + ((double)'a' - 65.0)/116;
	private double b = .5 + ((double)'b' - 65.0)/116;
	private double c = .5 + ((double)'c' - 65.0)/116;

	@Test
	public void testEncode() {
		
		StringEncoder encoder = new StringEncoder();
		TrainingContext tc = new TrainingContext();
		tc.setMaxLength(5);
		double result[] = encoder.featurize("abc", tc);
		
		Assert.assertEquals("Result should be of length equal to context's max length",5,result.length);
		Assert.assertEquals("result[0] -- a", a, result[0], .001);
		Assert.assertEquals("result[1] -- b", b, result[1], .001);
		Assert.assertEquals("result[2] -- c", c, result[2], .001);
		Assert.assertEquals("result[3] -- empty", 0, result[3], .001);
		Assert.assertEquals("result[4] -- empty", 0, result[4], .001);
		
	}

	@Test
	public void testEncodeUnknownCharacter() {
		
		StringEncoder encoder = new StringEncoder();
		TrainingContext tc = new TrainingContext();
		tc.setMaxLength(5);
		double result[] = encoder.featurize("a'c", tc);
		
		Assert.assertEquals("Result should be of length equal to context's max length",5,result.length);
		Assert.assertEquals("result[0] -- a", a, result[0], .001);
		Assert.assertEquals("result[1] -- b", 1.0, result[1], .001);
		Assert.assertEquals("result[2] -- c", c, result[2], .001);
		Assert.assertEquals("result[3] -- empty", 0, result[3], .001);
		Assert.assertEquals("result[4] -- empty", 0, result[4], .001);
		
	}

}
