package com.jprm.searchtwitter.entity;

public class AgrregateTweetHttpResponseWrapperEntity {
	
	private String report;
	
	private Long count;
	
	private Object data;

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

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
