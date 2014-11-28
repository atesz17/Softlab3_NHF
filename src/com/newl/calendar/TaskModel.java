package com.newl.calendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import javax.swing.table.AbstractTableModel;

public class TaskModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2589449683766524478L;
	
	private String[] columnNames = {"Title", "Description", "Date"};
	
	private ArrayList<Task> tasks = new ArrayList<Task>();

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
		case 1: 	return t.getNotes();
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
	
	/**
	 * Uj Task hozzaadasa az ArrayListhez.
	 * Miutan a Task hozzalett adva a tombhoz, az sorbarendezi remindMeFromNowOn datum alapjan.
	 * @param t A Task amit be szeretnenk szurni.
	 * @throws TaskAlreadyInDatabase Ha a megadott task mar letezik.
	 */
	public void addTask(Task t) throws TaskAlreadyInDatabase	{
		
		if (tasks.contains(t))
			throw new TaskAlreadyInDatabase();
		
		tasks.add(t);
		Collections.sort(tasks, new TaskComparator());
	
		fireTableRowsInserted(tasks.size() - 1, tasks.size() - 1);
	}

	public void tryy()	{
		for (int i = 0; i < 99; i++)
			tasks.add(new Task("a" + i,"be" + i, Calendar.getInstance(), Calendar.getInstance()));
	}
}
