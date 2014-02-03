package todo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Todo {
    
    static JFrame window;
       
    private static void createAndShowGUI() {
        
        window = new JFrame("Todo");
        // Creating a content panel
        //
        JPanel testPanel = new JPanel();
        testPanel.setPreferredSize(new Dimension(200, 100));
        //
        window.getContentPane().add(testPanel);
        testPanel.setLayout(new BorderLayout()); // A BorderLayout is enough for
                                                 // this Demo
        
        JPanel eastPanel = new JPanel();
                
        // Create a JButton to close the application
        JButton closeButton = new JButton("Pop-up");
        testPanel.add(eastPanel, BorderLayout.WEST);
        
        eastPanel.add(closeButton);       // The default layout on a panel is a
                                          // FlowLayout.
        
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(window, "Reminder!");
            }
        });

        Table newContentPane = new Table();
        newContentPane.setOpaque(true); //content panes must be opaque
        window.setContentPane(newContentPane);
        
        window.pack();               // Finalize showing the JFrame
        window.setVisible(true);     // A Window is hidden by default. 
        // Make sure it is visible.

        window.setDefaultCloseOperation(EXIT_ON_CLOSE); // Make sure we can 
        // quit using the 
    }
    
    
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
}
