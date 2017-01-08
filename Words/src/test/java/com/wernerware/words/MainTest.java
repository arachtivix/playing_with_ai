package com.wernerware.words;

import static org.junit.Assert.*;
import junit.framework.Assert;

import org.junit.Test;

public class MainTest {
	
	@Test
	public void testRandomChars() {
		String word = Main.generateRandomCharacters(50);
		assertTrue(word.length() == 50);
		
		word = Main.generateRandomCharacters(23);
		assertTrue(word.length() == 23);
	}

}
