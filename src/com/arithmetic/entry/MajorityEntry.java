package com.arithmetic.entry;

public class MajorityEntry {

	private Integer key;
	private int count;
	
	public MajorityEntry(Integer key)
	{
		this.key = key;
		this.count = 1;
	}
	
	public Integer getKey() {
		return key;
	}
	public void setKey(Integer key) {
		this.key = key;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + count;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MajorityEntry other = (MajorityEntry) obj;
		if (key == other.key) {
			return true;
		}
		return false;
	}
	
}
