package com.newl.calendar;

import java.util.Calendar;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Test;

public class TaskTest {

	@Test
	public void testTitleGetAndSet()	{
		
		ArrayList<String> p = new ArrayList<String> ();
		p.add("Egyben");
		p.add("Kulon van");
		p.add("Szam is van 123");
		
		ArrayList<String> result = new ArrayList<String> ();
		
		Task t = new Task(null, null, null, null);
		
		for (String s : p)	{
			t.setTitle(s);
			result.add(t.getTitle());
		}
		
		Boolean res = (p.equals(result));
		Assert.assertEquals(Boolean.TRUE, res);
	}
	
	@Test
	public void testNotesGetAndSet()	{
		
		ArrayList<String> p = new ArrayList<String> ();
		p.add("Egyben");
		p.add("Kulon van");
		p.add("Szam is van 123");
		p.add("Sot meg vesszo is,");
		
		ArrayList<String> result = new ArrayList<String> ();
		
		Task t = new Task(null, null, null, null);
		
		for (String s : p)	{
			t.setNotes(s);
			result.add(t.getNotes());
		}

		Boolean res = (p.equals(result));
		Assert.assertEquals(Boolean.TRUE, res);
	}
	
	@Test
	public void testDueDateGetAndSet()	{
		
		Calendar d = new GregorianCalendar(1900,01,01);
		Task t = new Task(null, null, d, null);
		Assert.assertEquals(d, t.getDate());
	}
	
	@Test
	public void testRemindDateGetANdSet()	{
		
		Calendar d = new GregorianCalendar(1900,01,01);
		Task t = new Task(null, null, null, d);
		Assert.assertEquals(d, t.getRemindDate());
	}
	
	@Test
	public void testYMDStringFromat()	{
		
		Task t1 = new Task(null, null, new GregorianCalendar(1900,0, 01), null); // mert jo otlet volt 0tol a honapok...
		Task t2 =new Task(null, null, null, new GregorianCalendar(1999, 10, 9));
		
		ArrayList<String> res = new ArrayList<String> ();
		res.add(t1.getYMDInString(t1.getDate()));
		res.add(t2.getYMDInString(t2.getRemindDate()));
		
		Boolean result = ((res.get(0).equals("1900-01-01")) && (res.get(1).equals("1999-11-09")));
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void testIsTaskUrgent() throws InterruptedException	{
		
		Task t1 = new Task(null, null, null, Calendar.getInstance());
		
		Boolean[] res = new Boolean[2];
		Thread.sleep(100); // azert kell, mert annyira gyors, hogy a milisecundomost hasonlitas a datenel is rossz eredmenyt adna
		res[0] = t1.isUrgent();
		
		Calendar newRemind = Calendar.getInstance();
		newRemind.set(Calendar.MINUTE, newRemind.get(Calendar.MINUTE) + 1);
		t1.setRemindDate(newRemind);
		
		res[1] = t1.isUrgent();
		
		Boolean result = res[0].equals(true) && res[1].equals(false);		
		Assert.assertEquals(true, result);
	}
	
	@Test
	public void testCompareCheck()	{
		
		Integer[] results = new Integer[3];
		
		Task t = new Task (null, null, null, new GregorianCalendar(1900, 5, 2));
		
		results[0] = new Task(null, null, null, new GregorianCalendar(1900, 5, 1)).compareTo(t);
		results[1] = t.compareTo(t);
		results[2] = new Task(null, null, null, new GregorianCalendar(1900, 5, 3)).compareTo(t);
		
		Boolean result = results[0] == -1 && results[1] == 0 && results[2]==1;
		
		Assert.assertEquals(true, result);
	}
}
