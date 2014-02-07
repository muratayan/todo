package model;

import helper.ValueContainer;

/**Description of TaskItem
 * 
 * TaskItem is the events(time priority date etc)
 * They are all stored in an Arraylist inside Table
 * 
 *
 * 
 * @author tony björkman
 *
 */

public class TaskItem {

	private String priority;
	private String description;
	private String date;
	private String category;
	
	
	public String getPriority() {
		return priority;
	}



	public void setPriority(String priority) {
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


	public TaskItem(String descr,String prio,String cat,String date){
		this.description=descr;
		this.date=date;
		this.category=cat;
		this.priority=prio;
		
	}
	
	
	
	public ValueContainer getRowAsVC(){
		return new ValueContainer(description,priority,category,date);
	
	}



	public void setValuesfromVC(ValueContainer vc) {
		// TODO Auto-generated method stub
		System.out.println("TaskItem: Saves VC values, Finished");
		description =vc.descr;
		date = vc.date;
		category=vc.cat;
		priority=vc.prio;
	}
	
}
