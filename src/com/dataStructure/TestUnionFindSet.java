package com.dataStructure;

import java.util.Random;

import com.dataStructure.union.UnionFindSet;
import com.dataStructure.union.UnionFindSetBase;
import com.dataStructure.union.UnionFindSetUpgrade;
import com.dataStructure.union.UnionFindSetUpgrade2;
import com.dataStructure.union.UnionFindSetUpgrade3;
import com.dataStructure.union.UnionFindSetUpgrade4;
import com.dataStructure.union.UnionFindSetUpgrade5;

public class TestUnionFindSet {
	static Random r = new Random();
	public static void main(String[] str)
	{	
		int capatify = 100000;
		int count = 100000;
		UnionFindSetBase ufs = new UnionFindSetBase(capatify);
		test("UnionFindSetBase",ufs,capatify,count);	
		UnionFindSetUpgrade ufsu = new UnionFindSetUpgrade(capatify);
		test("UnionFindSetUpgrade",ufsu,capatify,count);	
		UnionFindSetUpgrade2 ufsu2 = new UnionFindSetUpgrade2(capatify);
		test("UnionFindSetUpgrade2",ufsu2,capatify,count);	
		UnionFindSetUpgrade3 ufsu3 = new UnionFindSetUpgrade3(capatify);
		test("UnionFindSetUpgrade3",ufsu3,capatify,count);
		UnionFindSetUpgrade4 ufsu4 = new UnionFindSetUpgrade4(capatify);
		test("UnionFindSetUpgrade4",ufsu4,capatify,count);
		UnionFindSetUpgrade5 ufsu5 = new UnionFindSetUpgrade5(capatify);
		test("UnionFindSetUpgrade5",ufsu5,capatify,count);
	}

	private static void test(String testname,UnionFindSet ufs, int capatify,int c) {
		long t1 = System.currentTimeMillis();
		int count3 =0;
		for(int i=0; i<c; i++)
		{
			int p = r.nextInt(capatify);
			int q = r.nextInt(capatify);
			if(p != q)
			{
				if(ufs.union(p, q))
				count3++;
			}		
		}
		System.out.println("connection success count:"+count3);
		int count =0;
		int count2 = 0;
		for(int i=0; i<c; i++)
		{
			int p = r.nextInt(capatify);
			int q = r.nextInt(capatify);
			if( p != q)
			{
				boolean result = ufs.isConnection(p, q);
				if(result)
				{
					count ++;
				}else{
					count2++;
				}
			}
		}
		System.out.println("connection success:"+count+" connection faild:"+count2);	
		long t2 = System.currentTimeMillis();
		System.out.println(testname+"用时："+(t2 - t1)/1000.0+"s");	
	}
}
