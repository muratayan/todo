package control;

import java.awt.event.ActionEvent;
import java.util.Locale;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Action class for switching the locale between English and Swedish
 *
 * @author Jarl
 */
public class LanguageAction extends BaseAction implements Action {

    private String text;

    public LanguageAction(JFrame frame, Icon icon, String text) {
        super(frame, icon, text);
        this.text = text;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (text.equals("English")) {
            i18n.Language.getInstance().setLocale(new Locale("en", "US"));
        }
        if (text.equals("Svenska")) {
            i18n.Language.getInstance().setLocale(new Locale("sv", "SE"));
        }
        JOptionPane.showMessageDialog(null, "You need to restart application for changes to take effect", "Notice", JOptionPane.PLAIN_MESSAGE);
    }

}
