package switchinterview;

//N Queen Problem

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        int n = 8;
        LinkedList<Position[]> results = new LinkedList<Position[]>();

        //for each column, starting at row 0
        findQueens(results, 0, n, new Position[n]); //start with row 1

        //access via new for-loop
        for(Object result : results) {
            System.out.println("Result start");
            Position[] element = (Position[]) result;
            for(int i=0; i<n; i++) {
                Position val = element[i];
                if( val!=null ) {
                    System.out.println("Position=" + val.row + "," + val.col);
                }
            }
            System.out.println("Result end");
        }
    }
    // Returns the maximum value that can be put in a knapsack of capacity W
    private static void findQueens(LinkedList<Position[]> results, int row, int n, Position[] result) {
        if( row == n ) {
            results.add(result);
        } else {
            //for each column
            for(int j = 0; j < n; j++) {
                if( !haveConflictInHashSet(row, j, n, result ) ) {
                    Position[] newResult = result.clone();
                    newResult[row] = new Position(row, j);

                    //System.out.println("added =" + row+" "+j);
                    findQueens(results, row+1, n, newResult);
                }
            }
        }
    }

    static class Position {
        public int row;
        public int col;
        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private static boolean haveConflictInHashSet(int row, int col, int n, Position[] result ) {
        //only need to take care of previous # of rows in the result.
        for(int i=0; i<row; i++) {
            Position val = result[i];
            if( haveConflict(val.row, val.col, row, col) ) {
                return true;
            }
        }
        return false;
    }

    private static boolean haveConflict(int row, int col, int nRow, int nCol ) {
        if( row == nRow || col == nCol ) {
            return true;
        } else if( Math.abs(nRow - row) == Math.abs(nCol - col) ) {
            return true;
        } else {
            return false;
        }
    }
}