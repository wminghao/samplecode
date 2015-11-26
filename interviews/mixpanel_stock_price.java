package mixpanelinterview;

/*
 *
 *Here is a list of the daily closing prices for Berkshire Hathaway (BRK-A) stock during January 2015

[223600, 220980, 220450, 223480, 226680, 224675, 222424, 223000, 221878, 221510, 223615, 222636, 220895, 224915, 223751, 224184, 221511, 217160, 219998, 215865]

Imagine you have built a time machine and you write down these numbers at the end of the month, and then jump back in time to the beginning of the month. 
You are now going to use your time travel powers to make a huge profit on the stock market!  

Write a function that computes the maximum profit you could make trading on this series of prices. 
You can buy and sell the stock as many times as you want and there is no fee to buy or sell, but you can only own 1 share of the stock at a time (i.e. you must sell before you can buy again).

Write another function that calculates the maximum profit with the additional constraint that you can only make 4 total trades (2 buys and 2 sells) in the month.
 */

import java.util.*;

//Explanation by Howard
public class Solution {

    public static void main(String[] args) {
        int[] price = {223600, 220980, 220450, 223480, 226680, 224675, 222424, 223000, 221878, 221510, 223615, 222636, 220895, 224915, 223751, 224184, 221511, 217160, 219998, 215865};
        int [] count = new int[1];
        Transaction[] totalTransactions = findBuyAndSellMaxProfit(price, count);
      
        int maxProfit = 0;
        for(int j=0; j<count[0]; j++ ) {
            int profit = totalTransactions[j].sellPrice- totalTransactions[j].buyPrice;

            System.out.println("buy at = "+ totalTransactions[j].buyPrice);
            System.out.println("sell at = "+ totalTransactions[j].sellPrice);
            System.out.println("profit at = "+ profit);

            maxProfit += profit;
        }
        System.out.println("No limit maxProfit =" + maxProfit );
      

        int cnt = count[0];        
        //input is the previous best transactions, and starting from index 0 as left boundary, with 2 remaining combinations.
        int maxProfit2 = findBuyAndSellComb( totalTransactions, cnt, 0, 2);
        System.out.println("With limit maxProfit =" + maxProfit2 );
        

        int[][] memoization = new int[cnt][cnt];
        //solution 2 use memoization.        
        int maxProfitSolution2 = findBuyAndSellCombMemoization( totalTransactions, cnt, memoization);
        System.out.println("With limit maxProfitSolution2 =" + maxProfitSolution2 );
    }
    

    //////////////////////////////////////////////////////////////////////////////////////////////////////  
    //Howard: Extra half an hour to finish a better algorithm, we can remember the results of previous computation
    //        and achieve a much better performance. It's a mechanism called dynamic programming(Memoization)
    //        With dynamic programming, the performance will be O(n^2) the best case.
    //////////////////////////////////////////////////////////////////////////////////////////////////////
    private static int findBuyAndSellCombLeftRight( Transaction[] transactions, int leftBound, int rightBound, int[][] memoization) {
    	if( leftBound < rightBound) {
	    	if( memoization[leftBound][rightBound] != 0 ) {
                System.out.println("memoization left="+leftBound +" right =" + rightBound +
                                 " value="+memoization[leftBound][rightBound]);
	    		return memoization[leftBound][rightBound];
	    	} else {
	    		int maxProfit = 0;
	            //find the maxProfit from right to left with single transaction. O(N^2) complexity
	            for(int left = leftBound; left < rightBound; left++) {
	              for( int right = left; right < rightBound; right++ ) {
	                  int profit = transactions[right].sellPrice - transactions[left].buyPrice;
	
	                  System.out.println("maxProfit remaining 1 updated left="+left +" right =" + right +
	                                   " value="+maxProfit + " profit="+profit);
	                  if( maxProfit < profit ) {
	                      maxProfit = profit;
	                  }
	              }
	            }
	            memoization[leftBound][rightBound] = maxProfit;
	            return maxProfit;
	    	}
    	}
    	return 0;
    }

