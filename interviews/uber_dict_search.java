package switchinterview;

//dict = {"h", "e", "ll", "o", "wo", "he", "alphabet", "o w"}
//input_string = "hello world"
//input_string_1 = "hello world alphabet" => {"h", "e", "he", "ll", "o", "wo", "alphabet", "o w"}
//find_dict_strings(input_string, dict) => {"h", "e", "he", "ll", "o", "wo", "o w"}

import java.util.*;

public class Solution {
 public static void main(String[] args) {
     // main method here
     HashSet<String> hset = new HashSet<String>();
     hset.add("h");
     hset.add("e");
     hset.add("ll");
     hset.add("o");
     hset.add("wo");
     hset.add("he");
     hset.add("alphabet");
     hset.add("o w");
     String input = "hello world";

     find_dict_strings(input, hset);
 }

 static void find_dict_strings( String input_string, HashSet<String> dict) {

     HashSet<String> result = new HashSet<String>();
     find_dict_string(input_string, 0, dict, result);
 }

 static void find_dict_string( String input_string, int index, HashSet<String> dict, HashSet<String> result) {
     if( index >= input_string.length() ) {
         return;
     }
     int startIndex = index;

     for(int i = index; i< input_string.length(); i++) {
         String substr = input_string.substring(startIndex, i);
         if( dict.contains(substr)) {
             if( !result.contains(substr)) {
                 System.out.println(substr);
                 result.add(substr);
             }
             find_dict_string(input_string, i, dict, result);
         }
     }
 }
}

/*
Dynamic programming to optimize
h,
e,
"llo world"

he,
"llo world"

O(N!)  vs. O(N)

n * (n-1) * (n-2) *(n-3)… * 2 *1 = O( N! )
*/