package mixpanelInterview;
/*
 * 
 * Sudoku solution validator

A sudoku puzzle contains 81 cells arranged in a 9x9 grid. The game has 3 basic
"units": rows, columns and boxes. Boxes are the 3x3 "minigrids". A valid
solution consists of a grid in which all units contain the digits 1-9. Here's a
picture of one:

Please write the following function:

is_valid_solution(string grid) => boolean

The input, grid, is a string representing a grid starting in the top-left
corner and moving row-by-row down to the bottom-right. For instance, the grid
pictured above would be:

grid = "385416927296857431417293658569134782123678549748529163652781394981345276374962815"

The output is a boolean representing whether it is a valid solution.
 */

import java.util.*;

//Solution by Howard
public class Solution {
    public static void main(String[] args) {
    	//The following grid
    	//good case
    	//String grid = "835416927296857431417293658569134782123678549748529163652781394981345276374962815";

    	//bad column
    	//String grid = "853416927296857431417293658569134782123678549748529163652781394981345276374962815";

    	//bad row
    	String grid = "235416927896857431417293658569134782123678549748529163652781394981345276374962815";

    	
    	//bad case, with a wrong digit
    	//String grid = "835316927296857431417293658569134782123678549748529163652781394981345276374962815";
    	
    	//null input
    	//String grid = null;

    	//wrong # of input
    	//String grid = "hello";
    	
    	//not a valid digit
    	//String grid = "8354169a7296857431417293658569134782123678549748529163652781394981345276374962815";
    	
    	boolean bIsValid = is_valid_solution(grid);
    	
    	System.out.println("bIsValid="+bIsValid);
    }
    
    private static boolean isValidBlock(int[] block) {
    	boolean bIsValid = true;
    	Arrays.sort(block);
    	int len = block.length;
    	for(int i=0; i < len; i++) {
    		if(block[i] != (i+1)) {
        		System.out.println("Invalid block! block["+i+"]="+block[i]);
    			bIsValid = false;
    			break;
    		}
    	}
    	return bIsValid;
    }
    
    public static boolean is_valid_solution(String grid) {
    	//cannot be null
    	if( grid == null ) {
    		System.out.println("null pointer");
    		return false;
    	}
    	//must be 81
    	int totalGridLen = grid.length();
    	if( totalGridLen!= 81 ) {
    		System.out.println("not a valid block, size not 81");
    		return false;
    	}
    	
    	int gridWidth = (int) Math.sqrt((double)totalGridLen);
    	assert gridWidth*gridWidth == totalGridLen;
    	
    	int rowStartIndex = 0;
    	int columnStartIndex = 0;
    	int columnStartPrevIndex = 0;
    	
    	int[] sudokoBlock = new int[9];

    	boolean isValid = true;
    	int totalCount = 0;
    	
    	//traverse the whole string
    	while(totalCount<totalGridLen) {
    		int count = 0;
    		int i = 0; 
    		int j = 0;
    		boolean isValidDigits = true;
    		System.out.println("new block="+rowStartIndex+" columnStartIndex="+columnStartIndex);
    		while(count < 9 && isValidDigits) {
    			char curChar = grid.charAt((rowStartIndex+i)*gridWidth + columnStartIndex + j);
    			//check for invalid characters.
    			if( curChar <'0' || curChar > '9') {
    				isValidDigits = false;
            		System.out.println("invalid digits="+curChar);
    				break;
    			}
    			//save the number
    			sudokoBlock[count++] = curChar - '0';
    			j++;
    			if( j==3 ){
    				j = 0;
    				i++;
    			}
        		System.out.println("sudokoBlock["+count+"]="+sudokoBlock[count-1]);
    		}
    		if( !isValidDigits || !isValidBlock(sudokoBlock)) {
    			isValid = false;
    			break;
    		}
    		
    		//update the starting row and columns
    		rowStartIndex = columnStartPrevIndex;
    		columnStartIndex += 3;
    		if(columnStartIndex == gridWidth) {
    			columnStartIndex = 0;
    			rowStartIndex += 3;
        		columnStartPrevIndex = rowStartIndex;	
    		}  
    		System.out.println("next block rowStartIndex="+rowStartIndex+" columnStartIndex="+columnStartIndex);
    		
    		//update the counter
    		totalCount+=count;
    	}
    	
    	////////////////////////////////////////
    	//New code begins
    	////////////////////////////////////////
    	/////////////////////////////////////////////////////////////////
    	//Sorry I never played Sudoko in my life, and now I understand it right
    	//missing code from this morning
    	////////////////////////////////////////////////////////////
    	if( isValid ) {
        	//detect per row correctness
        	for(int i=0; i<gridWidth; i++) {
        		int count = 0;
        		for(int j=0; j<gridWidth; j++) {
        			char curChar = grid.charAt(i*gridWidth + j);
        			//check for invalid characters.
        			if( curChar <'0' || curChar > '9') {
                		System.out.println("row wise invalid digits="+curChar);
                		isValid = false;
        				break;        				
        			}
        			sudokoBlock[count++] = curChar - '0';
        		}

        		System.out.println("detecting rows:"+i);
        		if( !isValid || !isValidBlock(sudokoBlock)) {
        			isValid = false;
        			break;
        		}
        	}  
        	if( isValid ) {
	    		System.out.println("detecting columns");
	    		//detect per column correctness
	        	for(int i=0; i<gridWidth; i++) {
	        		int count = 0;
	        		for(int j=0; j<gridWidth; j++) {
	        			char curChar = grid.charAt(j*gridWidth + i);
	        			//check for invalid characters.
	        			if( curChar <'0' || curChar > '9') {
	                		System.out.println("column wise invalid digits="+curChar);
	                		isValid = false;
	        				break;        				
	        			}
	        			sudokoBlock[count++] = curChar - '0';
	        		}
	
	        		System.out.println("detecting columns:"+i);
	        		if( !isValid || !isValidBlock(sudokoBlock)) {
	        			isValid = false;
	        			break;
	        		}
	        	}    
        	}
    	}
    	////////////////////////////////////////
    	//New code ends
    	////////////////////////////////////////
    	
    	return isValid;
    }
}
