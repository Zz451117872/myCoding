<<<<<<< HEAD
package com.arithmetic.thought;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import com.arithmetic.entry.Activity;
import com.arithmetic.entry.ActivityComparetor;

/**
 * 贪心算法 
 * @author Administrator
 *
 */
public class Greed {

	/**
	 * 选择活动 ：动态规划 
	 * 活动选择：活动有开始结束时间，同一时间内只能安排一个活动，求如何尽可能安排多的活动在一天内
	 * @param activitys
	 * @return
	 */
	public static int selectActivityByDynamic(List<Activity> activitys)
	{
		//dp[x][y] 表示 x,y任务之间符合要求的任务数量，即x任务结束后开始，y任务开始前结束的任务，且任意2个任务时间不交叉
		int[][] dp = new int[activitys.size()][activitys.size()];
		for(int i=0; i<dp.length; i++)
		{
			for(int j=0; j<dp[i].length; j++)
			{
				if(i == j)
				{
					dp[i][j] = 0;
				}else{
					dp[i][j] = -1;
				}
			}
		}						
		int max = 0;
		for(int i=0; i<activitys.size(); i++)
		{
			for(int j=0; j<activitys.size(); j++)
			{
				max = Math.max(max, selectActivityByDynamicAux(activitys,dp,i,j));
			}
		}
		return max+2;
	}
	
	private static int selectActivityByDynamicAux(List<Activity> activitys, int[][] dp, int x, int y) 
	{
		if(dp[x][y] != -1 )
		{
			return dp[x][y];
		}
		Activity a1 = activitys.get(x);
		Activity a2 = activitys.get(y);			
		List<Activity> fitActivitys = getFitActivity(activitys, a1, a2);
		if(fitActivitys != null && !fitActivitys.isEmpty())
		{			
			int max = 0;
			for(int i=0; i<fitActivitys.size(); i++)
			{
				int index = activitys.indexOf(fitActivitys.get(i));
				max = Math.max(max, (
						selectActivityByDynamicAux(activitys,dp,x,index)+
						selectActivityByDynamicAux(activitys,dp,index,y)+1));
			}
			dp[x][y] = dp[y][x] = max;
		}else{
			dp[x][y] = dp[y][x] = 0;
		}
		return dp[x][y];
	}

	//找出两个 活动中间的兼容 活动，即以任务1结束后开始，以任务2开始前结束的任务
	private static List<Activity> getFitActivity(List<Activity> activitys, Activity a1, Activity a2) 
	{
		List<Activity> fit = new ArrayList<Activity>();
		for(int i=0; i<activitys.size(); i++)
		{
			Activity activity = activitys.get(i);
			if((activity.start >= a1.end && activity.end <= a2.start)
				||(activity.start >= a2.end && activity.end <= a1.start))
			{
				fit.add(activitys.get(i));
			}
		}
		return fit;
	}
	
	/**
	 * 选择活动 ：由底向上 
	 * @param activitys
	 * @return
	 */
	public static int down_up_selectActivity(List<Activity> activitys)
	{
		List<List<Activity>> result = new ArrayList<List<Activity>>();
		//dp[x] 表示 以x任务为结尾的，满足任务排列要求的任务的个数
		int[] dp = new int[activitys.size()+1];
		dp[0] = 0;	
		result.add(new ArrayList<Activity>());
		dp[1] = 1;		//以第1个任务为结尾的 ，满足任务排列要求的任务个数为1
		result.add(activitys.subList(0, 1));
		for(int i=2; i<=activitys.size(); i++)	//从第2个任务 递推
		{
			dp[i] = 1;
			List<Activity> acts = new ArrayList<Activity>();
			acts.add(activitys.get(i-1));
			for(int k=1; k<i; k++)		
			{
				if(compare(activitys,k-1,i-1))
				{
					if(dp[k]+1 > dp[i])
					{
						dp[i] = dp[k] +1;
						acts.clear();
						acts.addAll(result.get(k));
						acts.add(activitys.get(i-1));
					}
				}
			}
			result.add(acts);
		}
		System.out.println(Arrays.toString(dp));
		Arrays.sort(dp);
		return dp[activitys.size()];
	}

