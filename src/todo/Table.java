package todo;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import model.eventItem;


//test

public class Table extends JPanel {
    private boolean DEBUG = false;
    private JPopupMenu popMenu;
    private Table myTable;
    final JTable table;
    
    public Table() {
        super(new GridLayout(1,0));

        myTable = this;
        
        String[] columnNames = {"Description",
                                "Due date",
                                "Category",
                                "Prio"};

        Object[][] data = {
            {"Buy potatoes", "2014-02-04", "Home", new Integer(1)},
            {"Call dad", "2014-02-05", "Family", new Integer(4)},
            {"Exam studies", "2014-02-06", "School", new Integer(2)},
            {"Water the plants", "2014-02-07", "Home", new Integer(3)}
        };
        
        //just for testing, remove later
        data[0] =new eventItem("Wazzer the plants", "2014-66-07", "Home", new Integer(3)).getAsRow();
        
        
        JMenuItem menuItemAdd = new JMenuItem("Remove event");
        menuItemAdd.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		int r=table.getSelectedRow();
        		System.out.println(""+r);
				ImmutableTableModel dataModel = (ImmutableTableModel) table.getModel();
				
        		dataModel.removeRow(r);
        	}

        });
        
        
        popMenu = new JPopupMenu();
        
        popMenu.add(menuItemAdd);
        

        ImmutableTableModel model = new ImmutableTableModel(data, columnNames);
        model.setColumnImmutable(2, true);
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);
        
        ListSelectionModel selModel = table.getSelectionModel();

        table.addMouseListener(new MouseAdapter(){

			@Override
			public void mouseReleased(MouseEvent arg0) {
				
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


}