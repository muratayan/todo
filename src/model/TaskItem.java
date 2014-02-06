package model;

public class TaskItem {

	private int priority;
	private String description;
	
	public int getPriority() {
		return priority;
	}



	public void setPriority(int priority) {
		this.priority = priority;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getDate() {
		return date;
	}



	public void setDate(String date) {
		this.date = date;
	}



	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}



	private String date;
	private String category;
	
	public TaskItem(String descr,String idate,String cat,int prio){
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
