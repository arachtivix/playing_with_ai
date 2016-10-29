package com.wernerware.bidders.strategies;

import java.util.List;

import com.wernerware.bidders.Auction;
import com.wernerware.bidders.Bid;

public interface Bidder {

	public abstract Bid bidOnAuction(Auction a, List<Bid> history);

}