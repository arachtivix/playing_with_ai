package com.wernerware.bidders;

import java.util.LinkedList;

import org.junit.Assert;
import org.junit.Test;

import com.wernerware.bidders.strategies.Bidder;
import com.wernerware.bidders.strategies.SplitTheDifferenceBidder;

public class AuctionTest {

	@Test
	public void noWinnerUntilAuctionRunTest() {
		
		LinkedList<Bidder> bidders = new LinkedList<Bidder>();
		Item item = new Item();
		
		Bidder harold = new SplitTheDifferenceBidder("Harold");
		harold.setImputedValue(item, 100d);
		Bidder spanky = new SplitTheDifferenceBidder("Spanky");
		spanky.setImputedValue(item, 150d);
		
		bidders.add(harold);
		bidders.add(spanky);
		
		Auction auction = new Auction(bidders, item, 1000);
		
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
