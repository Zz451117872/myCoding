package com.arithmetic.entry;

import java.util.Comparator;

public class ActivityComparetor implements Comparator<Activity>{

	@Override
	public int compare(Activity o1, Activity o2) {
		if(o1.end > o2.end)
		{
			return 1;
		}else if(o1.end == o2.end )
		{
			return 0;
		}
		return -1;
	}

}
