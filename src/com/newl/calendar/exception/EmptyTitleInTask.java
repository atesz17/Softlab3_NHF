package com.newl.calendar.exception;

public class EmptyTitleInTask extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -9002395606228935100L;

	public EmptyTitleInTask()	{
		super("No title for the task.");
	}
}
