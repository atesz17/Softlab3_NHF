package com.newl.calendar;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingUtilities;

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
        
        dateY.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if ((Integer)dateY.getSelectedItem() > Calendar.getInstance().get(Calendar.YEAR))	{
					
					// Kenyelmi funkcio, eltaroljuk az elozoleg kijelolt elemnek a poziciojat, hatha a masik evben is ki lehet valasztani
					int prevSelected = (Integer)dateM.getSelectedItem();
					int prevCount = dateM.getItemCount();
					
					while(dateM.getItemCount() > 0)
						dateM.removeItemAt(0);
					
					for (int i = 0; i < 12; i++)
						dateM.addItem(i+1);
					
					if (prevCount == dateM.getItemCount())
						dateM.setSelectedIndex(prevSelected - 1); // 1es offseteles
				}
				else if ((Integer)dateY.getSelectedItem() == Calendar.getInstance().get(Calendar.YEAR))	{
					
					while(dateM.getItemCount() > 0)
						dateM.removeItemAt(0);
					
					initComboBoxWithMonthAfterToday(dateM);
				}
				
				changeRemindDateIfNecessary((Integer)dateY.getSelectedItem(), (Integer)dateM.getSelectedItem(), (Integer)dateD.getSelectedItem(),
						remindY, remindM, remindD);
			}
        });
       
        
        dateM.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				SwingUtilities.invokeLater(
						new Runnable(){
							public void run(){
								Calendar c = new GregorianCalendar((Integer)dateY.getSelectedItem(), (Integer)dateM.getSelectedItem() - 1, 1); // 1es offseteles
								int dayCount = c.getActualMaximum(Calendar.DAY_OF_MONTH);
								while(dateD.getItemCount() > 0)
									dateD.removeItemAt(0);
								
								for (int i = 0; i < dayCount; i++)
									dateD.addItem(i+1);	
								
								changeRemindDateIfNecessary((Integer)dateY.getSelectedItem(), (Integer)dateM.getSelectedItem(), (Integer)dateD.getSelectedItem(),
										remindY, remindM, remindD);
						}
					}
				);
			}
        });
        
        remindY.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if ((Integer)remindY.getSelectedItem() > Calendar.getInstance().get(Calendar.YEAR))	{
					
					// Kenyelmi funkcio, eltaroljuk az elozoleg kijelolt elemnek a poziciojat, hatha a masik evben is ki lehet valasztani
					int prevSelected = (Integer)remindM.getSelectedItem();
					int prevCount = remindM.getItemCount();
					
					while(remindM.getItemCount() > 0)
						remindM.removeItemAt(0);
					
					for (int i = 0; i < 12; i++)
						remindM.addItem(i+1);
					
					if (prevCount == remindM.getItemCount())
						remindM.setSelectedIndex(prevSelected - 1); // 1es offseteles
				}
				else if ((Integer)remindY.getSelectedItem() == Calendar.getInstance().get(Calendar.YEAR))	{
					
					while(remindM.getItemCount() > 0)
						remindM.removeItemAt(0);
					
					initComboBoxWithMonthAfterToday(remindM);
				}
				
			}
        });
       
        
        remindM.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				SwingUtilities.invokeLater(
						new Runnable(){
							public void run(){
								Calendar c = new GregorianCalendar((Integer)remindY.getSelectedItem(), (Integer)remindM.getSelectedItem() - 1, 1); // 1es offseteles
								int dayCount = c.getActualMaximum(Calendar.DAY_OF_MONTH);
								while(remindD.getItemCount() > 0)
									remindD.removeItemAt(0);
								
								for (int i = 0; i < dayCount; i++)
									remindD.addItem(i+1);	
						}
					}
				);
			}
        });
   
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
	
	void changeRemindDateIfNecessary(int year, int month, int day, JComboBox<Integer> y, JComboBox<Integer> m, JComboBox<Integer> d)	{
		
		Calendar date = new GregorianCalendar(year, month, day);
		date.set(Calendar.HOUR_OF_DAY, 0);
		date.set(Calendar.MINUTE, 0);
		date.set(Calendar.SECOND, 0);
		date.set(Calendar.MILLISECOND, 0);
		Calendar rDate = new GregorianCalendar((Integer)y.getSelectedItem(), (Integer)m.getSelectedItem(), (Integer)d.getSelectedItem());
		rDate.set(Calendar.HOUR_OF_DAY, 0);
		rDate.set(Calendar.MINUTE, 0);
		rDate.set(Calendar.SECOND, 0);
		rDate.set(Calendar.MILLISECOND, 0);
		
		if (date.after(rDate))	{
			rDate = date;
			y.setSelectedItem(year);
			m.setSelectedItem(month);
			d.setSelectedItem(day);
		}
	}
	
	Task makeTask(String title, String notes,
			Integer dY, Integer dM, Integer dD,
			Integer rY, Integer rM, Integer rD)
					throws EmptyTitleInTask, DueDateIsInThePast	{
		
		if (title.equals(""))
			throw new EmptyTitleInTask();
		
		Calendar dueDate = Calendar.getInstance();
		Calendar remindDate = Calendar.getInstance();
		
		dueDate.set(dY, dM - 1, dD); //1es offset
		dueDate.set(Calendar.HOUR_OF_DAY, 0);
		dueDate.set(Calendar.MINUTE, 0);
		dueDate.set(Calendar.SECOND, 0);
		dueDate.set(Calendar.MILLISECOND, 0);
		
		
		if (dueDate.before(new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH))))
			throw new DueDateIsInThePast();
		
		remindDate.set(rY, rM - 1, rD); //1es offset
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
		
		try	{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("tasks.dat"));
			data.tasks = (ArrayList<Task>)ois.readObject();
			ois.close();
		}
		catch(Exception e)	{
			System.err.println(e.getMessage());
		}
		
		addWindowListener(new WindowAdapter(){
			
			@Override
			public void windowClosing(WindowEvent e)	{
				try	{
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tasks.dat"));
					oos.writeObject(data.tasks);
					oos.close();
				}
				catch(Exception ex)	{
					System.err.println(ex.getMessage());
				}
			}
		});
		
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
