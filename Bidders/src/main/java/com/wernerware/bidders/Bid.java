package com.wernerware.bidders;

import com.wernerware.bidders.strategies.Bidder;

public class Bid {
	
	public Bid(Bidder bidder, double amount){
		this.bidder = bidder;
		this.amount = amount;
	}

	public Bidder bidder;
	
	public double amount;
	
	@Override
	public String toString(){
		return bidder.toString() + " bid at " + amount;
	}
	
}
