package view;

import helper.ValueContainer;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

import todo.Table;

/*
 * ActionClass for when an Edit of TaskItem occurs
 * Opens up a TaskItem in a Dialog to allow editing
 * Uses the Tables function to retrieve and save TaskItem-data
 * values are transfered inside objects called ValueContainers
 */

public class EditAction extends AbstractAction {
	
	private JFrame frame;
	private Table table;
	
	public EditAction(JFrame frame,String text,Table table){
		super(text);
		this.table = table;
		this.frame = frame;
		System.out.println("Action created");
	}

	public EditAction(String string) {
		// TODO Auto-generated constructor stub
		super(string);
		this.table=table;
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//ValueContainer diaValue = new Edit(frame,"hejh","två","tre","fyra").getValue();
		
		//gets value from edit-dialog
		ValueContainer diaValue = new Edit(frame,table.getSelectedTaskAsVC()).getValues();
		
		System.out.println("EditAction: Dialog returned desc: "+diaValue.descr);
		//need to put these values back into the TaskItem!
		table.saveSelectedTaskAsVC(diaValue);
		//this.setEnabled(false);
	}
}
