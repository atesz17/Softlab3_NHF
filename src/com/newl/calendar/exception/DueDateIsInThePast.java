package com.newl.calendar.exception;

public class DueDateIsInThePast extends Exception {

	public DueDateIsInThePast()	{
		super("The Date is in the past!");
	}
}
