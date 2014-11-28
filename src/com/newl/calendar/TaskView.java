package com.newl.calendar;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;
import javax.swing.JButton;
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
		
		/**
		 * GLOBAL
		 */
		setLayout(new BorderLayout());
		
		/**
		 * WEST OLDALA A BORDERLAYOUTNAK
		 */
		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BorderLayout());
		
		JTable table = new JTable(data);
		table.setFillsViewportHeight(true);
        table.setVisible(true);
		JScrollPane scrollPane = new JScrollPane(table);
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        //tablePanel.setPreferredSize(new Dimension(400, 400)); ez valamiert nem mukodik
        add(tablePanel); // itt nincs masodik parameter es ugy tunik kitolti a helyet, szval meno
        
        /**
         * EAST OLDALA A BORDERLAYOUTNAK
         */
        
        JPanel inputPanel = new JPanel();
        add(inputPanel, BorderLayout.EAST);
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        
        JPanel inputPanelTitle = new JPanel();
        inputPanelTitle.add(new JLabel("Parameters"));
        inputPanel.add(inputPanelTitle);
        
        JPanel titlePanel = new JPanel();
        JTextField titleField = new JTextField(20);
        titlePanel.add(new JLabel("Title:"));
        titlePanel.add(titleField);
        inputPanel.add(titlePanel);
        
        JPanel notesPanel = new JPanel();
        JTextField notesField = new JTextField(20);
        notesPanel.add(new JLabel("Notes:"));
        notesPanel.add(notesField);
        inputPanel.add(notesPanel);
        
        JPanel datesPanel = new JPanel();
        datesPanel.setLayout(new BoxLayout(datesPanel, BoxLayout.Y_AXIS));
        
        datesPanel.add(new JLabel("Due date:"));
        JPanel dueDatePanel = new JPanel();        
        JComboBox<Integer> dateY = new JComboBox<Integer>();
        JComboBox<String> dateM = new JComboBox<String>();
        JComboBox<Integer> dateD = new JComboBox<Integer>();
        dueDatePanel.add(new JLabel("Year:"));
        dueDatePanel.add(dateY);
        dueDatePanel.add(new JLabel("Month:"));
        dueDatePanel.add(dateM);
        dueDatePanel.add(new JLabel("Day:"));
        dueDatePanel.add(dateD);
        datesPanel.add(dueDatePanel);
        
        datesPanel.add(new JLabel("Remind me after:"));
        JPanel remindDatePanel = new JPanel();        
        JComboBox<Integer> remindY = new JComboBox<Integer>();
        JComboBox<String> remindM = new JComboBox<String>();
        JComboBox<Integer> remindD = new JComboBox<Integer>();
        remindDatePanel.add(new JLabel("Year:"));
        remindDatePanel.add(remindY);
        remindDatePanel.add(new JLabel("Month:"));
        remindDatePanel.add(remindM);
        remindDatePanel.add(new JLabel("Day:"));
        remindDatePanel.add(remindD);
        datesPanel.add(remindDatePanel);
        
        inputPanel.add(datesPanel);
        
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add");
        buttonPanel.add(addButton);
        
        inputPanel.add(buttonPanel);
        
        data.tryy();
        System.out.println("initComponents Done!");
	}
	
	public TaskView()	{
		super("Under Control");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		data = new TaskModel();
		
		initComponents();
		setPreferredSize(new Dimension(1300, 760));
		setMinimumSize(new Dimension(640, 480));
		
		System.out.println("TaskView() cnstr Done!");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		TaskView t = new TaskView();
		t.setVisible(true);
	}

}
