package view;

import helper.ValueContainer;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
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
import javax.swing.JTextField;

import model.TaskItem;

import java.awt.FlowLayout;

/** Description of DialogWindow
 * 
 * Provides input fields for adding or editing TaskItems 
 * 
 * @author tony björkman
 *
 */


public class DialogWindow extends JDialog {
    
	ValueContainer returnValue;
	
	public class SaveAction extends AbstractAction{

		public SaveAction(String actionName){
			super(actionName);
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			//System.out.println("actionperformed in innerclass");
			returnValue = new ValueContainer(descrField.getText(),(String) prioBox.getSelectedItem(),(String)catBox.getSelectedItem(),dateField.getText());
			window.dispose();
		}
		
	}
	
	public class CancelAction extends AbstractAction{

		public CancelAction(String actionName){
			super(actionName);
		}
		@Override
		public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
			//System.out.println("actionperformed in innerclass");
			returnValue = null;
			window.dispose();
		}
		
	}
	
	
    private JDialog window; 
    private TaskItem theTask;
    private JTextField descrField,dateField;
    private JPanel panel,panelBottom;
    private JLabel descrLabel,dateLabel,catLabel,prioLabel;
    private JButton applyButton,cancelButton;
    private JComboBox prioBox,catBox;
    
    /*
     * opens a dialogwindow with components that get pre-filled values from TaskItem in
     * constructor to update the data in the TaskItem. The update is not implemented yet.
     */
    
    //no arguments, its a new task!
    public DialogWindow(JFrame frame){
    	//super(frame,"Add");
        System.out.println("Edit: no argument=Add");

    	setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);

    	window = this;
    	buildGUI();
        add(panel);          //
        setSize(300,300);  //
    	setVisible(true); //this needs to be in the right order, else it wont  
    					//update fields or read fields!
    }
    //need to return multiple values, one way is to put it into a storage object.
    //then i need to create a new class for the storage object and import it into both
    //this class and the class calling this.
    
    public DialogWindow(JFrame frame,ValueContainer vc) {
        //super(frame,"Edit",true);
    	if(vc==null)
    		return;
    	
    	setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);

        window = this;
        System.out.println("Edit: constructur(big) value of desc:"+vc.descr);

        buildGUI();
        descrField.setText(vc.descr);
        dateField.setText(vc.date);
        //catBox.addItem(vc.cat);
        catBox.setSelectedItem(vc.cat);
        prioBox.setSelectedItem(vc.prio);
        add(panel);          //
        setSize(300,300);  //
    	setVisible(true); //this needs to be in the right order, else it wont  
    					//update fields or read fields!
        
        
    }
    
    public void buildGUI(){
     
        
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panelBottom = new JPanel();
        
        
        descrLabel = new JLabel();
        descrLabel.setText("Description");
        descrField = new JTextField();
        
        //Date, change this to more suitable later
        dateLabel = new JLabel("Date");
        catLabel = new JLabel("Category");
        prioLabel = new JLabel("Prio");

        applyButton = new JButton("Apply");
        applyButton.setAction(new SaveAction("Apply"));

        cancelButton = new JButton("Cancel");
        cancelButton.setAction(new CancelAction("Cancel"));
        
        //SaveAction is an event where the data from the Dialog
        
        
        
        dateField = new JTextField();
        
        //combobox values to choose from
        String [] priorities = {"-","1","2","3","4"};

        
        String [] categories = {"Home","Downtown","Office","School","Cool"};
        
        prioBox = new JComboBox(priorities);
        
        catBox = new JComboBox(categories);
        
        panel.add(descrLabel);
        panel.add(descrField);
        panel.add(dateLabel);
        panel.add(dateField);
        panel.add(prioLabel);
        panel.add(prioBox);
        panel.add(catLabel);
        panel.add(catBox);
        panelBottom.add(applyButton);
        panelBottom.add(cancelButton);
        panel.add(panelBottom);
    }

    //Returns the values that have been edited in this Dialog
    //Important method in other words.
	public ValueContainer getValues() {
		// TODO Auto-generated method stub
		return returnValue;
	}
    
      
}