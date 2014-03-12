package control;

import helper.ValueContainer;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import com.michaelbaranov.microba.calendar.CalendarPane;

import view.DialogWindow;
import view.Table;

/**
 * AddAction class that extends BaseAction.
 *
 * @author tony bjorkman
 *
 */
public class AddAction extends BaseAction {

    private CalendarPane calendar;

    public AddAction(JFrame frame, String text, Table table, CalendarPane calendar) {
        super(frame, text, table);
        this.calendar = calendar;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {

		//will send calendar-date but only if something is selected.
        //
        ValueContainer diaValue;
        System.out.println("" + calendar.getClass());
        if (calendar == null) {
            diaValue = new DialogWindow(frame).getValues();
        } else {
            diaValue = new DialogWindow(frame, calendar.getDate()).getValues();

        }

        //need to put these values back into the TaskItem!
        if (diaValue != null) {
            System.out.println("EditAction: Dialog returned desc: " + diaValue.descr);
            table.saveVCinNewTask(diaValue);
        }

    }

}
