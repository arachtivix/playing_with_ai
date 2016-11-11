package com.wernerware.bidders.programs;

import static org.junit.Assert.*;

import org.junit.Test;

public class TrainingTest {

	@Test
	public void test() {
		
		Training t = new Training();
		
		assertNotNull(t.getScenarios());
		
	}

}
