/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import helper.FileWrite;
import helper.ValueContainer;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;

import static javax.swing.JFrame.EXIT_ON_CLOSE;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.michaelbaranov.microba.calendar.CalendarPane;

import control.AboutAction;
import control.AddAction;
import control.EditAction;
import control.ExitAction;
import control.LanguageAction;
import control.RemoveAction;
import control.XmlProp;

import java.awt.Event;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.swing.JComponent;
import javax.swing.KeyStroke;

//import org.joda.time.*;
//import org.joda.time.format.DateTimeFormat;
//import org.joda.time.format.DateTimeFormatter;
/**
 * Description of TodoWindow
 *
 * Contains UI components Adds actions to components
 *
 * @author Jarl
 * @author Tony Bj�rkman
 */
public class TodoWindow extends JFrame {

    private JFrame window;
    public static Table table;
    private JPanel mainPanel, tablePanel, northPanel, buttonPanel, sidePanel, statusBar;
    private JMenuBar menu;
    private JButton addButton, delButton, editButton;
    private JLabel month, reserve, statusBarLabel;
    private CalendarPane calendar;
    private GridBagConstraints c;
    public i18n.Language lang;
    public XmlProp prop;

    /**
     * GUI components initialisation method
     */
    public TodoWindow() {

        super("Todo System v0.1");
        //        DateTime today = new DateTime();
        //        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy/MMM/dd");
        //        System.out.println("Tid:"+fmt.print(today));

        window = this;

        //set language
        lang = i18n.Language.getInstance();

        prop = new XmlProp(window); //the object that holds frame settings

        prop.load(); //load the frame settings, position and size

        //spawner methods builds the GUI piece by piece
        spawnPanels();
        spawnTable();
        spawnCalender();

        spawnActions();

        //a little bit of an awkward solution. the table has to be instantiated
        //before the actions can be added since the actions depend on the table instance.
        table.addMenuActions();

        spawnButtons();
        spawnMenu();
        spawnStatusBar(); //Disabled until the question of org.joda.* api is resolved

        this.pack();               // Finalize showing the JFrame
        this.setVisible(true);     // A Window is hidden by default. 
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    /*
     * Disabled until the question about the org.joda.* api is resolved
     * 
     class ClockLabel extends JLabel implements ActionListener {

     DateTimeFormatter format;

     public ClockLabel() {
     super("" + new DateTime());
     format = DateTimeFormat.forPattern("yyyy MMM dd hh:mm:ss");
     Timer t = new Timer(999, this);
     t.start();
     }

     public void actionPerformed(ActionEvent ae) {
     setText(format.print(new DateTime()));
     }
     }
     */
    /**
     * Creates the big calender object visible in GUI
     */
    private void spawnCalender() {
        // TODO Auto-generated method stub

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.anchor = GridBagConstraints.NORTH;
        c.weightx = 0.0;
        c.weighty = 0.0;
        c.insets = new Insets(0, 10, 0, 10);
        c.gridy = 1;
        c.gridx = 3;
        //c.anchor =GridBagConstraints.NORTH;
        CalendarPane cal = new CalendarPane();
        cal.setLocale(i18n.Language.getInstance().getLocale());
        cal.setShowNoneButton(false);
        setCalendar(cal);
        try {
            cal.setDate(new Date());
        } catch (PropertyVetoException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        mainPanel.add(getCalendar(), c);

    }

    public void spawnPanels() {
        // Creating a content panel
        //
        mainPanel = new JPanel();
        //mainPanel.setPreferredSize(new Dimension(700,400));
        mainPanel.setLayout(new GridBagLayout());
        c = new GridBagConstraints();

        Container ct = window.getContentPane();
        ct.add(mainPanel);  //add main panel to the contentPanepm1

        tablePanel = new JPanel(new GridLayout(1, 0));

    }

    /**
     * Draw the menu bar
     */
    public void spawnMenu() {

        //********MENU BAR***********
        menu = new JMenuBar();
        JMenu file = new JMenu(lang.getString("text.file"));
        JMenu edit = new JMenu(lang.getString("text.edit"));
        JMenu help = new JMenu(lang.getString("text.help"));
        JMenu language = new JMenu(lang.getString("text.language"));

        JMenuItem exit = new JMenuItem(lang.getString("text.exit"));
        exit.setAction(new ExitAction(this, table, lang.getString("text.exit")));
        file.add(exit);//add exit submenu to File menu

        JMenuItem add = new JMenuItem("Add item");
        //add.setAction(new AddAction(window,"Add item",table,calendar));
        add.setAction(control.GlobalActions.addAction);
        edit.add(add);
        java.net.URL imageURL = getClass().getResource("/About24.gif");
        System.out.println("url: " + imageURL); //imageURL is printing correctly in console
        ImageIcon icon = new ImageIcon(getClass().getResource("/About24.gif"));
        ImageIcon icon2 = new ImageIcon(getClass().getResource("/About24.gif"));
        ImageIcon icon3 = new ImageIcon(getClass().getResource("/About24.gif"));

        JMenuItem about = new JMenuItem("About");
        about.setAction(new AboutAction(window, icon, "About"));
        help.add(about);

        JMenuItem engLang = new JMenuItem("engLang");
        engLang.setAction(new LanguageAction(window, icon2, "English"));
        language.add(engLang);

        JMenuItem svLang = new JMenuItem("svLang");
        svLang.setAction(new LanguageAction(window, icon3, "Svenska"));
        language.add(svLang);

        menu.add(file);
        menu.add(edit);
        menu.add(help);
        menu.add(language);

        setJMenuBar(menu);
    }

    /*
     * Create the status bar.
     * */
    public void spawnStatusBar() {

        c.insets = new Insets(0, 1, 0, 1);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.weightx = 1.0;
        c.weighty = 0.0;
        c.gridy = 2;
        c.gridx = 0;
        c.anchor = GridBagConstraints.NORTH;

        //statusBarLabel = new ClockLabel();
        statusBarLabel = new JLabel(lang.getString("text.statusbar"));
        statusBarLabel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
        //statusBar.add(statusBarLabel);

        mainPanel.add(statusBarLabel, c);

    }

    /**
     * Add the table to the main panel
     */
    public void spawnTable() {

        //***Table*******
        //holds the data-model
        //the reference of table can reach the datamodel.
        c.fill = GridBagConstraints.BOTH;
        c.ipady = 40;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        c.weightx = 1.0;
        c.weighty = 1.0;

        table = new Table(this);

        /* Deactivate add and edit actions if table is row is deselected. */
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent arg0) {
                // TODO Auto-generated method stub
                if (table.isRowSelected()) {
                    control.GlobalActions.editAction.setEnabled(true);
                    control.GlobalActions.removeAction.setEnabled(true);

                } else {
                    control.GlobalActions.editAction.setEnabled(false);
                    control.GlobalActions.removeAction.setEnabled(false);

                }
            }
        });

