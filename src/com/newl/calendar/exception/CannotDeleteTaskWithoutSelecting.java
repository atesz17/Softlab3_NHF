package com.newl.calendar.exception;

public class CannotDeleteTaskWithoutSelecting extends Exception{

	public CannotDeleteTaskWithoutSelecting()	{
		super("No task is selected. Select a task to use DELETE button.");
	}
}
