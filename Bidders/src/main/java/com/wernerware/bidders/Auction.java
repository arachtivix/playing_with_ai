package com.wernerware.bidders;

import java.util.List;

public class Auction {
	
	private List<Bidder> bidders;
	private double lastBid;
	private Bidder highestBidder;
	private boolean auctionDone = false;
	
	public Auction(List<Bidder> bidders){
		this.bidders = bidders;
	}
	
	public void runAuction(){
		auctionDone = true;
	}
	
	public Bidder getWinner() throws Exception {
		if( !auctionDone ){
			throw new Exception("Auction not done yet!");
		} else {
			return highestBidder;
		}
	}
	
	public double getWinningBid() throws Exception {
		if( !auctionDone ){
			throw new Exception("Auction not done yet!");
		} else {
			return lastBid;
		}
	}
	
}