        tablePanel.add(new JScrollPane(table));
        mainPanel.add(tablePanel, c);
    }

    /**
     * create necessary actions: Edit, Add, Remove.
     */
    public void spawnActions() {

        //It is the labels of the actions that are actually printed on the buttons.
        control.GlobalActions.editAction = new EditAction(this, lang.getString("text.edit"), table);
        control.GlobalActions.addAction = new AddAction(this, lang.getString("text.new"), table, calendar);
        control.GlobalActions.removeAction = new RemoveAction(this, lang.getString("text.delete"), table);
        control.GlobalActions.editAction.setEnabled(false);
        control.GlobalActions.removeAction.setEnabled(false);
    }

    /**
     * Create Edit, Add, Remove buttons
     */
    public void spawnButtons() {

        c.fill = GridBagConstraints.NONE;
        c.insets = new Insets(0, 0, 0, 0);
        c.ipady = 0;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.weightx = 0.0;
        c.weighty = 0.0;

        //ADD BUTTON
        // Add action shortcut
        KeyStroke keySave = KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK);
        addButton = new JButton("add");
        addButton.getActionMap().put("add", control.GlobalActions.addAction);
        addButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keySave, "add");

        mainPanel.add(addButton, c);

        c.gridx = 1;
        c.gridy = 0;

        //EDIT BUTTON
        // Add action shortcut
        KeyStroke keyEdit = KeyStroke.getKeyStroke(KeyEvent.VK_E, Event.CTRL_MASK);
        editButton = new JButton("edit");
        editButton.getActionMap().put("edit", control.GlobalActions.editAction);
        editButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyEdit, "edit");
        mainPanel.add(editButton, c);

        c.gridx = 2;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_START;

        //DELETE BUTTON
        delButton = new JButton("delete");
        mainPanel.add(delButton, c);

        c.gridy = 0;
        c.gridx = 3;
        c.fill = GridBagConstraints.BOTH;

        MyComponent customC = new MyComponent(table.tasks.size());
        mainPanel.add(customC, c);

        customC.addSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                // TODO Auto-generated method stub
                table.getSelectionModel().setSelectionInterval(e.getFirstIndex(), e.getFirstIndex());
            }

        });

        //functionality to open the edit dialog
        //uses the action-class editAction
        //which gets values of selected TaskItem and
        //passes it to dialog Edit which then returns updated values
        //and EditAction passes it to function in Table that saves it to selected TaskItem
        editButton.setAction(control.GlobalActions.editAction);
        addButton.setAction(control.GlobalActions.addAction);
        delButton.setAction(control.GlobalActions.removeAction);

        //SET ICONS FOR BUTTONS
        ImageIcon icon;

        //get icons from Todo/res
        icon = new ImageIcon(getClass().getResource("/Edit24.gif"));
        editButton.setIcon(icon);
        icon = new ImageIcon(getClass().getResource("/Add24.gif"));
        addButton.setIcon(icon);
        icon = new ImageIcon(getClass().getResource("/Delete24.gif"));
        delButton.setIcon(icon);
    }

    public CalendarPane getCalendar() {
        return calendar;
    }

    public void setCalendar(CalendarPane calendar) {
        this.calendar = calendar;
    }

    protected void processWindowEvent(WindowEvent e) {

        if (e.getID() == WindowEvent.WINDOW_CLOSING) {

            //this dialog is pain in the ass during development but add it in final
            //int exit = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?");
            //if (exit == JOptionPane.YES_OPTION) {
            //SAves all the entries before closing
            prop.save();
            System.exit(0);
            //}

        } else {

            // If you do not want listeners processing the WINDOW_CLOSING
            // events, you could this next call in an else block for the:
            //     if (e.getID() ...)
            // statement. That way, only the other types of Window events
            // (iconification, activation, etc.) would be sent out.
            super.processWindowEvent(e);
        }
    }

}
