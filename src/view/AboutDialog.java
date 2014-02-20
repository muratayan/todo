package view;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutDialog extends JDialog {

	public AboutDialog(){
        JPanel panel = new JPanel();
        
         JLabel descrLabel = new JLabel("about it");
         panel.add(descrLabel);
        add(panel);
		 setSize(179,276);  //
	     setVisible(true);
		
	}
	
}
