package com.arithmetic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.Stack;
import java.util.TreeMap;
import java.util.Vector;

public class Solution {
	public static void main(String[] srt)
	{				
		System.out.println( 12 == 12.0 );
	}
	 public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
		 
		 int length = Integer.MAX_VALUE;
		 List<String> words = selectOneCharDiff( beginWord , wordList );
		 
		 if( words.isEmpty() ) return 0;
		 if( words.contains( endWord ) ) return 2;
		 for( int i =0 ; i < words.size(); i++ )
		 {
			 List<String> src = new ArrayList<String>();
			 Collections.copy( src, wordList );
			 int len = ladderLength( words.get( i ) , endWord ,  src );
			 if( len != 0 && len < length )
			 {
				 length = len;
			 }
		 }
	     return length == Integer.MAX_VALUE ? 0 : length+1;
	 }	
	 
	 public static List<String> selectOneCharDiff( String word , List<String> wordList )
	 {
		 List<String> words = new ArrayList<String>();
		 if( wordList != null )
		 {
			 for( int i = 0; i < wordList.size() ; i ++ )
			 {
				 if( isOneCharDiff( word , wordList.get( i ) ) )
				 {
					 words.add( wordList.get( i ) );
				 }
			 }
		 }
		 System.out.println( word + " >>>> " + words.size() );
		 for(int i=0; i<words.size(); i++)
		 {
			 System.out.print( words.get(i) + " ");
		 }
		 System.out.println("");
		 return words;
	 }
	 
	 public static boolean isOneCharDiff( String word1 , String word2 )
	 {
		 int diff = 0;
		 for( int i = 0 ; i < word1.length() ; i ++ )
		 {
			 if( word1.charAt( i ) != word2.charAt( i ) )
			 {
				 diff ++;
				 if( diff > 1 ) return false;
			 }
		 }
		 return diff == 1;
	 }
}
class ListNode {
	     int val;
	     ListNode next;
	     ListNode(int x) { val = x; }
}
