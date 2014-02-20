package control;

import java.awt.event.ActionEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import view.AboutDialog;

public class AboutAction extends BaseAction {

	public AboutAction(JFrame frame, Icon icon, String text) {
		super(frame,icon, text);
		// TODO Auto-generated constructor stub
        
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		new AboutDialog();
	}

}
