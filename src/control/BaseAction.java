package control;

import helper.ValueContainer;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

import view.Table;

/**
 * 
 * @author tony bj�rkman
 *
 */

/*
 * ActionClass for when an Edit of TaskItem occurs
 * Opens up a TaskItem in a Dialog to allow editing
 * Uses the Tables function to retrieve and save TaskItem-data
 * values are transfered inside objects called ValueContainers
 */

public abstract class BaseAction extends AbstractAction {
	
	protected JFrame frame;
        protected Table table;
	
        /**
         * 
         * @param JFrame
         * @param String 
         * @param Table  
         */
	public BaseAction(JFrame frame,String text,Table table){
		super(text);
		this.table = table;
		this.frame = frame;
		System.out.println("Action created");
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {

	}
}
