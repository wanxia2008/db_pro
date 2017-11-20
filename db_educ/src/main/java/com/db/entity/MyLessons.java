package com.db.entity;


import java.util.List;

public class MyLessons {
	private String myDate;
	private List<TimeAndLesson> talList;

	public String getMyDate() {
		return myDate;
	}
	public void setMyDate(String myDate) {
		this.myDate = myDate;
	}
	public List<TimeAndLesson> getTalList() {
		return talList;
	}
	public void setTalList(List<TimeAndLesson> talList) {
		this.talList = talList;
	}
}
