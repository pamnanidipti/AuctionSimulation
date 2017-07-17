package simulation;


/**
 * Class provided for ease of test. This will not be used in the project
 * evaluation, so feel free to modify it as you like.
 */
public class Simulation {
	public static void main(String[] args) {
		int nrSellers = 100;
		int nrBidders = 100;

		Thread[] sellerThreads = new Thread[nrSellers];
		Thread[] bidderThreads = new Thread[nrBidders];
		Seller[] sellers = new Seller[nrSellers];
		Bidder[] bidders = new Bidder[nrBidders];
                // Start the sellers
		for (int i = 0; i < nrSellers; ++i) {
			sellers[i] = new Seller(AuctionServer.getInstance(), "Seller" + i, 100, 50, i);
			sellerThreads[i] = new Thread(sellers[i]);
			sellerThreads[i].start();
		}
                
		// Start the buyers
		for (int i = 0; i < nrBidders; ++i) {
			bidders[i] = new Bidder(AuctionServer.getInstance(), "Buyer" + i, 1000, 20, 150, i);
			bidderThreads[i] = new Thread(bidders[i]);
			bidderThreads[i].start();
		}
		
		// Join on the sellers
		for (int i = 0; i < nrSellers; ++i) {
			try {
				sellerThreads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// Join on the bidders
		for (int i = 0; i < nrBidders; ++i) {
			try {
				bidderThreads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// total cash spent by all buyers
		int totalBuyerMoney = 0;
		for (int i = 0; i < nrBidders; i++) {
			totalBuyerMoney = bidders[i].cashSpent() + totalBuyerMoney;
		}
                
                

		AuctionServer auctionServer = AuctionServer.getInstance();

		 System.out.println("Auction Results and Profit Status:");
		auctionServer.profit();
		System.out.println("");
		System.out.println("Test Case Results:");
		//System.out.println("Items sold = " + auctionServer.soldItemsCount());
		System.out.println("Revenue = " + auctionServer.revenue());
		System.out.println("Cash Spent by Buyers: " + totalBuyerMoney);
                System.out.println("Sum of Highest Bids: " +auctionServer.getSumHighestBids());
                System.out.println("Sold items count: "+auctionServer.soldItemsCount());
                System.out.println("Highest Bidders size: " +auctionServer.highestBiddersSize());
	

	}
}