package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/** 
 * Descr: The frame class for the gadget window, containing the list.
 * @author Max Pihlsröm
 */
public class GadgetWindow extends JFrame {

 	private	JPanel topPanel;
	private	view.List listbox;
    
    	/**
	 * Sets up the gadget window GUI, and creation of the list model.
	 */
	public GadgetWindow() {

		super("Todo Gadget");
                
		topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		getContentPane().add(topPanel);
                topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
                
		listbox = new view.List(TodoWindow.table.tableDataModel);
                JButton checkButton = new JButton("check");
		checkButton.setAction(new control.CheckAction(i18n.Language.getInstance().getString("text.list.check"), listbox));              

                topPanel.add(listbox);
                topPanel.add(checkButton);
                
		this.pack();
		this.setVisible(true);
	}

}
