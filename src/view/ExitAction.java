package view;

import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import control.BaseAction;

public class ExitAction extends BaseAction {

	public ExitAction(JFrame frame, String text) {
		super(frame, text);
		// TODO Auto-generated constructor stub
	}
	
	public void actionPerformed(ActionEvent e){
		
		//open Exit dialog
		int i = new JOptionPane().showConfirmDialog(frame, "Do you really want to exit?");
		if(i==0){
		System.exit(0);
		}
	}

}
