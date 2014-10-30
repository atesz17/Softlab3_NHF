package com.newl.calendar;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.EventQueue;

public class Program extends JFrame {

	private static final long serialVersionUID = 1L;

	public Program()	{
		initUI();
	}
	
	private void initUI()	{
		
		 try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		setJMenuBar(new MenuBar());
		
		setTitle("Calendar");
		setSize(800, 600);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(EXIT_ON_CLOSE);  
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable()	{
			
			@Override
			public void run()	{
				Program program = new Program();
				program.setVisible(true);
			}
		});

	}

}
