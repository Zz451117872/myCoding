package com.arithmetic.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import com.arithmetic.entry.Activity;
import com.arithmetic.entry.ActivityComparetor;
import com.arithmetic.thought.Greed;

public class TestGreed {
	static Random random = new Random();
	static List<Activity> activitys = null;
	@Before
	public void before()
	{
		activitys = createActivity(10);
	}
	
	@Test
	public void selectActivityByGreed_test()
	{
		printActivitys(activitys);
		System.out.println("-----selectActivityByGreed_test------------------------------------------");
		List<Activity> selected = Greed.selectActivityByGreed(activitys);
		printActivitys(selected);
		System.out.println("------selectActivityByDynamic_test-------------------------------------");
		System.out.println(Greed.selectActivityByDynamic(activitys));
		System.out.println("------down_up_selectActivity-------------------------------------");
		System.out.println(Greed.down_up_selectActivity(activitys));
	}

	
	
	private void printActivitys(List<Activity> selected) {
		if(selected != null && !selected.isEmpty())
		{
			for(int i=0; i<selected.size(); i++)
			{
				System.out.println(selected.get(i));
			}
		}
	}

	private List<Activity> createActivity(int count) {
		List<Activity> activitys = new ArrayList<Activity>();
		for(int i=0; i<count; i++)
		{
			int start = random.nextInt(20);
			Activity activity = new Activity(i+1,start,start+random.nextInt(6)+1);
			activitys.add(activity);
		}
		Activity[] temp = new Activity[activitys.size()];
		activitys.toArray(temp);
		Arrays.sort(temp, new ActivityComparetor());
		activitys = Arrays.asList(temp);
		for(int i=0; i<activitys.size(); i++)
		{
			activitys.get(i).id = i;
		}
		return activitys;
	}
}
