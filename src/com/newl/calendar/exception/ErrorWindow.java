package com.newl.calendar.exception;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ErrorWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1272529696684739061L;

	public ErrorWindow(Exception e)	{
		
		super("Error!");
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		
		setPreferredSize(new Dimension(400, 100));
		setMinimumSize(new Dimension(400, 100));
		setVisible(true);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		setLayout(new BorderLayout());
		JLabel errorMsg = new JLabel(e.getMessage());
		errorMsg.setHorizontalAlignment(JLabel.CENTER);
		errorMsg.setVerticalAlignment(JLabel.CENTER);
		errorMsg.setForeground(Color.RED);
		add(errorMsg, BorderLayout.CENTER);
		
		addWindowListener(new WindowListener(){

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				// TODO Auto-generated method stub
				dispose();
				System.out.println("Error window is closing. Disposing.");
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
}
