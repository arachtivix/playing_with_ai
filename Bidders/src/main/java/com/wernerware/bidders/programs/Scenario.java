package com.wernerware.bidders.programs;

import java.util.List;

import com.wernerware.bidders.Auction;
import com.wernerware.bidders.Item;
import com.wernerware.bidders.strategies.Bidder;

public class Scenario {
	
	private Auction auction;

	public Scenario() {
		
		Item item = new Item();
		int maxRounds = 100;
		List<Bidder> bidders = new LinkedList<Bidder>();
		
		auction = new Auction(bidders, item, maxRounds);
		
	}
	
}
