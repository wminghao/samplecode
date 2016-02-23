import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 *
 * Optimization: Dynamic programming, reuse the previous result
 * i.e., 17->10->1 (reuse 10)
 */

class Solution {
  
    public static void main(String[] args) {  
	int n = 100;
	for(int i=1; i<=n; i++) {  
	    if(isHappyNo(i)) {
		System.out.println(i+" is a happy number!");
	    }
	}
    }
  
    private static boolean isHappyNo( int n ) {
    
	boolean bIsHappyNo = false;
    
	int startNo = n;
	HashSet<Integer> results = new HashSet<Integer>();  
	while( true ) {
	    //find all digits from the number
	    int sum = 0;
	    while( startNo!= 0 ) {
		int divideBy10 = startNo/10;
		int digit = (startNo - divideBy10*10);
		sum += digit*digit;
		startNo = divideBy10;          
	    }
      
	    //System.out.println("sum ="+sum);
	    if(sum == 1) {
		bIsHappyNo = true;
		break;
	    } else if(results.contains(sum)) {
		break;
	    } else {
		results.add(sum);
	    }
	    startNo = sum;
	}
	return bIsHappyNo;
    }
}

/* 
Your previous Plain Text content is preserved below:

This is just a simple shared plaintext pad, with no execution capabilities.

When you know what language you'd like to use for your interview,
simply choose it from the dropdown in the top bar.

You can also change the default language your pads are created with
in your account settings: https://coderpad.io/profile

Enjoy your interview!


Happy numbers
Find all of the happy numbers from 1 to n. i.e., n=100
Take the sum of the squares of all digits of a given number
Continue until either the sum equals 1, or the sum equals a number that has been seen previously (loop)

Is 13 happy?
1^2 + 3^2 = 1 + 9 = 10
1^2 + 0^2 = 1
->Happy

Is 11 happy?
1^2 + 1^2 = 1 + 1 = 2
2^2 = 4
4^2 = 16
1^2 + 6^2 = 1 + 36 = 37
3^2 + 7^2 = 9 + 49 = 58
5^2 + 8^2 = 25 + 64 = 89
8^2 + 9^2 = 64 + 81 = 145
1^2 + 4^2 + 5^2 = 1 + 16 + 25 = 42
4^2 + 2^2 = 16 + 4 = 20
2^2 = 4
->Unhappy
*/
