package com.wernerware.words;

import java.util.HashMap;
import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

public class LetterComboFrequencyExtractorTest {

	@Test
	public void test() {

		LetterComboFrequencyExtractor lcfe = new LetterComboFrequencyExtractor();
		
		LinkedList<String> in = new LinkedList<String>();
		in.add("fkerfkie");
		
		HashMap<String,Integer> out = lcfe.extract(in);
		Assert.assertEquals(new Integer(1),out.get("fke"));
		Assert.assertEquals(new Integer(1),out.get("ker"));
		Assert.assertEquals(new Integer(1),out.get("erf"));
		Assert.assertEquals(new Integer(1),out.get("rfk"));
		Assert.assertEquals(new Integer(1),out.get("fki"));
		Assert.assertEquals(new Integer(1),out.get("kie"));
		Assert.assertEquals(null,out.get("mso"));
		
	}

	@Test
	public void testMultipleInstancesOfCombo() {

		LetterComboFrequencyExtractor lcfe = new LetterComboFrequencyExtractor();
		
		LinkedList<String> in = new LinkedList<String>();
		in.add("wonton");
		in.add("tone");
		
		HashMap<String,Integer> out = lcfe.extract(in);
		Assert.assertEquals(new Integer(1),out.get("won"));
		Assert.assertEquals(new Integer(2),out.get("ton"));
		Assert.assertEquals(new Integer(1),out.get("one"));
		Assert.assertEquals(null,out.get("mso"));
		
	}

}
