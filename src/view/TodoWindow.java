/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import todo.Table;

/**
 *
 * @author Jarl
 */
public class TodoWindow {
    
    private JFrame window; 
    public Table taskList;
    
    public TodoWindow() {
        window = new JFrame("Todo");

        // Creating a content panel
        //
        JPanel mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(1024,768));
        mainPanel.setLayout(new BorderLayout());
        
        Container ct = window.getContentPane();
        ct.add(mainPanel);  //add main panel to the 
                            //contentPanepm1
        
        //***********PANELS************
        
        //north panel 
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new BorderLayout());
        mainPanel.add(northPanel, BorderLayout.NORTH);

        //button panel
        JPanel buttonPanel = new JPanel();
        northPanel.add(buttonPanel, BorderLayout.WEST);
        
        //sidebar panel
        JPanel sidePanel = new JPanel();
        sidePanel.setLayout(new BorderLayout());
        mainPanel.add(sidePanel, BorderLayout.EAST);
        
        //********MENU BAR***********
        JMenuBar menu = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenu help = new JMenu("Help");
        
        menu.add(file);
        menu.add(edit);
        menu.add(help);
        
        northPanel.add(menu, BorderLayout.NORTH);
        northPanel.validate();
        
        //*******BUTTONS*******
        
        // test button
                                           
        //***Table*******
        //holds the data-model
        //the reference of table can reach the datamodel.
        //thus its possible to edit the table from this method
        //another option would be to create a separate class and pass this "tasklist"
        //to that new class. To change a TaskItem we also need to get the new input, the
        //new values that the TaskItem will be updated with.
        //taskList = new Table();
        JPanel tablePanel = new JPanel(new GridLayout(1,0));
        Table table = new Table();
        taskList = table;
        tablePanel.add(new JScrollPane(table));
        
        /*
         * Those new values for the TaskItem will be returned from the Dialog class.
         * therefore the same class that calls the Dialog will take part in updating the model.
         * the class that opens up the Dialog would be the Action-class.. 
         * How is the Action class supposed to get a hold of the dataModel?
         * It needs to relay this information to a class that is more suitable.
         * 
         * Action(button pressed) -> Get selected Row data -> Dialog(Row data) -> 
         * put Row data back into TaskItem. 
         * 
         * Class named ModelInterface could keep track of the table and its models.
         * getSelectedRowData(String desc, String cat, String date, String prio)
         * setSelectedRowData(String desc, String cat, String date, String prio)
         *  
         *  This in turn needs to be extracted from the TaskItem that is selected in the table
         *  
         *  
         *  ModelInterface needs to have a Table
         *  Table has a ImmutableTableModel for storing data
         *  TodoWindow has the main-JFrame, main-JPanel,menu, buttons and actions.
         *  Buttons need Actions and a JPanel to be added to so button fits well in TodoWindow.
         *  I dont know if it is possible to send all the Actions created in TodoWindow to 
         *  a Button-class as parameters to a constructor. Perhaps as a List<Action> object.
         *   
         *  TaskItem is at the bottom.
         *  Edit(Dialog) is at the bottom but requires to know from which frame it is called.
         *  
         *  
         *  Buttons can be created in a different class as long as that class is passed the right panels
         *  Actions should be created in the super-class to the controls such that all actions are
         *  available to all controls.
         *  
         *  Either you make a variable public, or else you need to have a plan for connections
         *  between classes. Connections between classes is decided by knowing which classes there will
         *  be and which classes will need to communicate.
         *  
         */
        
        
        Action editAction = new EditAction("Edit");
        
        //some dummy buttons for now
        JButton addButton = new JButton("Add");
        buttonPanel.add(addButton);
        
        JButton editButton = new JButton("Edit");
        buttonPanel.add(editButton);
       
        JButton delButton = new JButton("Delete");
        buttonPanel.add(delButton);
        
        //functionality to open the edit dialog
        //editButton.setAction(editAction);
        editButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Edit edit = new Edit(window,taskList.getSelectedTask());
		        				
			}
        	
        });
                //JOptionPane.showMessageDialog(window, "Reminder!");
           
        
        
        //test table
        taskList.setOpaque(true); //content panes must be opaque
        
        //reserver space dummy
        JLabel reserve = new JLabel("<html>R E S E R V E D <br>FOR <br>CALENDAR</html>", JLabel.LEFT);
        reserve.setOpaque(true);
        sidePanel.add(reserve, BorderLayout.NORTH);
        
        //Month indicator dummy
        JLabel month = new JLabel("<html>February</html>", JLabel.CENTER);
        month.setOpaque(true);
        northPanel.add(month, BorderLayout.EAST);
        
        
        //task list
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        
        window.pack();               // Finalize showing the JFrame
        window.setVisible(true);     // A Window is hidden by default. 
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    
    
}
