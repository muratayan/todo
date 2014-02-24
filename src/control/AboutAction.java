package control;

import java.awt.event.ActionEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import view.AboutDialog;

/** Description of AboutAction
 * 
 * Class defines the action used when the "About" menu button is pressed.
 *
 */
public class AboutAction extends BaseAction {
        
        /**
         * 
         * @param frame
         * @param icon
         * @param text 
         */
	public AboutAction(JFrame frame, Icon icon, String text) {
		super(frame,icon, text);
        
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		new AboutDialog();
	}

}
