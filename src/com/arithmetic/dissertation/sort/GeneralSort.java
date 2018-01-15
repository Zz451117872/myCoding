package com.arithmetic.dissertation.sort;
/*
 * 通用排序
 */
public interface GeneralSort {
	/*
	 * 冒泡排序
	 */
	public <T extends Comparable<T>> void bubbleSort(T[] arr,int start, int end);
	
	/*
	 * 选择排序
	 */
	public <T extends Comparable<T>> void  selectSort(T[] arr,int start, int end);
	
	/*
	 * 插入排序
	 * arr: 待排序数据
	 * start: 起始位置
	 * end: 结束位置
	 */
	public <T extends Comparable<T>> void insertSort(T[] arr,int start,int end);
	
	/*
	 * 归并排序：至顶向下递归
	 *  arr: 待排序数据
	 * start: 起始位置
	 * end: 结束位置
	 * room: 排序需要空间
	 */
	public <T extends Comparable<T>> void mergeSortByRecursion(T[] arr, int start, int end, T[] room);
	
	/*
	 * 归并排序：由底向上递推
	 */
	public <T extends Comparable<T>> void mergeSortByRecurrence(T[] arr, int start, int end, T[] room);

	/*
	 * 快速排序1路算法
	 */
	public <T extends Comparable<T>> void qulikSort1Ways(T[] arr, int start, int end);
	
	/*
	 * 快速排序2路算法
	 */
	public <T extends Comparable<T>> void qulikSort2Ways(T[] arr, int start, int end);
	
	/*
	 * 快速排序3路算法
	 */
	public <T extends Comparable<T>> void qulikSort3Ways(T[] arr, int start, int end ,T[] room);

	/*
	 * 堆排序
	 */
	public <T extends Comparable<T>> void heapSort(T[] arr);
	
}
