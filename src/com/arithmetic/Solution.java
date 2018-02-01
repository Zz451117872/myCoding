package com.arithmetic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class Solution {
	public static void main(String[] srt)
	{
			String s = "abbaaaaaa";
	}
	
	 public int removeElement(int[] nums, int val) {
		 if(nums == null || nums.length < 1) return 0;
		 int k = nums.length-1;
		 int start = 0;
		 int len = nums.length;
		 while(start < len && start < k)
		 {
			 if(nums[start] == val)
			 {
				 nums[start] = nums[k];
				 nums[k] = val;
				 k --;
			 }
			 start ++;
		 }
		 
	     return k+1;   
	}

		
}
