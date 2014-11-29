package com.newl.calendar.exception;

public class TaskAlreadyInDatabase extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6115615884756326256L;

	public TaskAlreadyInDatabase() {
		super("Task is already in list!");
	}
}
