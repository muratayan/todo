package view;

import helper.FileWrite;
import helper.ValueContainer;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;





import control.AddAction;
import control.EditAction;
import control.RemoveAction;
import model.ImmutableTableModel;
import model.TaskItem;


/**
 * Table class that holds list of tasks
 * 
 * @author Jarl
 */

public class Table extends JTable implements TableModelListener {
    private boolean DEBUG = false;
    private JPopupMenu popMenu;
    private Table myTable;
    private JFrame frame;
    public List<TaskItem> tasks;
    ImmutableTableModel tableDataModel;
    ListSelectionModel tableSelectionModel;
     
    
    
    /**
     * Table, holds the selectionModel and Datamodel
     * can get and set selected TaskItems.
     * Stores all taskItems in "tasks"
     * 
     * @param frame 
     */
     public Table(final TodoWindow frame) {
    	super();
    	this.frame=frame;
    	myTable = this;

    	//XML file read/write object
        FileWrite f = new FileWrite();

        tasks = f.readXmlFile(); //get those tasks!


        i18n.Language lang = i18n.Language.getInstance();
        
        String[] columnNames = {
            lang.getString("text.task.description"),
            lang.getString("text.task.prio"),
            lang.getString("text.task.category"),
            lang.getString("text.task.date"),
            lang.getString("text.task.progress"),
            lang.getString("text.task.done")
            };
        
        
         tableDataModel = new ImmutableTableModel(tasks, columnNames); 
         //Add listener to save, this will be replaced with a dedicated action later?
        tableDataModel.addTableModelListener(new TableModelListener(){

        	
        	
			@Override
			public void tableChanged(TableModelEvent e) {
				// TODO Auto-generated method stub
		         System.out.println("Table: Saving to database");
		         new FileWrite().writeXmlFile((ArrayList)tasks);			
		         }
        	
        });
        
        this.setModel(tableDataModel);           
        this.setPreferredScrollableViewportSize(new Dimension(500, 70));
        this.setFillsViewportHeight(true);
        this.setAutoCreateRowSorter(true);
        
        this.getColumn(lang.getString("text.task.progress")).setCellRenderer(new ProgressCellRender());

        
        tableSelectionModel = this.getSelectionModel();
        

        //set columns to be immutable.
        tableDataModel.setColumnImmutable(2, true);
        tableDataModel.setColumnImmutable(3, true);
        tableDataModel.setColumnImmutable(5, false);

        
        //Submenu when rightclick
        JMenuItem menuItemAdd = new JMenuItem();
        menuItemAdd.setAction(new AddAction(frame,"Add new event",this,frame.getCalendar()));
        
        JMenuItem menuItemRemove = new JMenuItem();
        menuItemRemove.setAction(new RemoveAction(frame,"Remove event",this));
        
        JMenuItem menuItemEdit = new JMenuItem();
        menuItemEdit.setAction(new EditAction(frame,"Edit event",this));
     
        //Add actionlistener for handling submenu depending on row selected
        this.addMouseListener(new MouseAdapter(){
            
            @Override
            public void mouseReleased(MouseEvent arg0) {
                // Opens a submenu when rightclicking on a valid table row
                if(arg0.getButton()==arg0.BUTTON3){
                    JTable clickedTable = (JTable) arg0.getSource();
                    int r = clickedTable.rowAtPoint(arg0.getPoint());
                    if(r>=0 && r < clickedTable.getRowCount()) {
                        clickedTable.setRowSelectionInterval(r, r);
                        // TODO Auto-generated method stub
                        popMenu.show(arg0.getComponent(),arg0.getX(),arg0.getY());
                    }
                    else {
                        frame.editAction.setEnabled(false);
                        frame.removeAction.setEnabled(false);
                        clickedTable.clearSelection();
                    }	
                }
            }
        });

        popMenu = new JPopupMenu();
        popMenu.add(menuItemAdd);
        popMenu.add(menuItemRemove);
        popMenu.add(menuItemEdit);
        
        
     
        
     }
       
     
    /*
     * Returns the TaskItem as ValueContainer that corresponds to the selected row in the table(datamodel)
     */
     public ValueContainer getSelectedTaskAsVC(){
	   
        if(isRowSelected()){
            int row = this.convertRowIndexToModel(getSelectedRow());
            System.out.println("Table: got task on row "+row);
            return tableDataModel.getItemFromList(row).getRowAsVC();
        }
        else {
            return null;
        }
	   
    }
     
     //Saves data from a ValueCOntainer-object to the selected TaskItem.
     public void saveSelectedTaskAsVC(ValueContainer vc){
        int row = this.convertRowIndexToModel(this.getSelectedRow());
        System.out.println("Table: saved task on row "+row);
        tableDataModel.getItemFromList(row).setValuesfromVC(vc);
        this.repaint();
     }
	
     public void saveVCinNewTask(ValueContainer vc) {
        //tableDataModel.
        System.out.println("Table: saved task in new row ");
        tableDataModel.addItemToList(vc);
        //tells the table that a row in the model has been inserted in the end. -1 to adjust it from 1..3 to 0..2 index.
        tableDataModel.fireTableRowsInserted(tableDataModel.getRowCount()-1, tableDataModel.getRowCount()-1);       
        
        this.repaint();
    }
	
     
 
    /**
     * Retrieve flag whether the table row is selected
     * 
     * @return boolean  
     */
    public boolean isRowSelected(){
        int row = this.getSelectedRow();
        if(row == -1){
            System.out.println("Table: no row selected");
            return false;
        }
        else {
            return true;
        }
    }
	
    /**
     * Delete selected item from the list
     */
    public void removeSelectedItem(){
        if(isRowSelected()){
            int row = this.convertRowIndexToModel(this.getSelectedRow());
            tableDataModel.removeItemFromList(row);
            System.out.println("Table: removed row: "+row);
            this.repaint(); //update table
        }
		
    }

}