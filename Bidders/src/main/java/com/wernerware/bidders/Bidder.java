package com.wernerware.bidders;

import java.util.List;

public class Bidder {
	
	private double valueAsserted;
	private String name;
	
	public Bidder(double valueAsserted, String name){
		this.valueAsserted = valueAsserted;
		this.name = name;
	}
	
	public Bid bidOnAuction(Auction a, List<Bid> history){
		if( history.size() > 1 ){
			double last = history.get(history.size() - 1).amount;
			double bid = last + (valueAsserted - last) / 2;
			if( bid > last ){
				return new Bid(this,bid);
			} else {
				return null;
			}
		} else {
			return new Bid(this,valueAsserted / 2);
		}
	}
	
	@Override
	public String toString(){
		return name + " @iv " + valueAsserted;
	}

}
