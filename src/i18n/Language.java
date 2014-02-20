package i18n;

import java.util.Locale;
import java.util.ResourceBundle;

/*Singleton pattern*/
public final class Language {
   private static Language instance = null;

   public Locale locale;
   
   protected Language() {}

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
   
   public String getString(String item) {
        ResourceBundle bundle = ResourceBundle.getBundle("i18n.bundle", locale);
        return bundle.getString(item);
   }
   
}