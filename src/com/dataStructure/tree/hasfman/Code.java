package com.dataStructure.tree.hasfman;

public class Code {
	private char c;
	private int weight;
	private Code left;
	private Code right;
	private String code;
	
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public char getC() {
		return c;
	}
	public void setC(char c) {
		this.c = c;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public Code getLeft() {
		return left;
	}
	public void setLeft(Code left) {
		this.left = left;
	}
	public Code getRight() {
		return right;
	}
	public void setRight(Code right) {
		this.right = right;
	}
	public Code(char c, int weight, Code left, Code right) {
		super();
		this.c = c;
		this.weight = weight;
		this.left = left;
		this.right = right;
	}

	
	
}
