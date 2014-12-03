package com.newl.calendar.exception;

public class CannotDeleteTaskWithoutSelecting extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6438876847370369275L;

	public CannotDeleteTaskWithoutSelecting()	{
		super("No task is selected. Select a task to use DELETE button.");
	}
}
