package com.wernerware.bidders.strategies;

import java.util.List;

import com.wernerware.bidders.Auction;
import com.wernerware.bidders.Bid;

public class SplitTheDifferenceBidder implements Bidder {
	
	private double valueAsserted;
	private String name;
	
	public SplitTheDifferenceBidder(double valueAsserted, String name){
		this.valueAsserted = valueAsserted;
		this.name = name;
	}
	
	/* (non-Javadoc)
	 * @see com.wernerware.bidders.strategies.Bidder#bidOnAuction(com.wernerware.bidders.Auction, java.util.List)
	 */
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
