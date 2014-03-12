package control;

import helper.ValueContainer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import view.DialogWindow;
import view.Table;

/**
 * This is the action used for checking an item in the list.
 *
 * @author Max Pihlström
 */
public class CheckAction extends AbstractAction implements ActionListener {

    view.List list;

    public CheckAction(String text, view.List l) {
        super(text);
        list = l;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        list.removeSelectedItem();
    }
}
