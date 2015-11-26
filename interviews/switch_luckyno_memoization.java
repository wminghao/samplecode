package switchinterview;

/*
 *
-Winning Ticket!
-
-Your favorite uncle, Morty, is crazy about the lottery and even crazier about how he picks his “lucky” numbers.
-And even though his “never fail” strategy has yet to succeed, Uncle Morty doesn't let that get him down.
-
-Every week he searches through the Sunday newspaper to find a string of digits that might be potential lottery picks.
-But this week the newspaper has moved to a new electronic format, and instead of a comfortable pile of papers, Uncle Morty receives a text file with the stories.
-
-Help your Uncle find his lotto picks. Given a large series of number strings, return each that might be suitable for a lottery ticket pick.
-Note that a valid lottery ticket must have 7 unique numbers between 1 and 59, digits must be used in order, and every digit must be used.
-
-For example, given the following strings:
-
-[ “1”, “42". “100848", “4938532894754”, “1234567”, “472844278465445”]
-
-Your function should return:
-
-4938532894754 -> 49 38 53 28 9 47 54
-1234567 -> 1 2 3 4 5 6 7
 */


import java.util.*;

//Explanation by Howard
//Original solution by Howard, performance is 2^n, 2 branches per recurse, with a tree
//The new solution: algorithm is improved using memoization to achieve O(n) performance.
public class Solution {

    private static final int TOTAL_NUMBERS = 7;
    private static final int MIN_VAL = 1;
    private static final int MAX_VAL = 59;
    
    //create a key class and override equals and hashCode functions to do hashmap lookup
    private static class Key {
    	public String str;
    	public int remaining;
    	
    	public Key(String st, int rem) {
    		this.str = st;
    		this.remaining = rem;
    	}
    	
    	@Override
    	public int hashCode(){
    		return (this.str+"_"+this.remaining).hashCode();
    	}
    	
    	@Override
    	public boolean equals(Object o) {
    		if( o instanceof Key) {
	    		Key obj = (Key) o;
	    		return (this.str.equals(obj.str)) && (this.remaining == obj.remaining);
    		} else {
    			return false;
    		}
    	}
    }
    
    //memoization saving a map from Key(substring, remainingNumber) to value of a list of results from the key
    private static Map<Key, List<List<Integer>>> mementos= new HashMap<Key, List<List<Integer>>>();
    
    public static void main(String[] args) {
    	//String input = "4938532894754";
    	//String input = "1234567";
    	String input = "12345156"; //two possibilities, 1,2,3,4,5,15,6 or 1,2,3,4,51,5,6

    	//String input = "12345242";//found nothing
    	//String input = "5698157156";
    	
    	if(input == null ) {
			System.out.println("Wrong input! input = null");
    		return;
    	}
    	
    	boolean allDigits = true;
    	for(int i=0; i< input.length(); i++) {
    		char cur = input.charAt(i);
    		if( cur >'9' || cur <'0') {
    			System.out.println("Wrong input! Cur ="+cur);
    			allDigits = false;
    		}
    	}
    	//check for all digits
    	if( allDigits ) {
    		List<List<Integer>> results = calcResult(input, TOTAL_NUMBERS);
	    	
    		if( results != null ) {
    			
		    	//remove non-unique result
		    	ListIterator<List<Integer>> iter = results.listIterator();
		    	while(iter.hasNext()){
		    	    if(!isUnique(iter.next())){
		    	        iter.remove();
		    	    }
		    	}
		    	
		    	//print the final results
		    	System.out.println("Total # of results="+results.size());
		    	for (List<Integer> result : results) {
					System.out.println("=================");
					System.out.println("a new List starts!");
		    		for( int res: result) {
		    			System.out.println("lucky number="+res);
		    		}
		        }
    		} else {
    			System.out.println("No results found!");    			
    		}
    	} else {
			System.out.println("Wrong input!");
    	}
    }
    
