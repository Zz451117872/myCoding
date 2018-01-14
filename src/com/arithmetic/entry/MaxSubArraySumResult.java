package com.arithmetic.entry;


public class MaxSubArraySumResult implements Comparable<MaxSubArraySumResult>{

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
	public MaxSubArraySumResult(int left, int right, int maxSubArraySum) {
		super();
		this.left = left;
		this.right = right;
		this.maxSubArraySum = maxSubArraySum;
	}
	@Override
	public int compareTo(MaxSubArraySumResult o) {
		return this.maxSubArraySum - o.maxSubArraySum;
	}
}
