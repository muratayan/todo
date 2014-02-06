package todo;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import model.TaskItem;

import java.util.*;

/**
 *  Description: Table model that allows it to be readonly or not
 */
public class ImmutableTableModel extends AbstractTableModel {

    public List<TaskItem> tasks;
	boolean[] immutableColumns; 
	String[] columnHeaders;
  
    public ImmutableTableModel(List data, String[] columnNames) {
        
    	
    	tasks = data;
        columnHeaders = columnNames;
    	
        //todo: are the bools set to false by default? 
        immutableColumns = new boolean[getColumnCount()];
    }  
    
    public TaskItem getItem(int row){
    	return tasks.get(row);
    }
    
    public void removeItem(int row){
    	tasks.remove(row);
    	fireTableDataChanged();
    }
    
    
    public String getColumnName(int col){
    	return columnHeaders[col];
    }
    
    public int getColumnCount(){
    	return columnHeaders.length;
    }
    
    public int getRowCount(){
    	return tasks.size();
    }

    public Object getValueAt(int row,int column){
    	TaskItem item = null;
    	item=tasks.get(row);
    	
    	switch(column){
    	case 0:
    		return item.getDescription();
    	case 1:
    		return item.getDate();
    	case 2:
    		return item.getCategory();
    	case 3:
    		return item.getPriority();
    	default:
    		return "";
    		
    	}
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