    //first sort the array, then check if it's unique or not
    private static boolean isUnique(List<Integer> curResult) {
    	List<Integer> newResult = new ArrayList<Integer>();
    	newResult.addAll(curResult);
    	Collections.sort(newResult);
    	
    	boolean bIsUnique = true;
    	int prev = -1; //b/c all positive integer, not possible to be the same
    	for( int res: newResult) {
    		if( res == prev) {
    			bIsUnique = false;
    			//System.out.println("redundant number="+res);
    			break;
    		}
    		prev = res;
		}
    	return bIsUnique;
    }
    
    //calcResult is a recursive function, 2 inputs, a string and the remaining lucky numbers derived from the string
    private static List<List<Integer>> calcResult(String input, int remaining) {
    	//check memento
    	Key lk = new Key(input, remaining);
		if( mementos.containsKey(lk) ) {
			//System.out.println("!Found key subStr="+input+" remaining="+ remaining);
			return mementos.get(lk);
		}
    	
    	int strLen = input.length();
    	if( remaining == 0 ) {
    	    List<List<Integer>> results = new ArrayList<List<Integer>>();
    		if( strLen == 0 ) {
	    	    List<Integer> result = new ArrayList<Integer>();
	    	    results.add(result);
    		}
    		return results;
    	} else if(strLen > 0){

    		List<List<Integer>> resultsS = null;
    		List<List<Integer>> resultsD = null;
    		
    		//single character case
    		String singleStr = input.substring(0, 1); 
    		int valSingle = Integer.parseInt(singleStr); 
    		if( valSingle >= MIN_VAL && valSingle <= MAX_VAL ) {

    			//use memento here if it's found, don't do it again.
    			String subStr = input.substring(1);
    			Key lookupKey = new Key(subStr, remaining-1);
    			List<List<Integer>> results = mementos.get(lookupKey);
    			if( results == null) {
    				results = calcResult(subStr, remaining-1);   
    				if( mementos.get(lookupKey) == null ) {
    					System.out.println("!Inserted key subStr="+subStr+" remaining="+ (remaining-1));
    					mementos.put(lookupKey, results);
    				}
    			}

    			//add all results from previous result
				if( results != null ) {
					resultsS = new ArrayList<List<Integer>>();

			    	for (List<Integer> result : results) {
		    			List<Integer> newResult = new ArrayList<Integer>();
						newResult.add(valSingle);
		    			newResult.addAll(result);
						resultsS.add(newResult);
			    	}
				}
    		}
    		
    		//double character case
    		if( strLen > 1 ) {
        		String doubleStr = input.substring(0, 2); 
        		int valDouble = Integer.parseInt(doubleStr); 
        		if( valDouble >= MIN_VAL && valDouble <= MAX_VAL ) {
        			
        			//use memento here if it's found, don't do it again.
        			String subStr = input.substring(2);
        			Key lookupKey = new Key(subStr, remaining-1);

        			List<List<Integer>> results = mementos.get(lookupKey);
        			if( results == null) {
        				results = calcResult(subStr, remaining-1);  
        				if( mementos.get(lookupKey) == null ) {  
        					System.out.println("!Inserted key subStr="+subStr+" remaining="+ (remaining-1));
        					mementos.put(lookupKey, results);
        				}
        			} 

        			//add all results from previous result
    				if( results != null ) {
    					resultsD = new ArrayList<List<Integer>>();
    					
    			    	for (List<Integer> result : results) {
    		    			List<Integer> newResult = new ArrayList<Integer>();
    						newResult.add(valDouble);
    		    			newResult.addAll(result);
    						resultsD.add(newResult);
    			    	}
    				}
        		}
    		}	
    		
    		List<List<Integer>> results = new ArrayList<List<Integer>>();
    		if( resultsS != null) {
    			results.addAll(resultsS);
    		} 
    		if( resultsD != null ) {
    			results.addAll(resultsD);
    		}
			System.out.println("!Inserted key subStr="+input+" remaining="+ remaining);
			mementos.put(new Key(input, remaining), results);
			return results;
    	}  
    	return null;
    }
}
