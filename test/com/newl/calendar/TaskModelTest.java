package com.newl.calendar;

import java.util.Calendar;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;

import com.newl.calendar.exception.InvalidTask;
import com.newl.calendar.exception.TaskAlreadyInDatabase;


public class TaskModelTest {

	@Test
	public void testColumnCount()	{
		
		TaskModel t = new TaskModel();
		
		Assert.assertEquals(3, t.getColumnCount());
	}
	
	@Test
	public void testRowCount()	{
		
		TaskModel t = new TaskModel();
		
		Assert.assertEquals(t.tasks.size(), t.getRowCount());
	}
	
	@Test
	public void testGetValueAt() throws TaskAlreadyInDatabase	{
		
		TaskModel tm = new TaskModel();
		Task t = new Task("title", "notes", new GregorianCalendar(2014, 11, 2), null);
		
		tm.addTask(t);
		
		String[] results = new String[3];
		
		results[0] = (String)tm.getValueAt(0, 0);
		results[1] = (String)tm.getValueAt(0, 1);
		results[2] = (String)tm.getValueAt(0, 2);
		
		boolean res = results[0].equals("title") && results[1].equals("notes") && results[2].equals("2014-12-02");
		
		Assert.assertEquals(true, res);
	}
	
	@Test (expected=TaskAlreadyInDatabase.class)
	public void testTaskAlreadyInEx() throws TaskAlreadyInDatabase	{
		
		TaskModel tm = new TaskModel();
		Task t = new Task("title", "notes", new GregorianCalendar(1900, 0, 1), new GregorianCalendar(1900, 0, 1));
		
		tm.addTask(t);		
		tm.addTask(t);
	}
	
	@Test
	public void testCheckValidTask()	{
		
		TaskModel tm = new TaskModel();
		Task t = new Task(null, null, new GregorianCalendar(1000, 10, 10), new GregorianCalendar(2000, 10, 10));
		
		Assert.assertEquals(false, tm.isValid(t));
	}
	
	@Test (expected = InvalidTask.class)
	public void testCheckInvalidEx() throws TaskAlreadyInDatabase, InvalidTask	{
		
		TaskModel tm = new TaskModel();
		Task t = new Task(null, null, new GregorianCalendar(1000, 10, 10), new GregorianCalendar(2000, 10, 10));
		
		tm.attemptToAddTask(t);
	}
}
