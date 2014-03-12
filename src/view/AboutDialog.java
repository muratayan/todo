package view;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Descr: Class opens a dialog used to inform about the application
 *
 * @author tony bjorkman
 *
 */
public class AboutDialog extends JDialog {

    public AboutDialog() {
        JPanel panel = new JPanel();

        JLabel descrLabel = new JLabel("about it");
        panel.add(descrLabel);
        add(panel);
        setSize(179, 276);  //
        setVisible(true);

    }

}
