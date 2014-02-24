package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import view.Table;

/**
 * 
 * @author tony bjorkman
 *
 */

/*
 * ActionClass for when an Edit of TaskItem occurs
 * Opens up a TaskItem in a Dialog to allow editing
 * Uses the Tables function to retrieve and save TaskItem-data
 * values are transfered inside objects called ValueContainers
 */

public abstract class BaseAction extends AbstractAction implements ActionListener {
	
	protected JFrame frame;
        protected Table table;
	
        /**
         * 
         * @param JFrame
         * @param String 
         * @param Table  
         */
    	public BaseAction(JFrame frame,String text){
    		super(text);
    		this.frame = frame;
    		System.out.println("Action created");
    	}

    	public BaseAction(JFrame frame,Icon icon, String text){
    		super(text,icon);
    		this.frame = frame;
    		System.out.println("Action created");
    	}
    	
        public BaseAction(JFrame frame,String text,Table table){
        	super(text);
        	this.table = table;
        	this.frame = frame;
        	System.out.println("Action created");
	}     
        
}
