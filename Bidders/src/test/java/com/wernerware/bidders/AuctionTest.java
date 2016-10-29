package com.wernerware.bidders;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

public class AuctionTest {

	@Test
	public void noWinnerUntilAuctionRunTest() {
		
		LinkedList<Bidder> bidders = new LinkedList<Bidder>();
		bidders.add(new Bidder(100d,"Harold"));
		bidders.add(new Bidder(150d,"Spanky"));
		
		Auction auction = new Auction(bidders, 1000);
		
		boolean winnerException = false;
		try{
			auction.getWinningBid();
		} catch( Exception e ){
			winnerException = true;
		}
		
		boolean winningBidException = false;
		try{
			auction.getWinningBid();
		} catch( Exception e ){
			winningBidException = true;
		}

		Assert.assertTrue(winnerException);
		Assert.assertTrue(winningBidException);
		
		auction.runAuction();

		winnerException = false;
		try{
			auction.getWinningBid();
		} catch( Exception e ){
			winnerException = true;
		}
		
		winningBidException = false;
		try{
			auction.getWinningBid();
		} catch( Exception e ){
			winningBidException = true;
		}
		
		Assert.assertFalse(winnerException);
		Assert.assertFalse(winningBidException);
		
	}

}
