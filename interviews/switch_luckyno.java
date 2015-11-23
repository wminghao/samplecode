package switchinterview;

/*
 *
Winning Ticket!

Your favorite uncle, Morty, is crazy about the lottery and even crazier about how he picks his “lucky” numbers. 
And even though his “never fail” strategy has yet to succeed, Uncle Morty doesn't let that get him down.

Every week he searches through the Sunday newspaper to find a string of digits that might be potential lottery picks. 
But this week the newspaper has moved to a new electronic format, and instead of a comfortable pile of papers, Uncle Morty receives a text file with the stories.

Help your Uncle find his lotto picks. Given a large series of number strings, return each that might be suitable for a lottery ticket pick. 
Note that a valid lottery ticket must have 7 unique numbers between 1 and 59, digits must be used in order, and every digit must be used.

For example, given the following strings:

[ “1”, “42". “100848", “4938532894754”, “1234567”, “472844278465445”]

Your function should return:

4938532894754 -> 49 38 53 28 9 47 54 
1234567 -> 1 2 3 4 5 6 7
 */


import java.util.*;

//Solution by Howard, performance is 2^n, 2 branches per recurse, 
//can be improved using dynamic programming to achieve O(n) performance using memoization.
public class Solution {
    public static void main(String[] args) {
    	//String input = "4938532894754";
    	//String input = "1234567";

    	String input = "12345242";
    	boolean allDigits = true;
    	for(int i=0; i< input.length(); i++) {
    		char cur = input.charAt(i);
    		if( cur >='9' || cur <='0') {
    			allDigits = false;
    		}
    	}
    	//check for all digits
    	if( allDigits ) {
	    	List<List<Integer>> results = new ArrayList<List<Integer>>();
	    	calcResult(input, 0, results, null);
	    	for (List<Integer> result : results) {
				System.out.println("=================");
				System.out.println("a new List starts!");
	    		for( int res: result) {
	    			System.out.println("lucky number="+res);
	    		}
	        }
    	} else {
			System.out.println("Wrong input!");
    	}
    }
    
    private static final int TOTAL_NUMBERS = 7;
    private static final int MIN_VAL = 1;
    private static final int MAX_VAL = 59;
    
    
    private static void calcResult(String input, int index, List<List<Integer>> results, List<Integer> curResult) {
    	int strLen = input.length();
    	if(index >= strLen) {
    		if(  curResult!= null && curResult.size() == TOTAL_NUMBERS ) {
    			results.add(curResult);	
    		}
    		return;
    	} else if( curResult!= null && curResult.size() == TOTAL_NUMBERS && index < strLen) {
    		return;
    	} else {
    		//single character case
    		String singleStr = input.substring(index, index+1); 
    		int valSingle = Integer.parseInt(singleStr); 
    		if( valSingle >= MIN_VAL && valSingle <= MAX_VAL ) {
    			List<Integer> res = new ArrayList<Integer>();
    			if( curResult == null ) {
    				curResult = new ArrayList<Integer>();
    			}
    			res.addAll(curResult);
    			if( res.size() < TOTAL_NUMBERS) {
    				res.add(valSingle);
	    			calcResult(input, index+1, results, res);
    			}
    		}
    		
    		//double character case
    		if( (index+2) <= strLen ) {
        		String doubleStr = input.substring(index, index+2); 
        		int valDouble = Integer.parseInt(doubleStr); 
        		if( valDouble >= MIN_VAL && valDouble <= MAX_VAL ) {
        			List<Integer> res = null;
        			res = new ArrayList<Integer>();
        			res.addAll(curResult);
        			if( res.size() < TOTAL_NUMBERS) {
        				res.add(valDouble);
	        			calcResult(input, index+2, results, res);
        			}
        		}
    		}	
    	}    	
    }
}
