package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import todo.Table;

import javax.swing.JTextField;

import model.TaskItem;

import java.awt.FlowLayout;

public class Edit {
    
    private JDialog window; 
    
    /*
     * opens a dialogwindow with components that get pre-filled values from TaskItem in
     * constructor to update the data in the TaskItem. The update is not implemented yet.
     */
    public Edit(JFrame frame,TaskItem task) {
        window = new JDialog(frame,"Edit");
     
        
        JPanel descrPanel = new JPanel();
        descrPanel.setLayout(new BoxLayout(descrPanel,BoxLayout.Y_AXIS));
        
        JLabel descrLabel = new JLabel();
        descrLabel.setText("Description");
        JTextField descrField = new JTextField(task.getDescription());
        
        //Date, change this to more suitable later
        JLabel dateLabel = new JLabel("Date");
        JLabel catLabel = new JLabel("Category");
        JLabel prioLabel = new JLabel("Prio");

        
        JTextField dateField = new JTextField(task.getDate());
        
        
        String [] priorities = {"-","1","2","3","4"};

        
        String [] categories = {"Home","Downtown","Office","School","Cool"};
        
        JComboBox prioBox = new JComboBox(priorities);
        
        JComboBox catBox = new JComboBox(categories);
        
        
        
        descrPanel.add(descrLabel);
        descrPanel.add(descrField);
        descrPanel.add(dateLabel);
        descrPanel.add(dateField);
        descrPanel.add(prioLabel);
        descrPanel.add(prioBox);
        descrPanel.add(catLabel);
        descrPanel.add(catBox);
        
        window.add(descrPanel);
        window.setSize(300,200);
        window.setVisible(true); 

       // window.setLayout(new FlowLayout());
    }
      
}