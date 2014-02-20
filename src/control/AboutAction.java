package control;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import view.AboutDialog;

public class AboutAction extends BaseAction {

	public AboutAction(JFrame frame, String text) {
		super(frame, text);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new AboutDialog();
	}

}
