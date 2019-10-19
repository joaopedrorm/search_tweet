package com.jprm.searchtwitter.entity;

public class AggregateTweetResultEntity {
	
	private Long count;
	
	private Object data;

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
