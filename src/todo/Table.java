package todo;

import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


//test

public class Table extends JPanel {
    private boolean DEBUG = false;
    
    public Table() {
        super(new GridLayout(1,0));

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

        ImmutableTableModel model = new ImmutableTableModel(data, columnNames);
        model.setColumnImmutable(2, true);
        final JTable table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        table.setAutoCreateRowSorter(true);

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