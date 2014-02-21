package model;

import helper.ValueContainer;
import javax.swing.table.AbstractTableModel;
import java.util.*;

/**
 *  Description: Table model that allows it to be readonly or not
 */
public class ImmutableTableModel extends AbstractTableModel {

    public List<TaskItem> tasks;
	boolean[] immutableColumns; 
	String[] columnHeaders;
  
    public ImmutableTableModel(List<TaskItem> data, String[] columnNames) {
       
    	tasks = data;
        columnHeaders = columnNames;
    	
        //todo: are the bools set to false by default? 
        immutableColumns = new boolean[getColumnCount()];
    }  
    
    public void addItemToList(ValueContainer vc){
    	tasks.add(vc.convertToTaskItem());
    }
    
    
    
    public TaskItem getItemFromList(int row){
    	return tasks.get(row);
    }
    
    public void removeItemFromList(int row){
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
    		return item.getPriority();
    	case 2:
    		return item.getCategory();
    	case 3:
    		return item.getDate();
        case 4:
                return item.getProgress();
    	default:
    		return "";
    	}
    }
    
    /**
     * Overridden method to check if the cell should be read only
     */
    @Override
    public boolean isCellEditable(int row, int column) {
      
        return !immutableColumns[column];

        //return super.isCellEditable(row, column);
    }

    /**
     * Overridden method to set the value for the current cell
     */
    @Override
    public void setValueAt(Object value, int row, int column) {
      //if (!immutable)
        TaskItem item = null;
    	item=tasks.get(row);

    	switch(column){
    	case 0:
    		item.setDescription((String)value);
                break;
    	case 1:
    		item.setPriority((String)value);
                break;
    	case 2:
    		item.setCategory((String)value);
                break;
    	case 3:
    		item.setDate((String)value);
                break;
        case 4:
                item.setProgress(Integer.parseInt((String)value));
                break;
    	default:
        }
        
        super.setValueAt(value, row, column);
    	fireTableDataChanged();

    }
    
    public void setColumnImmutable(int i, boolean b) {
        immutableColumns[i] = b;
    }
    
    
}
