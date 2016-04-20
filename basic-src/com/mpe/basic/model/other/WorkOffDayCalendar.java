package com.mpe.basic.model.other;

import java.io.Serializable;

public class WorkOffDayCalendar implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String event;
	int month;
	int day;
	int year;
	
	
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	public int getMonth() {
		return (month - 1);
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	
	
	

}
