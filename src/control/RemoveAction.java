package control;

import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import view.Table;

/**
 * 
 * @author tony bjorkman
 *
 */

public class RemoveAction extends BaseAction {

	public RemoveAction(JFrame frame, String text, Table table) {
		super(frame, text, table);
		// TODO Auto-generated constructor stub
	}
	
	public void actionPerformed(ActionEvent e){
		table.removeSelectedItem();
	}

}
