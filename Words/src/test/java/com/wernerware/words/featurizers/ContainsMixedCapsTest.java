package com.wernerware.words.featurizers;

import static org.junit.Assert.*;

import org.junit.Test;

public class ContainsMixedCapsTest {

	@Test
	public void testPositiveCase() {
		
		ContainsMixedCaps featurizer = new ContainsMixedCaps();
		double result[] = featurizer.featurize("aBc", null);
		assertEquals("'aBc' should result in a positive finding for mixed caps",1.0,result[0],.001);
		
	}

	@Test
	public void testNegativeCase() {
		
		ContainsMixedCaps featurizer = new ContainsMixedCaps();
		double result[] = featurizer.featurize("abc", null);
		assertEquals("'abc' should result in a negative finding for mixed caps",0.0,result[0],.001);
		
	}

	@Test
	public void testOnlyFirstCharacterCapReturnsNegative() {
		
		ContainsMixedCaps featurizer = new ContainsMixedCaps();
		double result[] = featurizer.featurize("Abc", null);
		assertEquals("'Abc' should result in a negative finding for mixed caps because we ignore the first character",0.0,result[0],.001);
		
	}

}
