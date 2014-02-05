package model;

public class eventItem {

	private int priority;
	private String description;
	private String date;
	private String category;
	
	public eventItem(String descr,String idate,String cat,int prio){
		description=descr;
		date=idate;
		category=cat;
		priority=prio;
		
	}
	
	public Object[] getAsRow(){
		Object[] row = {description,date,category,priority};
	return row;
	}
	
}
