package view;

import helper.FileWrite;
import java.util.ArrayList;
import javax.swing.JList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import model.CheckListModel;
import model.TaskItem;
import model.ImmutableTableModel;

public class List extends JList {

    CheckListModel checkListModel;
    ImmutableTableModel tableModel;

    public List(String listData[]) {
        super(listData);
    }

    public List(ImmutableTableModel tm) {
        tableModel = tm;
        checkListModel = new CheckListModel(tableModel);
        this.setModel(checkListModel);
    }

    /**
     * Delete selected item from the list
     */
    public void removeSelectedItem() {
        int index = this.getSelectedIndex();

        if (index >= 0) {
            TaskItem task = checkListModel.getTaskAt(index);
            tableModel.checkItemInList(task);
        }

    }

}
