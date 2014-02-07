package view;

import helper.ValueContainer;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import control.AddAction;
import control.EditAction;
import control.RemoveAction;
import model.ImmutableTableModel;
import model.TaskItem;


//test

public class Table extends JTable {
    private boolean DEBUG = false;
    private JPopupMenu popMenu;
    private Table myTable;
    private JFrame frame;
    private List<TaskItem> tasks;
    ImmutableTableModel tableDataModel;
    ListSelectionModel tableSelectionModel;
    
    /*
     * Table, holds the selectionModel and Datamodel
     * can get and set selected TaskItems.
     * Stores all taskItems in "tasks"
     */
    
    public Table(TodoWindow frame) {
    	super();
    	this.frame=frame;
    	myTable = this;

        tasks = new ArrayList();
        
        //adds entries. this will be moved and replaces later on by a file that loads.
        tasks.add(new TaskItem("Wazzer the plants","-",  "Home","2014-66-07" ));
        tasks.add(new TaskItem("Call dad","4" , "Family","2014-02-05" ));
        tasks.add(new TaskItem("Exadm studies","2" , "School","2014-02-06" ));
        
        String[] columnNames = {"Description",
                "Prio",
                "Category",
                "Date"};
        
        //ImmutableTableModel model = new ImmutableTableModel(tasks, columnNames);
        tableDataModel = new ImmutableTableModel(tasks, columnNames); 
        
        this.setModel(tableDataModel);        
        
        this.setPreferredScrollableViewportSize(new Dimension(500, 70));
        this.setFillsViewportHeight(true);
        this.setAutoCreateRowSorter(true);
        
        tableSelectionModel = this.getSelectionModel();

        
        //model.setColumnImmutable(2, true);
        
        //Submenu when rightclick
        JMenuItem menuItemAdd = new JMenuItem();
        menuItemAdd.setAction(new AddAction(frame,"Add new event",this));
        
        JMenuItem menuItemRemove = new JMenuItem();
        menuItemRemove.setAction(new RemoveAction(frame,"Remove event",this));
        
        JMenuItem menuItemEdit = new JMenuItem();
        menuItemEdit.setAction(new EditAction(frame,"Edit event",this));
        
        
        
        this.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// Opens a submenu when rightclicking on a valid table row
				if(arg0.getButton()==arg0.BUTTON3){
				JTable clickedTable = (JTable) arg0.getSource();
				int r = clickedTable.rowAtPoint(arg0.getPoint());
					if(r>=0 && r < clickedTable.getRowCount()){
					clickedTable.setRowSelectionInterval(r, r);
					// TODO Auto-generated method stub
					popMenu.show(arg0.getComponent(),arg0.getX(),arg0.getY());
					}
					else
					{
					clickedTable.clearSelection();
					}
				
				}
			}

	
        	
        });

        
        popMenu = new JPopupMenu();
        popMenu.add(menuItemAdd);
        popMenu.add(menuItemRemove);
        popMenu.add(menuItemEdit);

        
        //Create the scroll pane and add the table to it.
     }

    /*
     * Returns the TaskItem as ValueContainer that corresponds to the selected row in the table(datamodel)
     */
   public ValueContainer getSelectedTaskAsVC(){
	  int row = this.convertRowIndexToModel(getSelectedRow());
	  System.out.println("Table: got task on row "+row);
	  return tableDataModel.getItemFromList(row).getRowAsVC();
   }
   //Saves data from a ValueCOntainer-object to the selected TaskItem.
   public void saveSelectedTaskAsVC(ValueContainer vc){
	   int row = this.convertRowIndexToModel(this.getSelectedRow());
	   System.out.println("Table: saved task on row "+row);
	   tableDataModel.getItemFromList(row).setValuesfromVC(vc);
	   this.repaint();
   }
	
	public void saveVCinNewTask(ValueContainer vc) {
		// TODO Auto-generated method stub
		//tableDataModel.
	   System.out.println("Table: saved task in new row ");
	   tableDataModel.addItemToList(vc);
	   //this.add(vc.convertToTaskItem());
	   tableDataModel.fireTableRowsInserted(tableDataModel.getRowCount()-1, tableDataModel.getRowCount()-1);
	   this.repaint();
	   
	   
	   
	   //tableDataModel.notify();
	   System.out.println("");
	}
	    
	public void removeSelectedItem(){
		int row = this.convertRowIndexToModel(this.getSelectedRow());
		tableDataModel.removeItemFromList(row);
		System.out.println("Table: removed row: "+row);
		this.repaint();
	}

}