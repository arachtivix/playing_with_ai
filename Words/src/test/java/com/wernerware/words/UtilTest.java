package com.wernerware.words;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

public class UtilTest {
	
	@Test
	public void testRandomChars() {
		String word = Util.generateRandomCharacters(50);
		assertTrue(word.length() == 50);
		
		word = Util.generateRandomCharacters(23);
		assertTrue(word.length() == 23);
	}

}
