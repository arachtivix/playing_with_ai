package com.wernerware.words.featurizers;


import org.junit.Assert;
import org.junit.Test;

import com.wernerware.words.TrainingContext;

public class VowelHeatmapTest {

	@Test
	public void testAllConsonants() {
		String toTest = "sdklfdlkjdfsl";
		VowelHeatmap vhm = new VowelHeatmap();
		TrainingContext tc = new TrainingContext();
		tc.setMaxLength(13);
		double result[] = vhm.featurize(toTest,tc);
		
		for( double d : result ){
			Assert.assertEquals("Should be all zeroes for consonants",0,d,.001);
		}
	}

	@Test
	public void testAllVowels() {
		String toTest = "iaueoyouaeoy";
		VowelHeatmap vhm = new VowelHeatmap();
		TrainingContext tc = new TrainingContext();
		tc.setMaxLength(12);
		double result[] = vhm.featurize(toTest,tc);
		
		for( double d : result ){
			Assert.assertEquals("Should be all ones for vowels",1.0,d,.001);
		}
	}

	@Test
	public void testOneStartingVowel() {
		String toTest = "icccc";
		VowelHeatmap vhm = new VowelHeatmap();
		TrainingContext tc = new TrainingContext();
		tc.setMaxLength(5);
		double result[] = vhm.featurize(toTest,tc);

		Assert.assertEquals("Should be all ones for vowels",1.0,result[0],.001);
		Assert.assertEquals("Should be all ones for vowels",0.5,result[1],.001);
		Assert.assertEquals("Should be all ones for vowels",0.25,result[2],.001);
		Assert.assertEquals("Should be all ones for vowels",0.125,result[3],.001);
		Assert.assertEquals("Should be all ones for vowels",0.0625,result[4],.001);
	}

	@Test
	public void testOneEndingVowel() {
		String toTest = "cccci";
		VowelHeatmap vhm = new VowelHeatmap();
		TrainingContext tc = new TrainingContext();
		tc.setMaxLength(5);
		double result[] = vhm.featurize(toTest,tc);

		Assert.assertEquals("Should be all ones for vowels",1.0,result[4],.001);
		Assert.assertEquals("Should be all ones for vowels",0.5,result[3],.001);
		Assert.assertEquals("Should be all ones for vowels",0.25,result[2],.001);
		Assert.assertEquals("Should be all ones for vowels",0.125,result[1],.001);
		Assert.assertEquals("Should be all ones for vowels",0.0625,result[0],.001);
	}

	@Test
	public void testOneMiddleVowel() {
		String toTest = "ccicc";
		VowelHeatmap vhm = new VowelHeatmap();
		TrainingContext tc = new TrainingContext();
		tc.setMaxLength(5);
		double result[] = vhm.featurize(toTest,tc);

		Assert.assertEquals("Should be all ones for vowels",0.25,result[0],.001);
		Assert.assertEquals("Should be all ones for vowels",0.5,result[1],.001);
		Assert.assertEquals("Should be all ones for vowels",1.0,result[2],.001);
		Assert.assertEquals("Should be all ones for vowels",0.5,result[3],.001);
		Assert.assertEquals("Should be all ones for vowels",0.25,result[4],.001);
	}

	@Test
	public void testTwoSideVowels() {
		String toTest = "iccci";
		VowelHeatmap vhm = new VowelHeatmap();
		TrainingContext tc = new TrainingContext();
		tc.setMaxLength(5);
		double result[] = vhm.featurize(toTest,tc);

		Assert.assertEquals("Should be all ones for vowels",1.0,result[0],.001);
		Assert.assertEquals("Should be all ones for vowels",0.5,result[1],.001);
		Assert.assertEquals("Should be all ones for vowels",0.25,result[2],.001);
		Assert.assertEquals("Should be all ones for vowels",0.5,result[3],.001);
		Assert.assertEquals("Should be all ones for vowels",1.0,result[4],.001);
	}

	@Test
	public void testTrailingZeroesWhereWordspaceExceedsWordLength() {
		String toTest = "iccci";
		VowelHeatmap vhm = new VowelHeatmap();
		TrainingContext tc = new TrainingContext();
		tc.setMaxLength(8);
		double result[] = vhm.featurize(toTest,tc);

		Assert.assertEquals("Should be all ones for vowels",1.0,result[0],.001);
		Assert.assertEquals("Should be all ones for vowels",0.5,result[1],.001);
		Assert.assertEquals("Should be all ones for vowels",0.25,result[2],.001);
		Assert.assertEquals("Should be all ones for vowels",0.5,result[3],.001);
		Assert.assertEquals("Should be all ones for vowels",1.0,result[4],.001);
		Assert.assertEquals("Should be all ones for vowels",0.0,result[5],.001);
		Assert.assertEquals("Should be all ones for vowels",0.0,result[6],.001);
		Assert.assertEquals("Should be all ones for vowels",0.0,result[7],.001);
	}

}
