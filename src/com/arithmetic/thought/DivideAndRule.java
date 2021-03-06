<<<<<<< HEAD
package com.arithmetic.thought;

import com.arithmetic.entry.MaxSubArraySumResult;

/*
 * 分治思想
 */
public class DivideAndRule {

	/**
	 * 求最大子列和：递归
	 * 1. 最大子数组和  在 左子数组
	 * 2. 最大子数组和 在 右子数组
	 * 3. 最大子数组和 经过 数组中心节点
	 * @param data
	 * @param start
	 * @param end
	 * @return
	 */
	public static MaxSubArraySumResult getMaxSubArraySum(int[] data,int start,int end)
	{
		if(end == start) 
			return new MaxSubArraySumResult(start,end,data[start]);//如果只有一个元素，则直接返回
		
		int mid = (end + start)/2;
		//从左子数组获得结果
		MaxSubArraySumResult maxSubArraySum_left = getMaxSubArraySum(data,start,mid);
		int start_right = mid+1 > end ? end : mid+1;
		//从右子数组获得结果
		MaxSubArraySumResult maxSubArraySum_right=getMaxSubArraySum(data,start_right,end);
		//结果经过的中点
		MaxSubArraySumResult maxSubArraySum_mid=getMaxSubArraySum_mid(data,start,mid,end);
		
		if(maxSubArraySum_left.compareTo(maxSubArraySum_right)>=0
				&& maxSubArraySum_left.compareTo(maxSubArraySum_mid)>=0)
		{							//这里的比较一定要用大于等于
			return maxSubArraySum_left;
		}else if(maxSubArraySum_right.compareTo(maxSubArraySum_left)>=0
				&& maxSubArraySum_right.compareTo(maxSubArraySum_left)>=0)
		{
			return maxSubArraySum_right;
		}else{
			return maxSubArraySum_mid;
		}
	}
	
	//计算当最大子数组和 经过中点
	private static MaxSubArraySumResult getMaxSubArraySum_mid(int[] data, int start, int mid, int end) 
	{
		int max_left = Integer.MIN_VALUE;
		int currentSum_left = 0;
		int left = mid;
		for(int i=mid; i>=start; i--)
		{
			currentSum_left += data[i];
			if(currentSum_left > max_left)
			{
				max_left = currentSum_left;
				left = i;
			}
		}
		int currentSum_right = 0;
		int max_right = Integer.MIN_VALUE;
		int right = mid+1;
		for(int i=mid+1; i<=end; i++)
		{
			currentSum_right += data[i];
			if(currentSum_right > max_right)
			{
				max_right = currentSum_right;
				right = i;
			}
		}
		int maxSubArraySum = max_left + max_right;
		return new MaxSubArraySumResult(left,right,maxSubArraySum);
	}

	/**
	 * 求最大子列和：在线计算
	 * @param data
	 * @param start
	 * @param end
	 * @return
	 */
	public static MaxSubArraySumResult getMaxSubArraySumByOnlineprocess(int[] data,int start,int end)
	{
		if(data == null || data.length <1) return null;
		if(end < start ) return null;
		if(start <0 || end >= data.length) return null;
		
		int left = 0;
		int right = 0;
		int temp =0;
		int maxSubArraySum = Integer.MIN_VALUE;
		int currentSubArraySum = 0;
		for(int i=0; i<data.length ;i++)
		{
			currentSubArraySum += data[i];
			if(currentSubArraySum > maxSubArraySum)
			{					//如果当前子列和  大于 最大子列和 ，则更新左右索引及最大子列和
				maxSubArraySum = currentSubArraySum;
				right = i;
				left = temp;
			}else {				//如果当前子列和 不大于 最大子列和 ，则判断 当前子列和 是否已小于0，是则重置当前子列和及设置左索引
				 if(currentSubArraySum < 0)
				 {
					 temp = i+1;
					 currentSubArraySum = 0;
				 }			
			}			
		}
		return new MaxSubArraySumResult(left,right,maxSubArraySum);
	}
}
=======
package com.arithmetic.thought;

