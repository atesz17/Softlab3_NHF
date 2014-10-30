package com.newl.calendar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;
	
	public MenuBar()	{
		super();
		
		JMenu fileM = new JMenu("File");
		fileM.setMnemonic(KeyEvent.VK_F);
		
			/*
			 *  OPEN...
			 */
		
	    	JMenuItem openMI = new JMenuItem("Open...");
			openMI.setMnemonic(KeyEvent.VK_O);
	        openMI.setToolTipText("Open file...");
	        
	        openMI.addActionListener(new ActionListener()	{
	        	@Override
	            public void actionPerformed(ActionEvent event) {
	               
	            }
	        });
	        
	        fileM.add(openMI);
		
	        /*
			 *  EXIT
			 */
	        
			JMenuItem exitMI = new JMenuItem("Exit");
			exitMI.setMnemonic(KeyEvent.VK_E);
	        exitMI.setToolTipText("Exit calendar");
	        
	        exitMI.addActionListener(new ActionListener()	{
	        	@Override
	            public void actionPerformed(ActionEvent event) {
	                System.exit(0);
	            }
	        });
	        
	        fileM.add(exitMI);
	        
	        
        this.add(fileM);
	}
}
