package control;

import helper.ValueContainer;

import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import view.DialogWindow;
import view.Table;

/** Description of EditAction
 * 
 * Action that opens a Dialog to edit a TaskItem
 * Invokes method to save changes to the TaskItem
 * 
 * @author tony bjorkman
 *
 */

/*
 * 
 * 
 * ActionClass for when an Edit of TaskItem occurs
 * Opens up a TaskItem in a Dialog to allow editing
 * Uses the Tables function to retrieve and save TaskItem-data
 * values are transfered inside objects called ValueContainers
 */

public class EditAction extends BaseAction {
	
	
	public EditAction(JFrame frame, String text, Table table) {
		super(frame, text, table);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//ValueContainer diaValue = new Edit(frame,"hejh","tv�","tre","fyra").getValue();
		
		//gets value from edit-dialog
		ValueContainer diaValue = new DialogWindow(frame,table.getSelectedTaskAsVC()).getValues();
		
		//need to put these values back into the TaskItem!
		if(diaValue != null){
			System.out.println("EditAction: Dialog returned desc: "+diaValue.descr);
			
			table.saveSelectedTaskAsVC(diaValue);
		}
		//this.setEnabled(false);
	}
}