import com.arithmetic.entry.MaxSubArraySumResult;

/*
 * 分治思想
 */
public class DivideAndRule {

	/*
	 * 求最大子数组和问题
	 * 1. 最大子数组和  在 左子数组
	 * 2. 最大子数组和 在 右子数组
	 * 3. 最大子数组和 经过 数组中心节点
	 */
	
	/*
	 * 递归求最大子列和：
	 */
	public static MaxSubArraySumResult getMaxSubArraySum(int[] data,int start,int end)
	{
		if(end == start) 
			return new MaxSubArraySumResult(start,end,data[start]);//如果只有一个元素，则直接返回
		
		int mid = (end + start)/2;
		//从左子数组获得结果
		MaxSubArraySumResult maxSubArraySum_left = getMaxSubArraySum(data,start,mid);
		int start_right = mid+1 > end ? end : mid+1;
		//从右子数组获得结果
		MaxSubArraySumResult maxSubArraySum_right=getMaxSubArraySum(data,start_right,end);
		//结果经过的中点
		MaxSubArraySumResult maxSubArraySum_mid=getMaxSubArraySum_mid(data,start,mid,end);
		
		if(maxSubArraySum_left.compareTo(maxSubArraySum_right)>=0
				&& maxSubArraySum_left.compareTo(maxSubArraySum_mid)>=0)
		{							//这里的比较一定要用大于等于
			return maxSubArraySum_left;
		}else if(maxSubArraySum_right.compareTo(maxSubArraySum_left)>=0
				&& maxSubArraySum_right.compareTo(maxSubArraySum_left)>=0)
		{
			return maxSubArraySum_right;
		}else{
			return maxSubArraySum_mid;
		}
	}
	
	//计算当最大子数组和 经过中点
	private static MaxSubArraySumResult getMaxSubArraySum_mid(int[] data, int start, int mid, int end) 
	{
		int max_left = Integer.MIN_VALUE;
		int currentSum_left = 0;
		int left = mid;
		for(int i=mid; i>=start; i--)
		{
			currentSum_left += data[i];
			if(currentSum_left > max_left)
			{
				max_left = currentSum_left;
				left = i;
			}
		}
		int currentSum_right = 0;
		int max_right = Integer.MIN_VALUE;
		int right = mid+1;
		for(int i=mid+1; i<=end; i++)
		{
			currentSum_right += data[i];
			if(currentSum_right > max_right)
			{
				max_right = currentSum_right;
				right = i;
			}
		}
		int maxSubArraySum = max_left + max_right;
		return new MaxSubArraySumResult(left,right,maxSubArraySum);
	}

	public static MaxSubArraySumResult getMaxSubArraySumByOnlineprocess(int[] data,int start,int end)
	{
		if(data == null || data.length <1) return null;
		if(end < start ) return null;
		if(start <0 || end >= data.length) return null;
		
		int left = 0;
		int right = 0;
		int temp =0;
		int maxSubArraySum = Integer.MIN_VALUE;
		int currentSubArraySum = 0;
		for(int i=0; i<data.length ;i++)
		{
			currentSubArraySum += data[i];
			if(currentSubArraySum > maxSubArraySum)
			{					//如果当前子列和  大于 最大子列和 ，则更新左右索引及最大子列和
				maxSubArraySum = currentSubArraySum;
				right = i;
				left = temp;
			}else
			{				//如果当前子列和 不大于 最大子列和 ，则判断 当前子列和 是否已小于0，是则重置当前子列和及设置左索引
				 if(currentSubArraySum < 0)
				 {
					 temp = i+1;
					 currentSubArraySum = 0;
				 }			
			}			
		}
		return new MaxSubArraySumResult(left,right,maxSubArraySum);
	}
}
>>>>>>> 02bffa1629e700b327f64c46ccb3692216d2d4bf
