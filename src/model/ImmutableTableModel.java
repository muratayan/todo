package model;

import helper.ValueContainer;
import javax.swing.table.AbstractTableModel;
import java.util.*;

/**
 * Descr: This class serves as the storage class for the todo list data.
 * The name derives from the functionality to allow some columns to be set to
 * immutable.
 * 
 * @author Max Pilström, Tony Björkman
 */
public class ImmutableTableModel extends AbstractTableModel {

    public List<TaskItem> tasks;
	boolean[] immutableColumns; 
	String[] columnHeaders;

    /**
     * Initializes the table, setting no columns as immutable by default.
     * @param data The initial data of the table (each row being an item).
     * @param columnNames The name of the table columns (attributes of each item).
     */
    public ImmutableTableModel(List<TaskItem> data, String[] columnNames) {
       
    	tasks = data;
        columnHeaders = columnNames;
    	
        //todo: are the bools set to false by default? 
        immutableColumns = new boolean[getColumnCount()];
    }  
    
    public void addItemToList(ValueContainer vc){
    	tasks.add(vc.convertToTaskItem());
    }
    
    
    @Override
    public Class getColumnClass(int columnIndex) {
        System.out.println(""+columnIndex);
    	if (columnIndex == 5)
            return Boolean.class;
        return super.getColumnClass(columnIndex);
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
        case 5:
            return item.getDone();
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
        case 4: {
                int p = Math.max(0, Math.min(100, Integer.parseInt((String)value)));
                item.setProgress(p);
                break;
            }
        case 5:
        	if(item.getDone())
        	item.setDone(false);
        	else
        	item.setDone(true);
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
