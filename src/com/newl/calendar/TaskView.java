package com.newl.calendar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import com.newl.calendar.exception.DueDateIsInThePast;
import com.newl.calendar.exception.EmptyTitleInTask;
import com.newl.calendar.exception.InvalidTask;
import com.newl.calendar.exception.TaskAlreadyInDatabase;

public class TaskView extends JFrame{

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
        
        /*
    	Integer[] years = {Calendar.getInstance().getWeekYear(), Calendar.getInstance().getWeekYear() + 1, Calendar.getInstance().getWeekYear() + 2, Calendar.getInstance().getWeekYear() + 3};
    	Integer[] months = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    	Integer[] days = new Integer[31];
    	for (int i = 0; i < 31; i++)
    		days[i] = i+1;
    		*/
    	
    	
        datesPanel.add(new JLabel("Due date:"));        
        JPanel dueDatePanel = new JPanel();        
        JComboBox<Integer> dateY = new JComboBox<Integer>();
        initComboBoxWithYearAfterToday(dateY);
        JComboBox<Integer> dateM = new JComboBox<Integer>();
        initComboBoxWithMonthAfterToday(dateM);
        JComboBox<Integer> dateD = new JComboBox<Integer>();
        initComboBoxWithDayAfterToday(dateD);
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
        initComboBoxWithYearAfterToday(remindY);
        JComboBox<Integer> remindM = new JComboBox<Integer>();
        initComboBoxWithMonthAfterToday(remindM);
        JComboBox<Integer> remindD = new JComboBox<Integer>();
        initComboBoxWithDayAfterToday(remindD);
        remindDatePanel.add(new JLabel("Year:"));
        remindDatePanel.add(remindY);
        remindDatePanel.add(new JLabel("Month:"));
        remindDatePanel.add(remindM);
        remindDatePanel.add(new JLabel("Day:"));
        remindDatePanel.add(remindD);
        datesPanel.add(remindDatePanel);
        
        inputPanel.add(datesPanel);
              
        class ComboBoxYearsMonthsDaysListener implements ActionListener	{

			@Override
			public void actionPerformed(ActionEvent e) {
				
				Calendar date = Calendar.getInstance();
				
			}
    		
    	}
        
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add");
        buttonPanel.add(addButton);
        
        addButton.addActionListener(new ActionListener()	{
        	
        	@Override
        	public void actionPerformed(ActionEvent arg0)	{
        		
        		try	{
        			Task t = makeTask(titleField.getText(),
        					notesField.getText(),
        					(Integer)dateY.getSelectedItem(), (Integer)dateM.getSelectedItem(), (Integer)dateD.getSelectedItem(),
        					(Integer)remindY.getSelectedItem(), (Integer)remindM.getSelectedItem(), (Integer)remindD.getSelectedItem());
        			data.attemptToAddTask(t);
        		}
        		catch (InvalidTask e)	{
        			System.err.println(e.getMessage());
        		}
        		catch(TaskAlreadyInDatabase e)	{
        			System.err.println(e.getMessage());
        		}
        		catch(EmptyTitleInTask e){
        			System.err.println(e.getMessage());
        		}
        		catch(DueDateIsInThePast e){
        			System.err.println(e.getMessage());
        		}
        	}
        });
        
        
        inputPanel.add(buttonPanel);
        
        //data.tryy();
        System.out.println("initComponents Done!");
	}
	
	void initComboBoxWIthStrings(JComboBox<String> c, String[] s)	{
		
		for (String element : s)
			c.addItem(element);
	}
	
	void initComboBoxWithIntegers(JComboBox<Integer> c, Integer[] i)	{
		
		for (Integer element : i)
			c.addItem(element);
	}
	
	void initComboBoxWithYearAfterToday(JComboBox<Integer> c)	{
		
		int year = Calendar.getInstance().get(Calendar.YEAR);
		for (int i = year; i < year + 10; i++)
			c.addItem(i);
	}
	
	void initComboBoxWithMonthAfterToday(JComboBox<Integer> month){
		
		for (int i = Calendar.getInstance().get(Calendar.MONTH); i < 12; i++)
			month.addItem(i + 1); // 0tol 11ig vannak a honapok
	}
	
	void initComboBoxWithDayAfterToday(JComboBox<Integer> day){
		
		int totalDays = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
		for (int i = Calendar.getInstance().get(Calendar.DAY_OF_MONTH); i <= totalDays; i++)
			day.addItem(i);
	}
	
	Task makeTask(String title, String notes,
			Integer dY, Integer dM, Integer dD,
			Integer rY, Integer rM, Integer rD)
					throws EmptyTitleInTask, DueDateIsInThePast	{
		
		if (title.equals(""))
			throw new EmptyTitleInTask();
		
		Calendar dueDate = Calendar.getInstance();
		Calendar remindDate = Calendar.getInstance();
		
		dueDate.set(dY, dM, dD);
		dueDate.set(Calendar.HOUR_OF_DAY, 0);
		dueDate.set(Calendar.MINUTE, 0);
		dueDate.set(Calendar.SECOND, 0);
		dueDate.set(Calendar.MILLISECOND, 0);
		
		if (dueDate.before(Calendar.getInstance()))
			throw new DueDateIsInThePast();
		
		remindDate.set(rY, rM, rD);
		remindDate.set(Calendar.HOUR_OF_DAY, 0);
		remindDate.set(Calendar.MINUTE, 0);
		remindDate.set(Calendar.SECOND, 0);
		remindDate.set(Calendar.MILLISECOND, 0);
		
		
		return new Task(title, notes, dueDate, remindDate);
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
