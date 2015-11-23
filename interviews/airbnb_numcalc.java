/*
The problem is quite interesting.
We have to find the integer solution of the below equation .

n<=sum of(ki*xi)<n+1

such that ki are all constants, xi are integers.

For example : 4<=1.4x1+3.4*x2+1.1*x3<5

solutions (x1,x2,x3) =(1,1,0),(3,0,0),(0,1,1),(0,0,4) ...
*/

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        int n = 4;
        double[] k = {1.4, 3.4, 1.1};
        Set<Trio> results= calcMethods(n, k);

        System.out.println("total num="+results.size());
        for (Trio result : results) {
	    System.out.println("x1="+result.x1+" x2="+result.x2+" x3="+result.x3);
        }
    }

    private static class Trio{
        public int x1;
        public int x2;
        public int x3;
        public Trio(int x1, int x2, int x3) {
            this.x1 = x1;
            this.x2 = x2;
            this.x3 = x3;
        }
    }

    public static void calcMethods(int n, double[] k, Set<Trio> results, int[] p) {
        double res = k[0]*p[0]+k[1]*p[1]+k[2]*p[2];

        int left = n;
        int right = n+1;
        if( res < right) {
            if( res >= left ) {
                boolean bFound = false;
                for (Trio result : results) {
                    if( result.x1 == p[0] &&
                        result.x2 == p[1] &&
                        result.x3 == p[2]
                        ) {
			bFound = true;
		    }
                }
                if( !bFound ) {
                    System.out.println("valArr[0]="+p[0]+" valArr[1]="+p[1]+" valArr[2]="+p[2]+"res="+res);
                    results.add( new Trio(p[0], p[1], p[2]) );
                }
            }
            calcMethods(n, k, results, new int[]{p[0]+1, p[1], p[2]});
            calcMethods(n, k, results, new int[]{p[0], p[1]+1, p[2]});
            calcMethods(n, k, results, new int[]{p[0], p[1], p[2]+1});
        }
    }

    public static Set<Trio> calcMethods(int n, double[] k) {
        Set<Trio> results = new HashSet<Trio>();
        int [] p = {0,0,0};
        calcMethods(n, k, results, p);
        return results;
    }
}