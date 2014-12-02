package com.newl.calendar;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Task implements Serializable, Comparable<Task> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2363842128403009351L;
	
	
	private String title;
	private String notes;
	private Calendar date;
	private Calendar remindMeFromNowOn;
	
	public Task(String t, String d, Calendar da, Calendar remind)	{
		
		title = t;
		notes = d;
		date = da;
		remindMeFromNowOn = remind;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String n) {
		this.notes = n;
	}
	
	public Calendar getDate()	{
		return date;
	}
	
	public void setDate(Calendar c)	{
		this.date = c;
	}
	
	public Calendar getRemindDate()	{
		return remindMeFromNowOn;
	}
	
	public void setRemindDate(Calendar r)	{
		this.remindMeFromNowOn = r;
	}
	
	public String getYMDInString(Calendar d)	{
		
		SimpleDateFormat formatum = new SimpleDateFormat("yyyy-MM-dd");
		String formatted = formatum.format(d.getTime());
		return formatted;
	}
	
	public Boolean isUrgent()	{
		if (Calendar.getInstance().after(remindMeFromNowOn))
			return true;
		return false;
	}

	/**
	 * Comparable interfesz fuggvenyenek megvalositasa. Akkor "kisebb" egy task a masiknal, ha az remindMeFromNowOn date-je elobb van
	 */
	@Override
	public int compareTo(Task t) {
		
		if (this.remindMeFromNowOn.before(t.remindMeFromNowOn))
			return -1;
		else if (this.remindMeFromNowOn.after(t.remindMeFromNowOn))
			return 1;
		else
			return 0;
	}
	
	@Override
	public boolean equals(Object other)	{
		
		if (!title.equals(((Task)other).title))
			return false;
		if (!date.equals(((Task)other).date))
			return false;
		return true;
	}
	
	@Override
	public String toString()	{
		return "Title:= " + title + ", Notes:= " + notes + ", Due Date:= " + date + ", Remind at:= " + remindMeFromNowOn; 
	}
	
}
