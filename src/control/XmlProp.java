package control;

import java.awt.Dimension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import javax.swing.JFrame;

public class XmlProp extends Properties {

	private JFrame frame;
	
	public XmlProp(JFrame frame){
		this.frame = frame;
	}
	
	public void save(){
		//try saving the properties of the TodoWindow to xml file
		try {
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
	
	public void load(){
		FileInputStream is;
		 try {
		        File f = new File("settings.xml");
		        is = new FileInputStream( f );
		   
	
		        // Try loading properties from the file (if found)
		        try {
					loadFromXML(is);
				} catch (InvalidPropertiesFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		    catch ( FileNotFoundException e ) {
		    	System.out.println("XmlProp: File not Found");
		    }
		    int x = Integer.parseInt(this.getProperty("xpos", "40"));
		    int y = Integer.parseInt(this.getProperty("ypos", "80"));
		    int h = Integer.parseInt(this.getProperty("height", "460"));
		    int w = Integer.parseInt(this.getProperty("width", "716"));
		    //Sets size and location of the TodoWindow
		    frame.setLocation(x, y);
		    frame.setPreferredSize(new Dimension(w,h));
	}
	
	
}
