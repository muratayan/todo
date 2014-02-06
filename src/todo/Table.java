package todo;

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

public class Table extends JPanel {
    private boolean DEBUG = false;
    private JPopupMenu popMenu;
    private Table myTable;
    private final JTable table;
    private List<TaskItem> tasks;
    
    public Table() {
        super(new GridLayout(1,0));

        myTable = this;
        
        String[] columnNames = {"Description",
                                "Due date",
                                "Category",
                                "Prio"};

     
        
        //just for testing, remove later
        
        //Submenu when rightclick, will remove a TaskItem from table datamodel.
        JMenuItem menuItemAdd = new JMenuItem("Remove event");
        menuItemAdd.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		int r=table.getSelectedRow();
        		System.out.println(""+r);
				ImmutableTableModel dataModel = (ImmutableTableModel) table.getModel();
				
        		dataModel.removeItem(r);
        	}

        });
        
        
        popMenu = new JPopupMenu();
        
        popMenu.add(menuItemAdd);
        
        tasks = new ArrayList();
        
        //adds entries. this will be moved and replaces later on by a file that loads.
        tasks.add(new TaskItem("Wazzer the plants", "2014-66-07", "Home", new Integer(3)));
        tasks.add(new TaskItem("Call dad", "2014-02-05", "Family", new Integer(4)));
        tasks.add(new TaskItem("Exam studies", "2014-02-06", "School", new Integer(2)));
        
        
        //ImmutableTableModel model = new ImmutableTableModel(tasks, columnNames);
        ImmutableTableModel model = new ImmutableTableModel(tasks, columnNames);
        //model.setColumnImmutable(2, true);
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        
        ListSelectionModel selModel = table.getSelectionModel();

        table.addMouseListener(new MouseAdapter(){

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
				/*JTable tableS = (JTable) arg0.getSource();
				ListSelectionModel LSmodel = tableS.getSelectionModel();
				System.out.println(""+ LSmodel.getMinSelectionIndex());
				dataModel.removeRow(LSmodel.getMinSelectionIndex());*/
				}
			}

	
        	
        });
    /*       if (DEBUG) {
            table.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    printDebugData(table);
                }
            });
        }*/

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }

    /*
     * Returns the TaskItem that corresponds to the selected row in the table(datamodel)
     */
   public TaskItem getSelectedTask(){
	   ImmutableTableModel mode = (ImmutableTableModel) table.getModel();
	  int row = table.getSelectedRow();
	  System.out.println("gets tasknr"+row);
	  return mode.getItem(row);
   }
    

}