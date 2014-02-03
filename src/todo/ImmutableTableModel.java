package todo;

import javax.swing.table.DefaultTableModel;


/**
 *  Description: Table model that allows it to be readonly or not
 */
public class ImmutableTableModel extends DefaultTableModel {

  protected boolean immutable;

  
   public ImmutableTableModel(Object[][] data, Object[] columnNames) {
       super(data, columnNames);
   }  
  
  /**
   * Method to set the Table to read only or not
   *
   * @param bReadOnly True if readonly, False if not
   */
  public void setReadOnly(boolean bReadOnly) {
    immutable = bReadOnly;
  }

  /**
   * Overridden method to check if the cell should be read only
   *
   * @return True if readonly, false otherwise
   */
  public boolean isCellEditable(int row, int column) {

    if (immutable)
      return false;

    return super.isCellEditable(row, column);
  }

  /**
   * Overridden method to set the value for the current cell
   */
  public void setValueAt(Object value, int row, int column) {
    if (!immutable)
      super.setValueAt(value, row, column);
  }
}
