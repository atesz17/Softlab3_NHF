package com.newl.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class TaskModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2589449683766524478L;
	
	private String[] columnNames = {"Title", "Description", "Date"};
	
	private ArrayList<Task> tasks = new ArrayList<Task>();
	
	public void tryy()	{
		tasks.add(new Task("cime", "leirasa", Calendar.getInstance()));
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return tasks.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Task t = tasks.get(rowIndex);
		
		switch(columnIndex){
		case 0:		return t.getTitle();
		case 1: 	return t.getDescription();
		default:	return t.getDate();
		}
	}
	
	@Override
	public String getColumnName(int columnIndex)	{
		return columnNames[columnIndex];
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex)	{
		
		switch(columnIndex){
		case 0:		return String.class;
		case 1:		return String.class;
		default:	return Calendar.class;
		}
	}
	
	public void addTask(Task t)	{
		
		tasks.add(t);
		fireTableRowsInserted(tasks.size() - 1, tasks.size() - 1);
	}

}
