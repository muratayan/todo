package view;

import java.awt.Component;

import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 * Descr: Adds progtressbar rendering functionality
 *
 * @author tony bjorkman
 *
 */
public class ProgressCellRender extends JProgressBar implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        int progress = 0;

        //checks that the cell value is an integer, then sets the progressbar value to that value.
        if (value instanceof Integer) {
            progress = (int) value;
            setValue(progress);

        }
        return this;
    }
}
