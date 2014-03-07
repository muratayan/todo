package view;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.accessibility.Accessible;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MyComponent extends JComponent implements Accessible {

	private int x=0;
	private int startX;
	private int boxX=0;
	private int startY;
	private int boxY=0;
	private boolean moveable=false;
	private List<ListSelectionListener> listeners;
	
	public int getItems() {
		return items;
	}

	public void setItems(int items) {
		this.items = items;
	}

	private int items;
	
	public MyComponent(int items){
        setPreferredSize(new Dimension(30,40));
        setBorder(BorderFactory.createLineBorder(Color.BLUE));
        
        this.items=items;
        
        listeners=new ArrayList<ListSelectionListener>();
        
        addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				moveable=false;
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				moveable=contains(e.getX(),e.getY());
				startX=e.getX()-boxX;
				startY=e.getY()-boxY;

				System.out.print("helloooo"+x);
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
				

			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
        
        addMouseMotionListener(new MouseMotionListener(){
        	
			
			@Override
			public void mouseMoved(MouseEvent e) {
				// TODO Auto-generated method stub
				if(contains(e.getX(),e.getY()))
					setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
					
			}
			
			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub
				
				if(!moveable)
					return;
				
				int newX=e.getX()-startX;
				int newY=e.getY()-startY;
				
				if(newX <0)
					newX=0;
				if(newX>getWidth()-20)
					newX=getWidth()-20;
				boxX=newX;
				
				
				if(newY <0)
					newY=0;
				if(newY>getHeight()-20)
					newY=getHeight()-20;
				boxY=newY;
				
				
				ListSelectionEvent event = new ListSelectionEvent(this, calcWhichItem(newX)
				, 1, false);
				for(ListSelectionListener list: listeners){
					list.valueChanged(event);
				}
				
				repaint();
			}


		});
        
       	}
	
	private int calcWhichItem(int x) {
		// TODO Auto-generated method stub
		int itemSize = getWidth()/items;
		
		return x/itemSize;
	}
	
	  public void addSelectionListener(ListSelectionListener listener) {
		    listeners.add(listener);
		  }
	
	public boolean contains(int x,int y){
		if(x>=boxX && x<=boxX + 20 && y >=boxY && y<=boxY+20)
			return true;
		else
			return false;
	}
	
	public void paintComponent(Graphics g){
		g.setColor(Color.BLACK);
		
		g.fillRect(boxX, boxY, 20, 20);
		//g.drawRect(0, 0, 30, 40);
	}
	
	
	
}
