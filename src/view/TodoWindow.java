/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
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
    
    public TodoWindow() {
        window = new JFrame("Todo");
    }
    
    public void createAndShowGUI() {
        
        
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
        JButton popupButton = new JButton("Pop-up");
        buttonPanel.add(popupButton);       
                                           
        
        popupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(window, "Reminder!");
            }
        });
        
        //some dummy buttons for now
        JButton addButton = new JButton("Add");
        buttonPanel.add(addButton);
        
        JButton editButton = new JButton("Edit");
        buttonPanel.add(editButton);
       
        JButton delButton = new JButton("Delete");
        buttonPanel.add(delButton);
        
        //test table
        Table taskList = new Table();
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
        mainPanel.add(taskList, BorderLayout.CENTER);
        
        window.pack();               // Finalize showing the JFrame
        window.setVisible(true);     // A Window is hidden by default. 
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    
    
}
