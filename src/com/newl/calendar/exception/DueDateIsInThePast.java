package com.newl.calendar.exception;

public class DueDateIsInThePast extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2579613080799105933L;

	public DueDateIsInThePast()	{
		super("The Date is in the past!");
	}
}
