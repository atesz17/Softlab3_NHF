package com.newl.calendar;

import java.io.Serializable;
import java.util.Date;

public class Event implements Serializable {

	private static final long serialVersionUID = 1L;
	
	String title;
	String description;
	Date startingDate;
	Date finalDate;
	
	public Event(String t, String d, Date sD, Date fD)	{
		
		title = t;
		description = d;
		startingDate = sD;
		finalDate = sD;
	}

	@Override
	public String toString() {
		return "Event [title=" + title + ", description=" + description
				+ ", startingDate=" + startingDate + ", finalDate=" + finalDate
				+ "]";
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

	public Date getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}

	public Date getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(Date finalDate) {
		this.finalDate = finalDate;
	}
}
