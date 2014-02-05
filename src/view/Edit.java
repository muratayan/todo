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
import javax.swing.JTextField;
import java.awt.FlowLayout;

public class Edit {
    
    private JFrame window; 
    
    public Edit() {
        window = new JFrame("Edit");
     
        JPanel descrPanel = new JPanel();
        descrPanel.setLayout(new BorderLayout());
        
        JLabel descrLabel = new JLabel();
        descrLabel.setText("Description");
        JTextField descrField = new JTextField();
        
        descrPanel.add(descrLabel, BorderLayout.NORTH);
        descrPanel.add(descrField, BorderLayout.SOUTH);
        window.add(descrPanel, BorderLayout.NORTH);
        window.setSize(400,400);
        window.setVisible(true); 

       // window.setLayout(new FlowLayout());
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
      
}