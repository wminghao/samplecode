/*
 * Return the coins combination with the minimum number of coins.
 * Time complexity O(MN), where M is the target value and N is the number of distinct coins.

 * Space complexity O(M).

 * Similiar to knapsack problem in O(M*N) time.
 */

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        int[] k = {1, 5, 10, 25, 28, 30, 89, 48, 66, 77, 2, 5, 6, 49, 51};
        int m = 100;
        Set<Integer> minRes = calcMethods(k, m, new HashSet<Integer>());

        System.out.println("total num="+minRes.size());
        for (int result : minRes) {
	    System.out.println("coin="+result);
	}
    }

    public static Set<Integer> calcMethods(int[] k, int m, Set<Integer> curSet) {
	if(m == 0) {
	    return curSet;
	} else if( m < 0) {
	    //discard
	    return null;
	}

	//with the first element
	if( k.length >= 1) {
	    int[] k1 = new int[k.length-1];
	    System.arraycopy(k, 1, k1, 0, k.length-1);
	    Set<Integer> newSet = new HashSet<Integer>();
	    newSet.addAll(curSet);
	    newSet.add(k[0]);
	    Set<Integer> resWith =calcMethods(k1, m-k[0], newSet);

	    //without the first element
	    Set<Integer> resWithout =calcMethods(k1, m, curSet);

	    if( resWith==null && resWithout!=null) {
		return resWithout;
	    } else if(resWith!=null && resWithout==null) {
		return resWith;
	    } else if( resWith==null && resWithout== null) {
		return null;
	    } else {
		if( resWith.size() < resWithout.size()) {
		    return resWith;
		} else {
		    return resWithout;
		}
	    }
	} else {
	    return null;
	}
    }
}