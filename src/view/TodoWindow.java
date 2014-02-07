/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import helper.ValueContainer;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import control.AddAction;
import control.EditAction;
import control.RemoveAction;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;



/** Description of TodoWindow
 * 
 * Contains UI components
 * Adds actions to components
 *
 * @author Jarl
 * @author Tony Björkman
 */
public class TodoWindow extends JFrame {
    
    private JFrame window; 
    public Table table;
    private JPanel mainPanel,tablePanel,northPanel,buttonPanel,sidePanel,statusBar;
    private JMenuBar menu;
    private JButton addButton,delButton,editButton;
    public Action editAction,addAction,removeAction;
    private JLabel month,reserve,statusBarLabel;
    
    public TodoWindow() {
    	
        super("Todo System v0.0");
        DateTime today = new DateTime();
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy/MMM/dd");
        
        System.out.println("Tid:"+fmt.print(today));
        window = this;
        
        //spawner methods builds the GUI piece by piece
        spawnPanels();
        spawnTable();
        spawnStatusBar();
        spawnActions();
        spawnButtons();
        spawnLabels();
        spawnMenu();
        
        this.pack();               // Finalize showing the JFrame
        this.setVisible(true);     // A Window is hidden by default. 
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
  
    }
    
    class ClockLabel extends JLabel implements ActionListener {

    	DateTimeFormatter format;
    	
  	  public ClockLabel() {
  	    super("" + new DateTime());
        format = DateTimeFormat.forPattern("yyyy MMM dd hh:mm:ss");
  	    Timer t = new Timer(999, this);
  	    t.start();
  	  }

  	  public void actionPerformed(ActionEvent ae) {
  	    setText(format.print(new DateTime()));
  	  }
  	}
    
    public void spawnPanels(){
        
        
        
        // Creating a content panel
        //
        mainPanel = new JPanel();
        mainPanel.setPreferredSize(new Dimension(1024,768));
        mainPanel.setLayout(new BorderLayout());
        
        Container ct = window.getContentPane();
        ct.add(mainPanel);  //add main panel to the 
                            //contentPanepm1
        
        //***********PANELS************
        
        //north panel 
        northPanel = new JPanel();
        northPanel.setLayout(new BorderLayout());
        mainPanel.add(northPanel, BorderLayout.NORTH);

        //button panel
        buttonPanel = new JPanel();
        northPanel.add(buttonPanel, BorderLayout.WEST);
        
        //sidebar panel
        sidePanel = new JPanel();
        sidePanel.setLayout(new BorderLayout());
        mainPanel.add(sidePanel, BorderLayout.EAST);
        
        tablePanel = new JPanel(new GridLayout(1,0));
        //taskList.setOpaque(true); //content panes must be opaque
        
    }
    
    public void spawnMenu(){
        
        //********MENU BAR***********
        menu = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenu help = new JMenu("Help");
        
        menu.add(file);
        menu.add(edit);
        menu.add(help);
        
        northPanel.add(menu, BorderLayout.NORTH);
        northPanel.validate();
        
    }
    
    public void spawnStatusBar(){
    	statusBarLabel = new ClockLabel();
        statusBarLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
    	//statusBar.add(statusBarLabel);
    	mainPanel.add(statusBarLabel, BorderLayout.SOUTH);
    	
    }
    
    public void spawnTable(){
        
        //*******BUTTONS*******
        
        // test button
                                           
        //***Table*******
        //holds the data-model
        //the reference of table can reach the datamodel.
        //
        table = new Table(this);
        tablePanel.add(new JScrollPane(table));
        mainPanel.add(tablePanel, BorderLayout.CENTER);
        
    }
    
    
        
    public void spawnActions(){    
        editAction = new EditAction(this,"Edit",table);
        addAction = new AddAction(this,"Add",table);
        removeAction = new RemoveAction(this,"Remove",table);
    }
    
    public void spawnButtons(){
    	
        addButton = new JButton("Add");
        buttonPanel.add(addButton);
        
        editButton = new JButton("Edit");
        buttonPanel.add(editButton);
       
        delButton = new JButton("Delete");
        buttonPanel.add(delButton);
        
        //functionality to open the edit dialog
        //uses the action-class editAction
        //which gets values of selected TaskItem and
        //passes it to dialog Edit which then returns updated values
        //and EditAction passes it to function in Table that saves it to selected TaskItem
        editButton.setAction(editAction);
        addButton.setAction(addAction);
        delButton.setAction(removeAction);
    }
    
    public void spawnLabels(){
        
        
        //test table
        
        //reserver space dummy
        reserve = new JLabel("<html>R E S E R V E D <br>FOR <br>CALENDAR</html>", JLabel.LEFT);
        reserve.setOpaque(true);
        sidePanel.add(reserve, BorderLayout.NORTH);
        
        //Month indicator dummy
        month = new JLabel("<html>February</html>", JLabel.CENTER);
        month.setOpaque(true);
        northPanel.add(month, BorderLayout.EAST);
        
        
        //task list
        
          }
    
    
    
}
