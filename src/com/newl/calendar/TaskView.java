package com.newl.calendar;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TaskView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4222858702398138432L;
	
	private TaskModel data;
	
	private void initComponents()	{
		
		data.tryy();
		
		setLayout(new BorderLayout());
		
		JTable table = new JTable(data);
		add(BorderLayout.CENTER, table);
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
		table.setFillsViewportHeight(true);
        table.setVisible(true);
	}
	
	public TaskView()	{
		super("Under Control");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		data = new TaskModel();
		
		initComponents();
		setMinimumSize(new Dimension(500, 200));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		TaskView t = new TaskView();
		t.setVisible(true);
	}

}
