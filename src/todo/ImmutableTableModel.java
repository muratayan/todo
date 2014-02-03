package todo;

import javax.swing.table.DefaultTableModel;
import java.util.*;

/**
 *  Description: Table model that allows it to be readonly or not
 */
public class ImmutableTableModel extends DefaultTableModel {

    boolean[] immutableColumns; 
  
    public ImmutableTableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
        
        //todo: are the bools set to false by default? 
        immutableColumns = new boolean[columnIdentifiers.size()];
    }  
  
    /**
     * Overridden method to check if the cell should be read only
     */
    public boolean isCellEditable(int row, int column) {
      
        if(immutableColumns[column])
            return false;

        return super.isCellEditable(row, column);
    }

    /**
     * Overridden method to set the value for the current cell
     */
    public void setValueAt(Object value, int row, int column) {
      //if (!immutable)
        super.setValueAt(value, row, column);
    }
    
    public void setColumnImmutable(int i, boolean b) {
        immutableColumns[i] = b;
    }
    
}
