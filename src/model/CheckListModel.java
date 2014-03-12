package model;

import helper.ValueContainer;
import javax.swing.AbstractListModel;
import java.util.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 * Descr: A list model that displays check list items. The data is retrieved
 * directly from the table model.
 *
 * @author Max Pihlström
 */
public class CheckListModel extends AbstractListModel implements TableModelListener {

    private ImmutableTableModel table;
    private List<TaskItem> checklist;

    public CheckListModel(ImmutableTableModel t) {
        checklist = new ArrayList<TaskItem>();
        table = t;
        table.addTableModelListener(this);
        constructList();
    }

    /**
     * Rebuilds the list, by scanning the the status of the table.
     */
    private void constructList() {
        checklist.clear();
        for (TaskItem i : table.tasks) {
            if (!i.getDone()) {
                checklist.add(i);
            }
        }
    }

    /**
     * Listening method, for changes in the table. Calls for a rebuilding of the
     * list.
     */
    @Override
    public void tableChanged(TableModelEvent e) {
        constructList();
        fireContentsChanged(this, 0, table.getRowCount() - 1);
    }

    @Override
    public int getSize() {
        return checklist.size();
    }

    @Override
    public Object getElementAt(int i) {
        return checklist.get(i).getDescription();
    }

    public TaskItem getTaskAt(int i) {
        return checklist.get(i);
    }

}
