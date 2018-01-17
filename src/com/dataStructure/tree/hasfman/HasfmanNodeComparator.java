package com.dataStructure.tree.hasfman;

import java.util.Comparator;

public class HasfmanNodeComparator implements Comparator<HasfmanNode>{

	@Override
	public int compare(HasfmanNode c1, HasfmanNode c2) {
		return c1.getWeight() - c2.getWeight();
	}

}
