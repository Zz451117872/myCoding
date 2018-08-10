package com.arithmetic.dissertation.sort;

/*
 * 不常用 排序
 */
public interface InactiveSort {

	/*
	 * 表排序 
	 */
	public <T extends Comparable<T>> void tableSort(T[] arr, T[] table);
	
	/*
	 * 基数排序
	 */
	public <T extends Comparable<T>> void radixSort(T[] arr, int start , int end);

	/*
	 * 桶排序
	 */
	public <T extends Comparable<T>> void bucketSort(T[] arr);

}
