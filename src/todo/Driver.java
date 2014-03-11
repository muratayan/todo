/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package todo;

import view.TodoWindow;
import view.GadgetWindow;

import java.util.ResourceBundle;
import java.util.ListResourceBundle;
import java.util.Locale;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;

/**
 *
 * @author Jarl
 */

public class Driver {
	           
    public static void main(String[] args) {
  
        try {
            // Set System L&F
        
        MetalLookAndFeel.setCurrentTheme(new DefaultMetalTheme());
         UIManager.setLookAndFeel(new MetalLookAndFeel());
        
    } 
    catch (UnsupportedLookAndFeelException e) {
       // handle exception
    }
        
        TodoWindow todo = new TodoWindow();
        GadgetWindow gadget = new GadgetWindow();
    }
}
