package com.arithmetic.test;

import org.junit.Test;

public class TestXXX {

	@Test
	public void intToStringTest()
	{
		int num = 13;
		char c = 'a';
		byte b = (byte) c;
		
		System.out.println(b);
		System.out.println(intToString(num));		
	}

	private String intToString(int num) {
		String result = "";
		while(num != 0)
		{
			result += '0'+ num%10;
			num /= 10;
		}
		return result;
	}
}
