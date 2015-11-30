package switchinterview;

//find the best combination of buy and sell problem in n days.
//from the current list find the minimum, & update the maxDiff
//keep 2 buyerIndex, sellerIndex, keep minIndex, keep maxDiff
import java.util.*;

public class Solution {
  public static void main(String[] args) {
      int[] stockPrice = {40,20,30,42,20,50,10,25,50};
      maxProfit(stockPrice);
  }
  public static void maxProfit(int[] stock)
  {
      int minIdx = 0;
      int buyIdx = 0;
      int sellIdx = 0;
      int maxDiff = 0;
      int j = 0;
      while(j < stock.length)
      {
          if(stock[j] - stock[minIdx] > maxDiff) {
              maxDiff = stock[j] - stock[minIdx];
              sellIdx = j;
              buyIdx = minIdx;
          } else if(stock[j] < stock[minIdx]) {
              minIdx = j;
          }
          j++;
      }
      System.out.println("buy "+stock[buyIdx]+" sell "+stock[sellIdx]);
  }
}