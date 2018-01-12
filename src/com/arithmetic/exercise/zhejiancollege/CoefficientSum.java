package com.arithmetic.exercise.zhejiancollege;

/*
 * 浙江大学-数据结构-练习
 */
public class CoefficientSum {
	
	public static void main(String[] str)
	{
		int[] coefficient = new int[]{3,4,6,2,5,7,2,5};
		int x = 3;
		int tallest = coefficient.length - 1;
		System.out.println(multinomialSum_1(tallest,coefficient,x));
		System.out.println(multinomialSum_2(tallest,coefficient,x));
	}
	
	
	/*
	 * 求多项式和:普通方式 
	 * tallest：参数的最高阶
	 * coefficient：各项系数
	 * argument：传入参数（X）
	 */
	public static double multinomialSum_1(int tallest,int[] coefficient,double argument)
	{
		int sum = coefficient[0];
		for(int i=1; i <= tallest; i++)
		{
			sum += coefficient[i]*Math.pow(argument, i);
		}
		return sum;
	}
	
	/*
	 * 求多项式和：装逼方式
	 */
	public static double multinomialSum_2(int tallest,int[] coefficient,double argument)
	{
		double sum = coefficient[tallest];
		for(int i=tallest; i > 0; i--)
		{
			sum = coefficient[i-1] + sum*argument;
		}
		return sum;
	}
}
