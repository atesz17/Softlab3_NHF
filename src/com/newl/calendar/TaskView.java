package com.newl.calendar;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class TaskView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4222858702398138432L;
	
	private TaskModel data;
	
	private void initComponents()	{
		
		setLayout(new BorderLayout());
		
		JTable table = new JTable(data);
		add(BorderLayout.CENTER, table);
		
		JScrollPane scrollPane = new JScrollPane(table);
		add(scrollPane);
		
		table.setFillsViewportHeight(true);
        table.setVisible(true);
        
        JPanel inputPanel = new JPanel();
        add(inputPanel, BorderLayout.EAST);
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        
        JTextField titleField = new JTextField(20);
        JTextField notesField = new JTextField(20);
        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Notes:"));
        inputPanel.add(notesField);
        
        JComboBox<Integer> dateY = new JComboBox();
        JComboBox<String> dateM = new JComboBox();
        JComboBox<Integer> dateD = new JComboBox();
        inputPanel.add(dateY);
        inputPanel.add(dateM);
        inputPanel.add(dateD);
        
        JComboBox<Integer> remindY = new JComboBox();
        JComboBox<String> remindM = new JComboBox();
        JComboBox<Integer> remindD = new JComboBox();
        
        
        System.out.println("initComponents Done!");
	}
	
	public TaskView()	{
		super("Under Control");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		data = new TaskModel();
		
		initComponents();
		setMinimumSize(new Dimension(1300, 760));
		
		System.out.println("TaskView() cnstr Done!");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		TaskView t = new TaskView();
		t.setVisible(true);
	}

}
