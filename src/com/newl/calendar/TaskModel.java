package com.newl.calendar;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.table.AbstractTableModel;

import com.newl.calendar.exception.CannotDeleteTaskWithoutSelecting;
import com.newl.calendar.exception.InvalidTask;
import com.newl.calendar.exception.TaskAlreadyInDatabase;

public class TaskModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2589449683766524478L;
	
	private String[] columnNames = {"Title", "Notes", "Date"};
	
	ArrayList<Task> tasks = new ArrayList<Task>();

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
		default:	return t.getYMDInString(t.getDate());
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
		default:	return String.class;
		}
	}
	
	/**
	 * Uj Task hozzaadasa az ArrayListhez.
	 * Miutan a Task hozzalett adva a tombhoz, az sorbarendezi remindMeFromNowOn datum alapjan.
	 * @param t A Task amit be szeretnenk szurni.
	 * @throws TaskAlreadyInDatabase Ha a megadott task mar letezik.
	 */
	void addTask(Task t) throws TaskAlreadyInDatabase	{
		
		if (tasks.contains(t))
			throw new TaskAlreadyInDatabase();
		
		tasks.add(t);
		Collections.sort(tasks, new TaskComparator());
	
		// DEBUG
		// Kiirjuk az elemeket, amiket tarolunk
		for (Task ct : tasks)
			System.out.println(ct);
		System.out.println();
		// DEBUG END
		
		fireTableRowsInserted(tasks.size() - 1, tasks.size() - 1);
		fireTableDataChanged();
	}
	
	boolean isValid(Task t)	{
		
		if (!t.getDate().before(t.getRemindDate()))
			return true;
		return false;
		
	}
	
	public void attemptToAddTask(Task t) throws TaskAlreadyInDatabase, InvalidTask	{
		
		if (!isValid(t))
			throw new InvalidTask();
		addTask(t);
	}

	public void removeTask(int selectedRow) throws CannotDeleteTaskWithoutSelecting {
		// TODO Auto-generated method stub
		if (selectedRow == -1)
			throw new CannotDeleteTaskWithoutSelecting();
		tasks.remove(selectedRow);
		
		fireTableRowsDeleted(selectedRow, selectedRow);
		fireTableDataChanged();
	}
}
