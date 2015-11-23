/*
 * Given a list of words, return the pairs of words that when concatenated form a palindrome in linear time.

 */

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        String[] k = {"steponno", "pets", "tac","oocat", "cat"};
        Set<Duo> results= calcMethods(k);

        System.out.println("total num="+results.size());
        for (Duo result : results) {
	    System.out.println("x1="+result.str1+" x2="+result.str2);
	}
    }

    private static class Duo{
        public String str1;
        public String str2;
        public Duo(String str1, String str2) {
            this.str1 = str1;
            this.str2 = str2;
        }
    }

    public static boolean isPalindrome(String a, String b) {
	boolean bIsPalindrome = true;
	String comb = a+b;
	int len = comb.length();
	int halfLen = len/2;
	for(int i=0; i< halfLen; i++) {
	    if(comb.charAt(i)!=comb.charAt(len-1-i)){
		bIsPalindrome = false;
		break;
	    }
	}
	return bIsPalindrome;
    }

    public static Set<Duo> calcMethods(String[] k) {
        Set<Duo> results = new HashSet<Duo>();
        int arrLen = k.length;
        for(int i=0; i<arrLen; i++) {
	    for(int j=i; j<arrLen; j++) {
		if( isPalindrome(k[i], k[j])) {
		    results.add(new Duo(k[i], k[j]));
		}
	    }
        }
        return results;
    }
}