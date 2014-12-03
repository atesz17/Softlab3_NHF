package com.newl.calendar.exception;

public class InvalidTask extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 937567905703834543L;

	public InvalidTask()	{
		super("Reminder has to happen before due date!");
	}
}
