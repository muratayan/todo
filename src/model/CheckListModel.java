package model;

import helper.ValueContainer;
import javax.swing.AbstractListModel;
import java.util.*;

/**
 *  Description: A list model that will load check list items
 */
public class CheckListModel extends AbstractListModel {
 
    ImmutableTableModel table;
    
    public CheckListModel(List<TaskItem> data) {
       
    }  

    public CheckListModel(ImmutableTableModel t) {
        table = t;
    }  
    
    @Override
    public int getSize() {
      return table.getRowCount();
      //return 1
    }
    
    @Override
    public Object getElementAt(int i){
        return table.getItemFromList(i).getDescription();
    }
    
}
