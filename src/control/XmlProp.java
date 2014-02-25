package control;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.InvalidPropertiesFormatException;
import java.util.Locale;
import java.util.Properties;

import javax.swing.JFrame;

/**
 * Class designed to save/load window preferences
 * @author Jarl
 */
public class XmlProp extends Properties {

	private JFrame frame;
	
	public XmlProp(JFrame frame){
		this.frame = frame;
	}
	
	/**
         * Saving window position to an xml file
         */
        public void save(){
		//try saving the properties of the TodoWindow to xml file
		try {
			setProperty("lang",""+i18n.Language.getInstance().getLocale());
                    setProperty("xpos", ""+frame.getX());
                    setProperty("ypos", ""+frame.getY());
                    setProperty("height", ""+frame.getSize().height);
                    setProperty("width", ""+frame.getSize().width);
	        
                    File f = new File("settings.xml");
                    OutputStream out = new FileOutputStream( f );
                    storeToXML(out,"");
                }
                catch (Exception e ) {
                    e.printStackTrace();
                }
	}
	
        /**
         * Load settings from the xml file
         */
	public void load(){
		FileInputStream is;
                try {
                    File f = new File("settings.xml");
                    is = new FileInputStream( f );
		   
                
                    // Try loading properties from the file (if found)
                    try {
                        loadFromXML(is);
                    } catch (InvalidPropertiesFormatException e) {
                    
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                catch ( FileNotFoundException e ) {
                    System.out.println("XmlProp: File not Found");
                }
                String stringLocale = this.getProperty("lang", "sv_SE");
		 	
                String language = stringLocale.substring(0, 2);
                String region = stringLocale.substring(3);
                System.out.println(stringLocale+" "+language + " " + region);
                Locale locale = new Locale(language,region);
                i18n.Language.getInstance().setLocale(locale);
		 	
                int x = Integer.parseInt(this.getProperty("xpos", "40"));
                int y = Integer.parseInt(this.getProperty("ypos", "80"));
                int h = Integer.parseInt(this.getProperty("height", "460"));
                int w = Integer.parseInt(this.getProperty("width", "716"));
                //Sets size and location of the TodoWindow
                //Sets language locale

                frame.setLocation(x, y);
                frame.setPreferredSize(new Dimension(w,h));
	}
	
	
}
