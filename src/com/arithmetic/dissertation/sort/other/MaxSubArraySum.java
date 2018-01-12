package com.arithmetic.dissertation.sort.other;

import java.util.Random;

public class MaxSubArraySum {
	/*
	 * 求最大子数组和问题
	 * 1. 最大子数组和  在 左子数组
	 * 2. 最大子数组和 在 右子数组
	 * 3. 最大子数组和 经过 数组中心节点
	 */
	public static void main(String[] str)
	{
		Random r = new Random();
		int[] data = new int[20];
		for(int i=0; i<20; i++)
		{
			data[i] = r.nextInt(2) - r.nextInt(20);
		}
		Result maxSubArraySum = getMaxSubArraySum(data,0,data.length-1);
//		Result maxSubArraySum = getMaxSubArraySumByOnlineprocess(data,0,data.length-1);
		for(int i=0; i<data.length; i++)
		{
			System.out.print(data[i] +"->");
		}
		System.out.println("");
		System.out.println("left:"+maxSubArraySum.getLeft() +" right:"+maxSubArraySum.getRight());
		System.out.println("maxSubArraySum:" + maxSubArraySum.getMaxSubArraySum());
	}
	
	/*
	 * 在线求最大子列和：该方式 没有 递归方式 稳定，可能出现未知情况，且边界不好控制。
	 */
	public static Result getMaxSubArraySumByOnlineprocess(int[] data,int start,int end)
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
		return new Result(left,right,maxSubArraySum);
	}
	
	/*
	 * 递归求最大子列和：
	 */
	public static Result getMaxSubArraySum(int[] data,int start,int end)
	{
		if(end == start) 
			return new Result(start,end,data[start]);//如果只有一个元素，则直接返回
		
		int mid = (end + start)/2;
		//从左子数组获得结果
		Result maxSubArraySum_left = getMaxSubArraySum(data,start,mid);
		int start_right = mid+1 > end ? end : mid+1;
		//从右子数组获得结果
		Result maxSubArraySum_right=getMaxSubArraySum(data,start_right,end);
		//结果经过的中点
		Result maxSubArraySum_mid=getMaxSubArraySum_mid(data,start,mid,end);
		
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
	private static Result getMaxSubArraySum_mid(int[] data, int start, int mid, int end) 
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
		return new Result(left,right,maxSubArraySum);
	}
	
}
class Result implements Comparable<Result>{
	private int left;	//左坐标
	private int right;//右坐标
	private int maxSubArraySum;//最大子列和
	public int getLeft() {
		return left;
	}
	public void setLeft(int left) {
		this.left = left;
	}
	public int getRight() {
		return right;
	}
	public void setRight(int right) {
		this.right = right;
	}
	
	public int getMaxSubArraySum() {
		return maxSubArraySum;
	}
	public void setMaxSubArraySum(int maxSubArraySum) {
		this.maxSubArraySum = maxSubArraySum;
	}
	public Result(int left, int right, int maxSubArraySum) {
		super();
		this.left = left;
		this.right = right;
		this.maxSubArraySum = maxSubArraySum;
	}
	@Override
	public int compareTo(Result o) {
		return this.maxSubArraySum - o.maxSubArraySum;
	}
	
}