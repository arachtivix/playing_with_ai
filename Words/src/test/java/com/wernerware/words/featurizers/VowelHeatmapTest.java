package com.wernerware.words.featurizers;


import org.junit.Assert;
import org.junit.Test;

public class VowelHeatmapTest {

	@Test
	public void testAllConsonants() {
		String toTest = "sdklfdlkjdfsl";
		VowelHeatmap vhm = new VowelHeatmap();
		double result[] = vhm.featurize(toTest,13);
		
		for( double d : result ){
			Assert.assertEquals("Should be all zeroes for consonants",0,d,.001);
		}
	}

	@Test
	public void testAllVowels() {
		String toTest = "iaueoyouaeoy";
		VowelHeatmap vhm = new VowelHeatmap();
		double result[] = vhm.featurize(toTest,12);
		
		for( double d : result ){
			Assert.assertEquals("Should be all ones for vowels",1.0,d,.001);
		}
	}

	@Test
	public void testOneStartingVowel() {
		String toTest = "icccc";
		VowelHeatmap vhm = new VowelHeatmap();
		double result[] = vhm.featurize(toTest,5);

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
		double result[] = vhm.featurize(toTest,5);

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
		double result[] = vhm.featurize(toTest,5);

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
		double result[] = vhm.featurize(toTest,5);

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
		double result[] = vhm.featurize(toTest,8);

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
