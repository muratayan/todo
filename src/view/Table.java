package view;

import helper.FileWrite;
import helper.ValueContainer;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

import control.AddAction;
import control.EditAction;
import control.RemoveAction;
import model.ImmutableTableModel;
import model.TaskItem;

/**
 * Table class that holds list of tasks
 *
 * @author Aleksejs Udris, Max Pihlström
 */
public class Table extends JTable {

    private boolean DEBUG = false;
    private JPopupMenu popMenu;
    private Table myTable;
    private JFrame frame;
    public List<TaskItem> tasks;
    public ImmutableTableModel tableDataModel;
    ListSelectionModel tableSelectionModel;

    /**
     * Descr: listens to changes and saves the table to external file.
     */
    class ModelListener implements TableModelListener {

        @Override
        public void tableChanged(TableModelEvent e) {
            System.out.println("Table: Saving to database");
            new FileWrite().writeXmlFile((ArrayList) tasks);
        }
    };

    /**
     * Table, holds the selectionModel and Datamodel can get and set selected
     * TaskItems. Stores all taskItems in "tasks"
     *
     * @param frame
     */
    public Table(final TodoWindow frame) {
        super();
        this.frame = frame;
        myTable = this;

        //XML file read/write object
        FileWrite f = new FileWrite();

        tasks = f.readXmlFile(); //get those tasks!

        i18n.Language lang = i18n.Language.getInstance();

        String[] columnNames = {
            lang.getString("text.task.description"),
            lang.getString("text.task.prio"),
            lang.getString("text.task.category"),
            lang.getString("text.task.date"),
            lang.getString("text.task.progress"),
            lang.getString("text.task.done")
        };

        tableDataModel = new ImmutableTableModel(tasks, columnNames);

        tableDataModel.addTableModelListener(new ModelListener());

        this.setModel(tableDataModel);
        this.setPreferredScrollableViewportSize(new Dimension(500, 70));
        this.setFillsViewportHeight(true);
        this.setAutoCreateRowSorter(true);

        //Make the progress column render as progressbar instead of ordinary cell
        this.getColumn(lang.getString("text.task.progress")).setCellRenderer(new ProgressCellRender());

        tableSelectionModel = this.getSelectionModel();

        //set columns to be immutable.
        tableDataModel.setColumnImmutable(2, true);
        tableDataModel.setColumnImmutable(3, true);
        tableDataModel.setColumnImmutable(5, false);

        //Submenu when rightclick
        popMenu = new JPopupMenu();

        //Add actionlistener for handling submenu depending on row selected
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent arg0) {
                // Opens a submenu when rightclicking on a valid table row
                if (arg0.getButton() == arg0.BUTTON3) {
                    JTable clickedTable = (JTable) arg0.getSource();
                    int r = clickedTable.rowAtPoint(arg0.getPoint());
                    if (r >= 0 && r < clickedTable.getRowCount()) {
                        clickedTable.setRowSelectionInterval(r, r);
                        // TODO Auto-generated method stub
                        popMenu.show(arg0.getComponent(), arg0.getX(), arg0.getY());
                    } else {
                        control.GlobalActions.editAction.setEnabled(false);
                        control.GlobalActions.removeAction.setEnabled(false);
                        clickedTable.clearSelection();
                    }
                }
            }
        });
    }

    /**
     * These are separated from the constructor since the actions need an
     * instantiated table.
     */
    public void addMenuActions() {
        JMenuItem menuItemAdd = new JMenuItem();
        menuItemAdd.setAction(control.GlobalActions.addAction);

        JMenuItem menuItemRemove = new JMenuItem();
        menuItemRemove.setAction(control.GlobalActions.removeAction);

        JMenuItem menuItemEdit = new JMenuItem();
        menuItemEdit.setAction(control.GlobalActions.editAction);

        popMenu.add(menuItemAdd);
        popMenu.add(menuItemEdit);
        popMenu.add(menuItemRemove);
    }

    /*
     * Returns the TaskItem as ValueContainer that corresponds to the selected row in the table(datamodel)
     */
    public ValueContainer getSelectedTaskAsVC() {

        if (isRowSelected()) {
            int row = this.convertRowIndexToModel(getSelectedRow());
            System.out.println("Table: got task on row " + row);
            return tableDataModel.getItemFromList(row).getRowAsVC();
        } else {
            return null;
        }

    }

    //Saves data from a ValueCOntainer-object to the selected TaskItem.
    public void saveSelectedTaskAsVC(ValueContainer vc) {
        int row = this.convertRowIndexToModel(this.getSelectedRow());
        System.out.println("Table: saved task on row " + row);
        tableDataModel.setItem(new TaskItem(vc), row);
        //this.repaint();
    }

    public void saveVCinNewTask(ValueContainer vc) {
        System.out.println("Table: saved task in new row ");
        tableDataModel.addItemToList(vc);
        //tells the table that a row in the model has been inserted in the end. -1 to adjust it from 1..3 to 0..2 index.
        //tableDataModel.fireTableRowsInserted(tableDataModel.getRowCount()-1, tableDataModel.getRowCount()-1);       
        //this.repaint();
    }

    /**
     * Retrieve flag whether the table row is selected
     *
     * @return boolean
     */
    public boolean isRowSelected() {
        int row = this.getSelectedRow();
        if (row == -1) {
            System.out.println("Table: no row selected");
            return false;
        } else {
            return true;
        }
    }

    /**
     * Delete selected item from the list
     */
    public void removeSelectedItem() {
        if (isRowSelected()) {
            int row = this.convertRowIndexToModel(this.getSelectedRow());
            tableDataModel.removeItemFromList(row);
            System.out.println("Table: removed row: " + row);
            this.repaint(); //update table
        }

    }

}
