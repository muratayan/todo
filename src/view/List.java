package view;

import javax.swing.JList;
import model.CheckListModel;

public class List extends JList {

    CheckListModel checkListModel;
    
    public List(String listData[]) {
        super(listData);
    }

    public List() {
        checkListModel = new CheckListModel(Table.tableDataModel);
        this.setModel(checkListModel); 
    }
    

}