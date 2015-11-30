package switchinterview;

/*
10. Imagine a man reading a book.
He can perform only 2 possible actions of reading:
1) read a page in a minute (careful reading),
2) read two pages in a minute (look through).
Nothing else is permitted.
Calculate the number of all possible combinations of book reading ways with given number of pages.
Example: given 3 pages.
Answer is 3 combinations, as follows:
1st: Careful reading (1) - careful reading (1) - careful reading (1),
2nd: Careful reading (1) - look through (2),

3rd: Look through (2) - careful reading (1).

//result n=3, res = 3, n=4, res=5, n=5, res=8, O(n) dynamic programming
*/
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        int n = 4;
        int[] mem = new int[n+1];
        int num = calcMethods(n, mem);
        System.out.printf("total num="+num);
    }

    public static int calcMethods(int n, int[] mem) {
        if( 0 == mem[n]) {
            if(n == 0) {
                mem[n] = 1;
            } else if( n>=2 ) {
                mem[n] = calcMethods(n-1, mem) + calcMethods(n-2, mem);
            } else if( n>=1 ) {
                mem[n] = calcMethods(n-1, mem);
            }
        }
        return mem[n];
    }
}