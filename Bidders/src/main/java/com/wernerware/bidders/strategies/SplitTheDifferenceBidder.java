package com.wernerware.bidders.strategies;

import java.util.HashMap;
import java.util.List;

import com.wernerware.bidders.Auction;
import com.wernerware.bidders.Bid;
import com.wernerware.bidders.Item;

public class SplitTheDifferenceBidder implements Bidder {
	
	private String name;
	private HashMap<Item,Double> valuations;
	
	public SplitTheDifferenceBidder(String name){
		this.name = name;
		valuations = new HashMap<Item,Double>();
	}
	
	/* (non-Javadoc)
	 * @see com.wernerware.bidders.strategies.Bidder#bidOnAuction(com.wernerware.bidders.Auction, java.util.List)
	 */
	public Bid bidOnAuction(Auction a, List<Bid> history){
		double valueAsserted = valuations.get(a.getItem());
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
		return name;
	}

	public void setImputedValue(Item item, double imputedValue) {
		valuations.put(item, imputedValue);
	}

}
