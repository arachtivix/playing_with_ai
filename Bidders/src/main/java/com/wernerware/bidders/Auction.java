package com.wernerware.bidders;

import java.util.LinkedList;
import java.util.List;

import com.wernerware.bidders.strategies.Bidder;
import com.wernerware.bidders.strategies.SplitTheDifferenceBidder;

public class Auction {
	
	private List<SplitTheDifferenceBidder> bidders;
	private Bid lastBid;
	private boolean auctionDone = false;
	private int maxRounds;
	
	private List<Bid> biddingHistory;
	
	public Auction(List<SplitTheDifferenceBidder> bidders, int maxRounds){
		biddingHistory = new LinkedList<Bid>();
		this.bidders = bidders;
		this.maxRounds = maxRounds;
	}
	
	public void runAuction() {
		int numNoBidsInARow = 0;
		int round = 0;
		while( round < maxRounds ){
			System.out.println("Round " + round++);
			for( Bidder b : bidders ){
				Bid bid = b.bidOnAuction(this,biddingHistory);
				if( bid != null && (biddingHistory.size() == 0 || biddingHistory.get(biddingHistory.size() - 1).amount < bid.amount) ){
					System.out.println(bid);
					biddingHistory.add(bid);
					numNoBidsInARow = 0;
					lastBid = bid;
				} else {
					System.out.println(b + " did not bid");
					numNoBidsInARow++;
				}
				
				if( numNoBidsInARow >= bidders.size() - 1 ){
					System.out.println("Auction over: " + lastBid.bidder + " won with " + lastBid.amount);
					auctionDone = true;
					return;
				}
			}
		}
	}
	
	public Bid getWinningBid() throws Exception {
		if( !auctionDone ){
			throw new Exception("Auction not done yet!");
		} else {
			return lastBid;
		}
	}
	
	public List<Bid> getBiddingHistory(){
		return biddingHistory;
	}
	
}
