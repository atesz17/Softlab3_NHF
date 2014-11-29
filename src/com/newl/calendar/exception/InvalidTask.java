package com.newl.calendar.exception;

public class InvalidTask extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 937567905703834543L;

	public InvalidTask()	{
		super("Something is not right with the task");
	}
}
