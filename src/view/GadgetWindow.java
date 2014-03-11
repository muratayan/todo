/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/** Description of TodoWindow
 * 
 * Contains UI components
 * Adds actions to components
 *
 * @author Jarl
 * @author Tony Bj�rkman
 */
public class GadgetWindow extends JFrame {

    	// Instance attributes used in this example
	private	JPanel		topPanel;
	private	JList		listbox;
    
    	/**
	 * GUI components initialisation method
	 */
	public GadgetWindow() {

		super("Todo Gadget");

		setSize( 300, 100 );
		//setBackground( Color.gray );

		// Create a panel to hold all other components
		topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		getContentPane().add( topPanel );

		// Create some items to add to the list
		String	listData[] =
		{
			"Item 1",
			"Item 2",
			"Item 3",
			"Item 4"
		};

		// Create a new listbox control
		listbox = new view.List();
		topPanel.add( listbox, BorderLayout.CENTER );

                //JScrollPane listScroller = new JScrollPane(listbox);
                //listScroller.setPreferredSize(new Dimension(250, 80));
                //listScroller.setAlignmentX(LEFT_ALIGNMENT);
                
                
		this.pack();               // Finalize showing the JFrame
		this.setVisible(true);     // A Window is hidden by default. 
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

}
