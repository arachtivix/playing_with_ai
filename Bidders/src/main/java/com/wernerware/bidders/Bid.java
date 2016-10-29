package com.wernerware.bidders;

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
