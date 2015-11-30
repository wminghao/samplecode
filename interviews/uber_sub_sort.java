package switchinterview;

//find a range from m to n, where if you sort them, the whole array is sorted /
import java.util.*;

public class Solution {
    public static void main(String[] args) {
        int [] input = {1,2,4,12,7,10,11,7,12,6,7,12,16,18,19};
        Tuple result = findSubSetSize(input);
        System.out.println("Result inputlen="+ input.length+" start=" + result.stt + " end=" + result.end);
    }

    private static class Tuple {
        public int stt;
        public int end;
        public Tuple(int st, int en) {
            stt = st;
            end = en;
        }
    }

    public static Tuple findSubSetSize(int[] input) {
        int m = 0;
        int n = 0;
        int maxValue = 0;
        int maxIndex = 0; //min = input[m]
        for(int i=1; i< input.length; i++) {

            if( input[i] >= input[i-1]) {
                if( m == i-1) {
                    m = i;
                } else if( maxValue!=0 && input[i] < maxValue ) {
                    n = i;
                    System.out.println("maxValue="+maxValue + " n="+n);
                }
            } else {
                n = i;
                //moving backward to find m
                for(int j = i-1; j>=0; j-- ) {
                    if( input[j] <= input[i] ) {
                        System.out.println("m="+m+" maxValue="+maxValue+" i="+i+" j="+j);
                        m = j+1;
                        break;
                    }

                    if( input[j] > maxValue ) {
                        maxIndex = i;
                        maxValue = input[j];
                        System.out.println("1 maxValue="+maxValue+" i="+i+" j="+j);
                    }
                }
            }
        }

        //all sorted
        if( n == 0 ) {
            n = input.length-1;
        }

        return new Tuple(m, n);
    }
}