package com.dataStructure.union;

import java.util.Random;

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
	}

	private static void test(String testname,UnionFindSet ufs, int capatify,int c) {
		long t1 = System.currentTimeMillis();
		int unionSuccess =0;
		for(int i=0; i<c; i++)
		{
			int p = r.nextInt(capatify);
			int q = r.nextInt(capatify);
			if(p != q)
			{
				if(ufs.union(p, q))
					unionSuccess++;
			}		
		}
		System.out.println("union success:"+unionSuccess);
		int success =0;
		int faild = 0;
		for(int i=0; i<c; i++)
		{
			int p = r.nextInt(capatify);
			int q = r.nextInt(capatify);
			if( p != q)
			{
				boolean result = ufs.isConnection(p, q);
				if(result)
				{
					success ++;
				}else{
					faild++;
				}
			}
		}
		System.out.println("connection success:"+success+" connection faild:"+faild);	
		long t2 = System.currentTimeMillis();
		System.out.println(testname+"用时："+(t2 - t1)/1000.0+"s");	
	}
}
