package control;

import helper.ValueContainer;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import view.DialogWindow;
import view.Table;

/**
 * AddAction class that extends BaseAction. 
 * @author tony bjorkman
 *
 */

public class AddAction extends BaseAction {
	

	public AddAction(JFrame frame, String text, Table table) {
		super(frame, text, table);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		ValueContainer diaValue = new DialogWindow(frame).getValues();
		
		//need to put these values back into the TaskItem!
		if(diaValue!=null){
			System.out.println("EditAction: Dialog returned desc: "+diaValue.descr);
			table.saveVCinNewTask(diaValue);
		}
		
	}

}
