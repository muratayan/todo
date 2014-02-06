package view;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

public class EditAction extends AbstractAction {
	
	private JFrame frame;
	
	public EditAction(JFrame frame){
		this.frame = frame;
		System.out.println("Action created");
	}

	public EditAction(String string) {
		// TODO Auto-generated constructor stub
		super(string);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		new Edit(frame);
		System.out.println("Action happened");
		this.setEnabled(false);
	}
}
