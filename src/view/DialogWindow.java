package view;

import helper.ValueContainer;

import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JSlider;

//import org.joda.time.DateTime;

import com.michaelbaranov.microba.calendar.DatePicker;

import model.TaskItem;


/** Description of DialogWindow
 * 
 * Provides input fields for adding or editing TaskItems 
 * 
 * @author tony bjorkman
 *
 */


public class DialogWindow extends JDialog {
    
    ValueContainer returnValue;
	
    public class SaveAction extends AbstractAction {

        public SaveAction(String actionName){
            super(actionName);
        }
        
        @Override
        
        public void actionPerformed(ActionEvent arg0) {
            // TODO Auto-generated method stub
            //System.out.println("actionperformed in innerclass");
        	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        	String strDate = dateFormat.format(datePick.getDate());

        	returnValue = new ValueContainer(descrField.getText(),(String) prioBox.getSelectedItem(),(String)catBox.getSelectedItem(),strDate);
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
        	        	
        	System.out.println(window.getSize());
        	
        	returnValue = null;
            window.dispose();
        }
		
    }
	
    private JDialog window; 
    private TaskItem theTask;
    private JTextField dateField;
    private JTextArea descrField;
    private JPanel panel,panelBottom;
    private JLabel descrLabel,dateLabel,catLabel,prioLabel,progressLabel;
    private JButton applyButton,cancelButton;
    private JComboBox prioBox,catBox;
    private DatePicker datePick;
    
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
        setSize(179,276);  //
    	setVisible(true); //this needs to be in the right order, else it wont  
    					//update fields or read fields!
    }
    //need to return multiple values, one way is to put it into a storage object.
    //then i need to create a new class for the storage object and import it into both
    //this class and the class calling this.
    
    public DialogWindow(JFrame frame, Date date){
    	//super(frame,"Add");
        System.out.println("Edit: no argument=Add");

    	setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);

    	window = this;
    	buildGUI();
        try {
			datePick.setDate(date);
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        add(panel);          //
        setSize(179,276);  //
    	setVisible(true); //this needs to be in the right order, else it wont  
    					//update fields or read fields!
    }
    
    public DialogWindow(JFrame frame,ValueContainer vc) {
        //super(frame,"Edit",true);
    	if(vc==null)
    		return;
    	
    	setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);

        window = this;
        System.out.println("Edit: constructur(big) value of desc:"+vc.descr);

        buildGUI();
        descrField.setText(vc.descr);

        //dateField.setText(vc.date);
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        datePick.setDateFormat(dateFormat);
        try {
        	Date date = dateFormat.parse(vc.date);
	        datePick.setDate(date);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        //catBox.addItem(vc.cat);
        catBox.setSelectedItem(vc.cat);
        prioBox.setSelectedItem(vc.prio);
        
        add(panel);          //
        setSize(179,276);  //
    	setVisible(true); //this needs to be in the right order, else it wont  
    					//update fields or read fields!
        
    }
    
    public void buildGUI(){
     
        i18n.Language lang = i18n.Language.getInstance();
        
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panelBottom = new JPanel();
        
        
        descrLabel = new JLabel();
        descrLabel.setText(lang.getString("text.task.description"));
        descrField = new JTextArea();
        descrField.setLineWrap(true);
        
        //Date, change this to more suitable later
        dateLabel = new JLabel(lang.getString("text.task.date"));
        catLabel = new JLabel(lang.getString("text.task.category"));
        prioLabel = new JLabel(lang.getString("text.task.prio"));
        progressLabel = new JLabel(lang.getString("text.task.progress"));

        applyButton = new JButton("apply");
        applyButton.setAction(new SaveAction(lang.getString("text.dialog.apply")));

        cancelButton = new JButton("cancel");
        cancelButton.setAction(new CancelAction(lang.getString("text.dialog.cancel")));
               
        datePick = new DatePicker(); 
        datePick.setShowNoneButton(false);
        
        
        //combobox values to choose from
        String [] priorities = {"-","1","2","3","4"};

        
        String [] categories = {"Home","Downtown","Office","School","Cool"};
        
        prioBox = new JComboBox(priorities);
        
        catBox = new JComboBox(categories);
        
        JSlider progressBar = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        //framesPerSecond.addChangeListener(this);
        
        panel.add(descrLabel);
        panel.add(descrField);
        panel.add(dateLabel);
        panel.add(datePick);
        panel.add(prioLabel);
        panel.add(prioBox);
        panel.add(catLabel);
        panel.add(catBox);
        panel.add(progressLabel);
        panel.add(progressBar);
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