package com.company;

import java.util.*;

public class Main {

    /*
    //Permutation
    public static void Perm(String pre, char[] cur, int index, int n) {
        if(index == n) {
            System.out.println(pre);
            return;
        }
        int len = cur.length;
        for(int i=0; i<len; i++) {
            char[] newCur = new char[len-1];
            for(int j=0, k=0; j<cur.length;j++) {
                if(j!=i) {
                    newCur[k++]=cur[j];
                }
            }
            Perm(pre+cur[i], newCur, index+1, n);
        }
    }

    //combination
    public static void Comb(String pre, char[] orig, int index, int k, int n) {
        //base case
        if(pre.length() == k) {
            System.out.println(pre);
            return;
        }

        //recursive case
        for(int i = index; i<n; i++) {
            String preNew = new String(pre);
            preNew += orig[i];
            Comb(preNew, orig, i+1, k, n);
        }
    }
    */

  public static void PermRedundant(String pre, char[] cur, int index, int n) {
    if(index == n) {
      System.out.println(pre);
      return;
    }
    int len = cur.length;
    for(int i=0; i<len;) {

      //find redundant
      int countRedundant = 0;
      char curChar = cur[i];
      for(int ii = i; ii<len; ii++) {
        if(cur[ii] == curChar) {
          countRedundant++;
        } else {
          break;
        }
      }

      //skip the cur characters
      char[] newCur = new char[len-1];
      for(int j=0, k=0; j<cur.length;j++) {
        if(j!=i) {
          newCur[k++]=cur[j];
        }
      }

      //run recursively
      PermRedundant(pre+curChar, newCur, index+1, n);

      //increment i by countRedundant
      i += countRedundant;
    }
  }

    public static void main(String[] args) {
	      // write your code here
        /*
        String input = "abcd";
        int n = input.length();
        */
        //Perm("", input.toCharArray(), 0, n);
        /*for(int k=0; k<=n; k++ ) {
          Comb("", input.toCharArray(), 0, k, n);
        }*/

        String input = "abca";
        int n = input.length();
        char[] sorted = input.toCharArray();
        Arrays.sort(sorted);
        System.out.println("sorted="+new String(sorted));

        PermRedundant("", sorted, 0, n);
    }
}
