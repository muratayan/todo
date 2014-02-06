package todo;

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

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import model.TaskItem;


//test

public class Table extends JTable {
    private boolean DEBUG = false;
    private JPopupMenu popMenu;
    private Table myTable;
    private List<TaskItem> tasks;
    ImmutableTableModel tableDataModel;
    ListSelectionModel tableSelectionModel;
    
    /*
     * Table, holds the selectionModel and Datamodel
     * can get and set selected TaskItems.
     * Stores all taskItems in "tasks"
     */
    
    public Table() {
    	super();
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
        
        //Submenu when rightclick, will remove a TaskItem from table datamodel.
        JMenuItem menuItemAdd = new JMenuItem("Remove event");
        menuItemAdd.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		int r=myTable.getSelectedRow();
        		System.out.println(""+r);
        		tableDataModel.removeItem(r);
        	}

        });

        
        
        
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
        
        //Create the scroll pane and add the table to it.
     }

    /*
     * Returns the TaskItem as ValueContainer that corresponds to the selected row in the table(datamodel)
     */
   public ValueContainer getSelectedTaskAsVC(){
	  int row = this.getSelectedRow();
	  System.out.println("Table: got task on row "+row);
	  return tableDataModel.getItem(row).getRowAsVC();
   }
   //Saves data from a ValueCOntainer-object to the selected TaskItem.
   public void saveSelectedTaskAsVC(ValueContainer vc){
	   int row = this.getSelectedRow();
	   System.out.println("Table: saved task on row "+row);
	   tableDataModel.getItem(row).setValuesfromVC(vc);
   }
    

}