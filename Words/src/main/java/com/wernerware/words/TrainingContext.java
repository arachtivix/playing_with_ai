package com.wernerware.words;

import java.io.Serializable;

public class TrainingContext implements Serializable {
	
	private int maxLength, numScenarios;

	public TrainingContext(){
		
	}

	public void setMaxLength(int maxLength) {
		this.maxLength = maxLength;
	}

	public void setNumScenarios(int numScenarios) {
		this.numScenarios = numScenarios;
	}

	public int getMaxLength() {
		return maxLength;
	}
	
	
	
}
