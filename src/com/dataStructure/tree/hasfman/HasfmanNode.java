package com.dataStructure.tree.hasfman;
/*
 * 哈夫曼树节点
 */
public class HasfmanNode {
	private Character c;		//该结点对应的 字符
	private int weight; //权重
	private HasfmanNode left;	//左子节点
	private HasfmanNode right;	//右子节点
	private String code;	//该结点对应的 密码串
	
	public HasfmanNode(int weight, HasfmanNode left, HasfmanNode right) {		
		this.weight = weight;
		this.left = left;
		this.right = right;
	}
	
	public HasfmanNode(char c, int weight, HasfmanNode left, HasfmanNode right)
	{
		this.c = c;
		this.weight = weight;
		this.left = left;
		this.right = right;
	}
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Character getC() {
		return c;
	}
	public void setC(Character c) {
		this.c = c;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public HasfmanNode getLeft() {
		return left;
	}
	public void setLeft(HasfmanNode left) {
		this.left = left;
	}
	public HasfmanNode getRight() {
		return right;
	}
	public void setRight(HasfmanNode right) {
		this.right = right;
	}
	

	
	
}
