package com.dataStructure.graph.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalUtil {

	public static double retainSeveralDecimals(int several,double data)
	{
		BigDecimal bd = new BigDecimal(data);
		return bd.setScale(2, RoundingMode.HALF_UP).doubleValue();
	}
}
