package com.yedam.ssy;

public class FullCalendarVO {
	private String title;
	private String startDate;
	private String endDate;
	
	// source > constructor using fields.	생성자.
	public FullCalendarVO(String title, String startDate, String endDate) {
		super();
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
