package i18n;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Descr: A singleton which maintains the current Locale setting.
 * 
 * @author Max Pilström
 * 
 */
public final class Language {
   private static Language instance = null;

   public Locale locale;
   
   protected Language() {}

   /**
    * The heart of the singleton functionality.
    */
   public static Language getInstance() {
      if(instance == null) {
         instance = new Language();
      }
      return instance;
   }
  
   public Locale getLocale() {
       return locale;
   }
   
   public void setLocale(Locale n) {
       locale = n;
   }
   
   /**
    * Returns the Locale dependent string given the string key.
    */
   public String getString(String item) {
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.bundle", locale);
        return bundle.getString(item);
   }
   
}