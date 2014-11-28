package com.newl.calendar;

import java.io.Serializable;
import java.util.Calendar;

public class Task implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String title;
	private String description;
	private Calendar date;
	
	public Task(String t, String d, Calendar da)	{
		
		title = t;
		description = d;
		date = da;
	}

	@Override
	public String toString() {
		return "Event [title=" + title + ", description=" + description + "date=" + date +"]";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Calendar getDate()	{
		return date;
	}
	
	public void setDate(Calendar c)	{
		this.date = c;
	}
}
