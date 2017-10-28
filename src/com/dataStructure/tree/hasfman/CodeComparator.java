package com.dataStructure.tree.hasfman;

import java.util.Comparator;

public class CodeComparator implements Comparator<Code>{

	@Override
	public int compare(Code c1, Code c2) {
		return c1.getWeight() - c2.getWeight();
	}

}