    private static int findBuyAndSellCombMemoization( Transaction[] transactions, int count, int[][] memoization) {
        int maxProfit = 0;
        for(int left = 0; left < count-1; left++) {
          for( int right = left; right < count-1; right++ ) {
              int profit1 = findBuyAndSellCombLeftRight(transactions, left, right, memoization);
              int profit2 = findBuyAndSellCombLeftRight(transactions, right+1, count-1, memoization);
              int combinedProfit = profit1 + profit2;
              if( maxProfit < combinedProfit ) {
                  maxProfit = combinedProfit;
              }
              System.out.println("maxProfit remaining 2 updated left="+left +" right =" + right +
                                 " value="+maxProfit + " profit1=" + profit1 + " profit2="+profit2);
          }
        }
        return maxProfit;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////  
    //Initial implementation is the brutal force algorithm.
    //this will find the best combination of 2 recursively. O(n^4) time complexity,
    //////////////////////////////////////////////////////////////////////////////////////////////////////  
    private static int findBuyAndSellComb( Transaction[] transactions, int count, int leftBound, int remaining) {
        if( remaining == 1 ) {
            if( leftBound < count ) {
                int maxProfit = 0;
                //find the maxProfit from right to left with single transaction. O(N^2) complexity
                for(int left = leftBound; left < count; left++) {
                  for( int right = left; right < count; right++ ) {
                      int profit = transactions[right].sellPrice - transactions[left].buyPrice;
                      /*
                      System.out.println("maxProfit remaining 1 updated left="+left +" right =" + right +
                                       " value="+maxProfit + " profit="+profit);
                       */
                      if( maxProfit < profit ) {
                          maxProfit = profit;
                      }
                  }
                }
                return maxProfit;
            }
        } else if( remaining == 2 ) {
        	if( leftBound < count-1 ) {
                //do it recursively. compare the best combination of profit 1 + the next recursion with profit 2.
                //and find the max profit here
                int maxProfit = 0;
                //iterate n^2 times, so total is O(N^2) * O(N^2) = O(N^4) complexity
                for(int left = leftBound; left < count-1; left++) {
                  for( int right = left; right < count-1; right++ ) {
                      int profit1 = transactions[right].sellPrice - transactions[left].buyPrice;
                      int profit2 = findBuyAndSellComb(transactions, count, right+1, 1);
                      int combinedProfit = profit1 + profit2;
                      if( maxProfit < combinedProfit ) {
                          maxProfit = combinedProfit;
                      }
                      /*
                      System.out.println("maxProfit remaining 2 updated left="+left +" right =" + right +
                                         " value="+maxProfit + " profit1=" + profit1 + " profit2="+profit2);
                       */
                  }
                }
                return maxProfit;
            }             
        } 
        assert false;
        return 0;      
    }
    
    static private class Transaction{
        public int buyPrice;
        public int sellPrice;
        public Transaction(int bp, int sp) {
            this.buyPrice = bp;
            this.sellPrice = sp;
        }
    }
  
    //this algorithm will find all possible best combination of buy and sell pairs without limit. 
    //O(N) time complexity
    private static Transaction[] findBuyAndSellMaxProfit(int[] price, int[] count) {
        int len = price.length;
        
        Transaction[] totalTransactions = new Transaction[len/2];
        
        int buyPrice = 0;
        int sellPrice = 0;
        int totalCount = 0;
        int i=0; 
      
        //iterate through the array
        while( i<len-1) {
            //find the minPrice from curIndex i.
            while( i < len-1 && price[i+1] <= price[i] ) {
                i++;
            }
            
            if(i==len-1) {
                break;
            }
            
            //the min value is the buy price
            buyPrice = price[i++];
          
            //find the maxPrice from curIndex i.
            while( i < len && price[i] > price[i-1] ) {
                i++;
            }
            
            sellPrice = price[i-1];
            
            totalTransactions[totalCount++] = new Transaction(buyPrice, sellPrice);
        }
        
        count[0] = totalCount;
        return totalTransactions;
    }
}