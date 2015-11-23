package mixpanelInterview;


/*
 * Provide a set of positive integers (an array of integers). 
 * Each integer represent number of nights user request on Airbnb.com. 
 * If you are a host, you need to design and implement an algorithm to find out the maximum number a nights you can accommodate. 
 * The constrain is that you have to reserve at least one day between each request, so that you have time to clean the room. 
 * Example: 1) Input: [1, 2, 3] ===> output: 4, because you will pick 1 and 3 
 * 2) input: [5, 1, 2, 6] ===>; output: 11, because you will pick 5 and 6 
 * 3) input: [5, 1, 2, 6, 20, 2] ===>; output: 27, because you will pick 5, 2, 20  
 */

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        int[] k = {5, 1, 2, 6, 20, 2};
        Set<Integer> maxRes = calcMethods(k, new HashSet<Integer>());
        
        System.out.println("total guests="+maxRes.size()+" total nights="+getSum(maxRes));
        for (int result : maxRes) {
	    System.out.println("nights="+result);
	}
    }
    
    public static int getSum(Set<Integer> curSet) {
	int sum = 0;
	for (int result : curSet) {
	    sum += result;
	}
	return sum;
    }
    
    public static Set<Integer> calcMethods(int[] k, Set<Integer> curSet) {
	int kLen = k.length;
	if( kLen == 0 ) {
	    return curSet;
	} else if(kLen>2) {
	    //compare withNode and withoutNode, find the maximum
	    int[] k1 = new int[kLen-2];
	    System.arraycopy(k, 2, k1, 0, kLen-2);
	    Set<Integer> newSet = new HashSet<Integer>();
	    newSet.addAll(curSet);
	    newSet.add(k[0]);
	    Set<Integer> withNode = calcMethods(k1, newSet);
	    
	    int[] k2 = new int[kLen-1];
	    System.arraycopy(k, 1, k2, 0, kLen-1);
	    Set<Integer> withoutNode = calcMethods(k2, curSet);
	    
	    int sumWith = getSum(withNode);
	    int sumWithout = getSum(withoutNode);
	    
	    return (sumWith<sumWithout)?withoutNode:withNode;
	    
	} else if(kLen == 1){
	    curSet.add(k[0]);
	    return curSet;
	} else if(kLen == 2){
	    Set<Integer> withNode1 = new HashSet<Integer>();
	    withNode1.addAll(curSet);
	    withNode1.add(k[0]);
	    
	    curSet.add(k[1]);

	    int sumWithNode1 = getSum(withNode1);
	    int sumWithNode2 = getSum(curSet);
	    return sumWithNode1<sumWithNode2?curSet:withNode1;
	} else {
	    return null;
	}
    }
}
