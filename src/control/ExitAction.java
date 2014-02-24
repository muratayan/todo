package control;

import helper.FileWrite;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import view.Table;
import view.TodoWindow;

/**
 * Action class for closing the program.
 * @author Jarl
 */
public class ExitAction extends BaseAction {
    Table table;
    private TodoWindow frame;
    
	public ExitAction(TodoWindow frame,Table table, String text) {
		super(frame, text);
		this.table=table;
		this.frame=frame;
		// TODO Auto-generated constructor stub
	}
	
	public void actionPerformed(ActionEvent e){
		
		//open Exit dialog
		int i = new JOptionPane().showConfirmDialog(frame, "Do you really want to exit?");
		if(i==0){
	       
                    frame.prop.save();

                    System.out.println("ExitAction: Settings saved to XML");
                    System.exit(0);
		}
	}

}
