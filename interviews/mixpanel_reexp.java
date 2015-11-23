package mixpanelInterview;
/*
 * Regular expression simple with symbol *
 */

import java.util.*;

//Solution by Howard
public class Solution {
    public static void main(String[] args) {
    	//String input = "aaabb";
    	//String input = "aaabcd";
    	String input = "aaabc";
    	//String pattern = "a*b*d";
    	String pattern = "a*b*";
        	
    	boolean bIsValid = isCorrect(input, pattern, 0, 0, false);
    	
    	System.out.println("bIsValid="+bIsValid);
    }
    
    private static boolean isCorrect(String input, String pattern, int m, int n, boolean bPreviousRepeated) {
    	if( n >= pattern.length() ) {
    		return (m >= input.length());
    	}
    	if( m >= input.length() ) {
    		return( n == pattern.length()-1);
    	}
    	
    	char inputChar = input.charAt(m);
    	char patternChar = pattern.charAt(n);
    	if( inputChar == patternChar) {
    		return isCorrect(input, pattern, m+1, n+1, false);
    	} else if( patternChar == '*' ) {
    		if(m > 0) {
    			if( bPreviousRepeated ) {
	    			if( input.charAt(m-1) == inputChar ) {
	    				//if it's repeat characters
	    				return isCorrect(input, pattern, m+1, n, true);
	    			} else if( n<pattern.length()-1 && pattern.charAt(n+1) == inputChar ) {
	    				return isCorrect(input, pattern, m+1, n+2, false);
	    			}
    			} else {
	    			if( input.charAt(m-1) == inputChar ) {
		    			//if it's repeat characters
	    				return isCorrect(input, pattern, m+1, n, true);
	    			} else if( n<pattern.length()-1 && pattern.charAt(n+1) == inputChar ) {
	    				return isCorrect(input, pattern, m+1, n+2, false);
	    			} else {
	    				//treat it as a new character
	    				return isCorrect(input, pattern, m+1, n, false);
	    			}
    			}
    		} else {
    			return isCorrect(input, pattern, m+1, n, false);
    		}
    	}
    	return false;
    }
}
