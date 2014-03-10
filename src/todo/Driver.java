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

/**
 *
 * @author Jarl
 */

public class Driver {
	  
    public static void init() {
        //Set language settings. this should be loaded from somewhere.
        i18n.Language lang = i18n.Language.getInstance();
        lang.setLocale(new Locale("en", ""));
        //lang.setLocale(Locale.getDefault());
    }
          
    public static void main(String[] args) {
  
        init();
        
        TodoWindow todo = new TodoWindow();
        GadgetWindow gadget = new GadgetWindow();
    }
}
