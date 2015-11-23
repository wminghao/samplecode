/*
 Given an array of arrays, implement an iterator class to allow the client to
 traverse and remove elements in the array list.  This iterator should provide
 three public class member functions:
 boolean hasNext()
    return true or false if there is another element in the set
 int next()
    return the value of the next element in the array
 void remove()
    remove the last element returned by the iterator.
    That is, remove the element that the previous next() returned

 The code should be well structured, and robust enough to handle any access
 pattern.  Additionally, write code to demonstrate that the class can be used for
 the following basic scenarios:
 Print elements
   Given:  [[],[1,2,3],[4,5],[],[],[6],[7,8],[],[9],[10],[]]
   Print:  1 2 3 4 5 6 7 8 9 10
 Remove multiplies of 3
   Given:  [[],[1,2,3],[4,5],[],[],[6],[7,8],[],[9],[10],[]]
   Should result in:  [[],[1,2],[4,5],[],[],[],[7,8],[],[],[10],[]]
*/

import java.io.*;
import java.util.*;

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */

class Solution {
    public static class ArrayListIterator {

	public ArrayListIterator(List<List<Integer>> arrayList) {
	    this.arrayList = arrayList;
	    totalNumOfLinkedList = arrayList.size();
	    curLinkedListIndex = -1; //initialize with -1
	    curLinkedListIndex = getToNexNonEmptyList();
	    if( curLinkedListIndex != -1 ) {
		curLinkedListPtr = arrayList.get(curLinkedListIndex);
		curListIndex = 0;
	    }
	}
	private int getToNexNonEmptyList() {
	    //find the first non-empty linedlist
	    int index = curLinkedListIndex;

	    //System.out.println("getToNexNonEmptyList starting index="+index);
	    while( (index+1) < totalNumOfLinkedList) {
		++index;
		List<Integer> ll = arrayList.get(index);
		if( ll.size() == 0 ) {
		    ll = arrayList.get(index );
		    //System.out.println("index="+index + " totalNumOfLinkedList="+totalNumOfLinkedList);
		} else {
		    break;
		}
	    }
	    return (index == curLinkedListIndex)?-1:((index+1==totalNumOfLinkedList)?-1:index);
	}

	public boolean hasNext() {
	    boolean ret = false;

	    //System.out.println("curLinkedListIndex="+curLinkedListIndex + " totalNumOfLinkedList="+totalNumOfLinkedList);
	    //System.out.println("curListIndex="+curListIndex + " curLinkedListSize="+curLinkedListPtr.size());
	    if( curListIndex < curLinkedListPtr.size() ) {
		ret = true;
	    } else {
		if( curLinkedListIndex < totalNumOfLinkedList ) {
		    int index = getToNexNonEmptyList();
		    if( index != -1 && index < totalNumOfLinkedList){
			ret = true;
		    }
		}
	    }
	    //System.out.println("hasNext ret="+ret);
	    return ret;
	}

	public int next() {
	    int ret = -1;
	    if( curListIndex < curLinkedListPtr.size() ) {
		ret = curLinkedListPtr.get(curListIndex++);
	    } else {
		if( curLinkedListIndex < totalNumOfLinkedList ) {
		    int index = getToNexNonEmptyList();
		    if( index != -1 ){
			curLinkedListPtr = arrayList.get(index);
			curLinkedListIndex = index;
			curListIndex = 0;
			ret = curLinkedListPtr.get(curListIndex++);
		    }
		}
	    }

	    //System.out.println("next ret="+ret);
	    return ret;
	}

	public void remove() {
	    //System.out.println("remove curListIndex="+curListIndex);
	    if( (curListIndex-1) < curLinkedListPtr.size() ) {
		curLinkedListPtr.remove(curListIndex-1);
		if((curListIndex-1) == curLinkedListPtr.size()) {
		    int index = getToNexNonEmptyList();
		    if( index != -1 ){
			curLinkedListPtr = arrayList.get(index);
			curLinkedListIndex = index;
			curListIndex = 0;
		    }
		}
	    }
	}

	List<List<Integer>> arrayList = null;
	int curLinkedListIndex = 0;
	int totalNumOfLinkedList = 0;

	List<Integer> curLinkedListPtr = null;
	int curListIndex = 0;
    }

    // Helper class to initialize the arraList.
    public static List<List<Integer>> listify(int[][] arrays) {
	List<List<Integer>> lists = new ArrayList<>();
	for (int[] array : arrays) {
	    List<Integer> list = new ArrayList<>();
	    for (int num : array) list.add(num);
	    lists.add(list);
	}
	return lists;
    }

    public static String listToString(List<Integer> list) {
	String buffer = "[";
	for (int i = 0; i < list.size(); ++i) {
	    if (i > 0) {
		buffer += ",";
	    }
	    buffer += list.get(i);
	}
	buffer += "]";
	return buffer;
    }

    public static String arrayListToString(List<List<Integer>> arrayList) {
	String buffer = "[";
	for (int i = 0; i < arrayList.size(); ++i) {
	    if (i > 0) {
		buffer += ",";
	    }
	    buffer += listToString(arrayList.get(i));
	}
	buffer += "]";
	return buffer;
    }

    public static void testCase1() throws Exception {
	System.out.println("---- Test case 1 ----");

	List<List<Integer>> arrayList = listify(
						new int[][]{{}, {1,2,3}, {4,5}, {}, {}, {6}, {7,8}, {}, {9}, {10}, {}});

	ArrayListIterator it = new ArrayListIterator(arrayList);
	while(it.hasNext()) {
	    System.out.println(it.next());
	}
    }

    public static void testCase2() throws Exception {
	System.out.println("---- Test case 2 ----");

	List<List<Integer>> arrayList = listify(
						new int[][]{{}, {1,2,3}, {4,5}, {}, {}, {6}, {7,8}, {}, {9}, {10}, {}});

	ArrayListIterator it = new ArrayListIterator(arrayList);
	while(it.hasNext()) {
	    Integer v = it.next();
	    if (v % 3 == 0) {
		it.remove();
	    }
	}
	System.out.println(arrayListToString(arrayList));
    }

    public static void main(String[] args) throws Exception {
	// Test cases.
	testCase1();
	testCase2();
    }
}