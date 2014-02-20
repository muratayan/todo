package view;

import helper.FileWrite;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import control.BaseAction;

public class ExitAction extends BaseAction {
    Table table;
    
	public ExitAction(JFrame frame,Table table, String text) {
		super(frame, text);
		this.table=table;
		// TODO Auto-generated constructor stub
	}
	
	public void actionPerformed(ActionEvent e){
		
		//open Exit dialog
		int i = new JOptionPane().showConfirmDialog(frame, "Do you really want to exit?");
		if(i==0){
	       new FileWrite().writeXmlFile((ArrayList)table.tasks);
	       System.out.println("ExitAction: Saved to XML!");
		System.exit(0);
		}
	}

}
