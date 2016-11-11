package com.wernerware.bidders.programs;

import java.util.LinkedList;
import java.util.List;

public class Training {

	private List<Scenario> scenarios;
	
	public Training(){
		scenarios = new LinkedList<Scenario>();
	}
	
	public List<Scenario> getScenarios() {
		return scenarios;
	}
	
}
