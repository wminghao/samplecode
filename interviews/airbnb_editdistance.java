package mixpanelInterview;


/*
 * return the edit distance between 2 words
 * edit distance = delete, insert, replace
 */

import java.util.*;

public class Solution {
    public static void main(String[] args) {
	//The following min distance is 3
	String a = "saturday";
	String b = "sunday"; 
	
	int[][] mem = new int [a.length()+1][b.length()+1];
	int dist = editDistance(a, b, 0, 0, mem);
	
	System.out.println("Dist="+dist);
    }
    public static int min(int a, int b){
	return a<b?a:b;
    }
    public static int min(int a, int b, int c){
	return min(min(a,b), c);
    }
    
    public static int editDistance(String a, String b, int m, int n, int [][] mem) {
	if( m == a.length() ) {
	    return b.length()-n;
	}
	if( n == b.length() ) {
	    return a.length()-m;
	} 
	
	if(a.charAt(m) == b.charAt(n)) {
	    if( mem[m+1][n+1] == 0 ) {
		mem[m+1][n+1] = editDistance(a,b,m+1,n+1, mem);
	    }
	    return mem[m+1][n+1];
	} else {
	    if( mem[m+1][n+1] == 0 ) {
		mem[m+1][n+1] = editDistance(a,b,m+1,n+1, mem);
	    }
	    if( mem[m+1][n] == 0 ) {
		mem[m+1][n] = editDistance(a,b,m+1,n, mem);
	    }
	    if( mem[m][n+1] == 0 ) {
		mem[m][n+1] = editDistance(a,b,m,n+1, mem);
	    }
	    return 1+min( mem[m][n+1], //insert a's character = delete b's character
			  mem[m+1][n], //delete a's character = insert b's character
			  mem[m+1][n+1]); //replace with each other
	}
    }
}



/*
 * return the edit distance between 2 words
 * edit distance = delete, insert, replace
 */

import java.util.*;

public class Solution {
    public static void main(String[] args) {
	//The following min distance is 3
	String a = "saturday";
	String b = "sunday"; 
	
	int dist = editDistance(a, b, 0, 0);
	
	System.out.println("Dist="+dist);
    }
    public static int min(int a, int b){
	return a<b?a:b;
    }
    public static int min(int a, int b, int c){
	return min(min(a,b), c);
    }
    
    public static int editDistance(String a, String b, int m, int n) {
	if( m == a.length() ) {
	    return b.length()-n;
	}
	if( n == b.length() ) {
	    return a.length()-m;
	} 
	
	if(a.charAt(m) == b.charAt(n)) {
	    return editDistance(a,b,m+1,n+1);
	} else {
	    return 1+min( editDistance(a, b, m, n+1), //insert a's character = delete b's character
			  editDistance(a, b, m+1, n), //delete a's character = insert b's character
			  editDistance(a, b, m+1, n+1)); //replace with each other
	}
    }
}