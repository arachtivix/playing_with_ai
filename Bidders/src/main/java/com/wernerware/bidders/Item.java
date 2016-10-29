package com.wernerware.bidders;

import java.util.UUID;

public class Item {

	private UUID id;
	
	public Item(){
		id = UUID.randomUUID();
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return id.equals(obj);
	}
	
}