	/**
	 * 活动任务的比较方法
	 * @param activitys
	 * @param front
	 * @param back
	 * @return
	 */
	private static boolean compare(List<Activity> activitys, int front, int back) {
		return activitys.get(back).start >= activitys.get(front).end;
	}

	//使用贪心算法 选择活动
	public static List<Activity> selectActivityByGreed(List<Activity> activitys)
	{
		List<Activity> selected = new ArrayList<Activity>();	
		while(activitys != null && !activitys.isEmpty())
		{
			Activity act = getEarlist(activitys);
			selected.add(act);
			activitys = getFitActivity(activitys,act.end);
		}		
		return selected;
	}
		
	//获取集合中 适配给定开始时间的活动
	private static List<Activity> getFitActivity(List<Activity> activitys, int end) 
	{
		List<Activity> fit = new ArrayList<Activity>();
		for(int i=0; i<activitys.size(); i++)
		{
			if(activitys.get(i).start >= end)
			{
				fit.add(activitys.get(i));
			}
		}
		return fit;
	}

	//从活动集合中 筛选最早结束的活动
	private static Activity getEarlist(List<Activity> activitys) 
	{
		int earlist = Integer.MAX_VALUE;
		Activity act = null;
		for(int i=0; i<activitys.size(); i++)
		{
			if(activitys.get(i).end < earlist)
			{
				earlist = activitys.get(i).end;
				act = activitys.get(i);
			}
		}
		return act;
	}

	
	/**
	 * 跳跃游戏，能否从头跳到尾 ：贪心算法
	 * 跳跃游戏：给定一个数组，数组中的值表示可以跳跃的距离，判断是否可以跳到末尾
	 * @param arr
	 * @return
	 */
	public static boolean jumpByGreed(int[] arr)
	{
		int start = 0;  //当前位置
		int longest = 0; //记录跳跃的最远位置
		ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(arr.length);
		queue.add(arr[0]);
		while(!queue.isEmpty())
		{
			int skip = queue.poll();	//从该点能最多能跳跃多少格
			if(skip == 0)					//如果跳跃距离为0，则循环结束
			{
				longest = start; break;
			}
			for(int i=start+1; i<arr.length && i<=start+skip; i++)//计算能跳跃的最远点
			{
				longest = Math.max(longest, i+arr[i]);
			}
			if(longest >= arr.length-1)	//如果能跳跃的最远点 已达到 尾点，则结束循环
			{
				return true;
			}
			if(longest != 0 && longest < arr.length)//将最远点加入队列，重复循环
			{
				try {
					queue.put(arr[longest]);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				start = longest;
			}
		}
		return longest >= arr.length-1;
	}
	
	
	/**
	 * 跳跃游戏，能否从头跳到尾 ：动态规划
	 * @param arr
	 * @return
	 */
	public static boolean jumpByDynamic(int[] arr)
	{
		//dp[x] 表示 从数组第x个位置跳，是否可以跳到末尾 
		int[] dp = new int[arr.length];
		dp[arr.length-1] = 1;		//最后一个结点即结尾结点，为true
		dp[arr.length-2] = arr[arr.length-2] > 0 ? 1 : -1;	//倒数第二个结点，跳跃格数若为0，则为false，反之为true
		
		for(int i=arr.length-3; i>=0; i--)  //从倒数第3个结点开始跳
		{
			int value = arr[i];	//从该位置最多可以跳多少格
			dp[i] = -1;
			for(int k=1; k<=value; k++)	//只要跳跃范围内有一个可达结尾结点，即为true
			{
				if(i+k <= arr.length-1 && dp[i+k] == 1)
				{
					dp[i] = 1;
					break;
				}
			}
		}
		return dp[0] == 1;//判断第一个结点是否可达末尾
	}
	
	
	/*
	 * 455.分饼干
	 * 392.给定两个字符串s和t，判断s是不是t的子序列
	 * 435.给定一组区间，区间起始点小于终点，删除多少区间使得剩余区间不重叠。
	 */
=======
package com.arithmetic.thought;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import com.arithmetic.entry.Activity;
import com.arithmetic.entry.ActivityComparetor;


public class Greed {

	/*
	 * 活动选择：活动有开始结束时间，同一时间内只能安排一个活动，求如何尽可能安排多的活动在一天内
	 */
	//使用动态规划 选择活动
	public static int selectActivityByDynamic(List<Activity> activitys)
	{
		//dp[x][y] 表示 x,y任务之间符合要求的任务数量，即x任务结束后开始，y任务开始前结束的任务，且任意2个任务时间不交叉
		int[][] dp = new int[activitys.size()][activitys.size()];
		for(int i=0; i<dp.length; i++)
		{
			for(int j=0; j<dp[i].length; j++)
			{
				if(i == j)
				{
					dp[i][j] = 0;
				}else{
					dp[i][j] = -1;
				}
			}
		}						
		int max = 0;
		for(int i=0; i<activitys.size(); i++)
		{
			for(int j=0; j<activitys.size(); j++)
			{
				max = Math.max(max, selectActivityByDynamicAux(activitys,dp,i,j));
			}
		}
		return max+2;
	}
	
	private static int selectActivityByDynamicAux(List<Activity> activitys, int[][] dp, int x, int y) 
	{
		if(dp[x][y] != -1 )
		{
			return dp[x][y];
		}
		Activity a1 = activitys.get(x);
		Activity a2 = activitys.get(y);			
		List<Activity> fitActivitys = getFitActivity(activitys, a1, a2);
		if(fitActivitys != null && !fitActivitys.isEmpty())
		{			
			int max = 0;
			for(int i=0; i<fitActivitys.size(); i++)
			{
				int index = activitys.indexOf(fitActivitys.get(i));
				max = Math.max(max, (
						selectActivityByDynamicAux(activitys,dp,x,index)+
						selectActivityByDynamicAux(activitys,dp,index,y)+1));
			}
			dp[x][y] = dp[y][x] = max;
		}else{
			dp[x][y] = dp[y][x] = 0;
		}
		return dp[x][y];
	}

	//找出两个 活动中间的兼容 活动，即以任务1结束后开始，以任务2开始前结束的任务
	private static List<Activity> getFitActivity(List<Activity> activitys, Activity a1, Activity a2) 
	{
		List<Activity> fit = new ArrayList<Activity>();
		for(int i=0; i<activitys.size(); i++)
		{
			Activity activity = activitys.get(i);
			if((activity.start >= a1.end && activity.end <= a2.start)
				||(activity.start >= a2.end && activity.end <= a1.start))
			{
				fit.add(activitys.get(i));
			}
		}
		return fit;
	}
	
	//使用 由底向上 选择活动
	public static int down_up_selectActivity(List<Activity> activitys)
	{
		List<List<Activity>> result = new ArrayList<List<Activity>>();
		//dp[x] 表示 以x任务为结尾的，满足任务排列要求的任务的个数
		int[] dp = new int[activitys.size()+1];
		dp[0] = 0;	
		result.add(new ArrayList<Activity>());
		dp[1] = 1;		//以第1个任务为结尾的 ，满足任务排列要求的任务个数为1
		result.add(activitys.subList(0, 1));
		for(int i=2; i<=activitys.size(); i++)	//从第2个任务 递推
		{
			dp[i] = 1;
			List<Activity> acts = new ArrayList<Activity>();
			acts.add(activitys.get(i-1));
			for(int k=1; k<i; k++)		
			{
				if(compare(activitys,k-1,i-1))
				{
					if(dp[k]+1 > dp[i])
					{
						dp[i] = dp[k] +1;
						acts.clear();
						acts.addAll(result.get(k));
						acts.add(activitys.get(i-1));
					}
				}
			}
			result.add(acts);
		}
		System.out.println(Arrays.toString(dp));
		Arrays.sort(dp);
		return dp[activitys.size()];
	}

	private static boolean compare(List<Activity> activitys, int front, int back) {
		return activitys.get(back).start >= activitys.get(front).end;
	}

	//使用贪心算法 选择活动
	public static List<Activity> selectActivityByGreed(List<Activity> activitys)
	{
		List<Activity> selected = new ArrayList<Activity>();	
		while(activitys != null && !activitys.isEmpty())
		{
			Activity act = getEarlist(activitys);
			selected.add(act);
			activitys = getFitActivity(activitys,act.end);
		}		
		return selected;
	}
		
	//获取集合中 适配给定开始时间的活动
	private static List<Activity> getFitActivity(List<Activity> activitys, int end) 
	{
		List<Activity> fit = new ArrayList<Activity>();
		for(int i=0; i<activitys.size(); i++)
		{
			if(activitys.get(i).start >= end)
			{
				fit.add(activitys.get(i));
			}
		}
		return fit;
	}

	//从活动集合中 筛选最早结束的活动
	private static Activity getEarlist(List<Activity> activitys) 
	{
		int earlist = Integer.MAX_VALUE;
		Activity act = null;
		for(int i=0; i<activitys.size(); i++)
		{
			if(activitys.get(i).end < earlist)
			{
				earlist = activitys.get(i).end;
				act = activitys.get(i);
			}
		}
		return act;
	}

	/*
	 * 跳跃游戏：给定一个数组，数组中的值表示可以跳跃的距离，判断是否可以跳到末尾
	 */
	//跳跃游戏，能否从头跳到尾//贪心算法
	public static boolean jumpByGreed(int[] arr)
	{
		int start = 0;  //当前位置
		int longest = 0; //记录跳跃的最远位置
		ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(arr.length);
		queue.add(arr[0]);
		while(!queue.isEmpty())
		{
			int skip = queue.poll();	//从该点能最多能跳跃多少格
			if(skip == 0)					//如果跳跃距离为0，则循环结束
			{
				longest = start; break;
			}
			for(int i=start+1; i<arr.length && i<=start+skip; i++)//计算能跳跃的最远点
			{
				longest = Math.max(longest, i+arr[i]);
			}
			if(longest >= arr.length-1)	//如果能跳跃的最远点 已达到 尾点，则结束循环
			{
				return true;
			}
			if(longest != 0 && longest < arr.length)//将最远点加入队列，重复循环
			{
				try {
					queue.put(arr[longest]);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				start = longest;
			}
		}
		return longest >= arr.length-1;
	}
	
	//跳跃游戏，能否从头跳到尾//动态规划
	public static boolean jumpByDynamic(int[] arr)
	{
		//dp[x] 表示 从数组第x个位置跳，是否可以跳到末尾 
		int[] dp = new int[arr.length];
		dp[arr.length-1] = 1;		//最后一个结点即结尾结点，为true
		dp[arr.length-2] = arr[arr.length-2] > 0 ? 1 : -1;	//倒数第二个结点，跳跃格数若为0，则为false，反之为true
		
		for(int i=arr.length-3; i>=0; i--)  //从倒数第3个结点开始跳
		{
			int value = arr[i];	//从该位置最多可以跳多少格
			dp[i] = -1;
			for(int k=1; k<=value; k++)	//只要跳跃范围内有一个可达结尾结点，即为true
			{
				if(i+k <= arr.length-1 && dp[i+k] == 1)
				{
					dp[i] = 1;
					break;
				}
			}
		}
		return dp[0] == 1;//判断第一个结点是否可达末尾
	}
	/*
	 * 455.分饼干
	 * 392.给定两个字符串s和t，判断s是不是t的子序列
	 * 435.给定一组区间，区间起始点小于终点，删除多少区间使得剩余区间不重叠。
	 */
>>>>>>> 02bffa1629e700b327f64c46ccb3692216d2d4bf
}