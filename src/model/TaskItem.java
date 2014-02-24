package model;

import helper.ValueContainer;

/**Description of TaskItem
 * 
 * TaskItem is the events(time priority date etc)
 * They are all stored in an Arraylist inside Table
 * 
 *
 * 
 * @author tony bjorkman
 *
 */

public class TaskItem {

	private String priority;
	private String description;
	private String date;
	private String category;
        private int progress;
    private boolean done;
	
	
        // ****** Accessors ******

    
    /**
     * Get priority accesor method
     * 
     * @return String priority
     */
    public boolean getDone(){
    	return done;
    }
        /**
         * Get priority accesor method
         * 
         * @return String priority
         */
	public String getPriority() {
		return priority;
	}

        /**
         * Get progress method
         * 
         * @return String priority
         */
	public int getProgress() {
		return progress;
	}
        
    /**
     * Set done method
     */
	public void setDone(boolean done){
		this.done=done;
	}
        /**
         * Set progress method
         */
	public void setProgress(int prog) {
		this.progress = prog;
	}
        
        /**
         * Set priority accessor method
         */
	public void setPriority(String priority) {
		this.priority = priority;
	}
        
        /**
         * Get description accessor method
         * 
         * @return String description
         */
	public String getDescription() {
		return description;
	}

        /**
         * Set description accessor method
         */
	public void setDescription(String description) {
		this.description = description;
	}

        /**
         * Get date accessor method
         * 
         * @return String date
         */
	public String getDate() {
		return date;
	}

        /**
         * Set date accessor method
         * 
         */
	public void setDate(String date) {
		this.date = date;
	}
        
        /**
         * Get category accessor method
         * 
         * @return String category
         */
	public String getCategory() {
		return category;
	}

        /**
         * Set category accessor method
         * 
         */
	public void setCategory(String category) {
		this.category = category;
	}

        /**
         * Set task item variables
         * 
         * @param String descr
         * @param String prio
         * @param String cat
         * @param String date 
         */
	public TaskItem(String descr,String prio,String cat,String date){
		this.description=descr;
		this.date=date;
		this.category=cat;
		this.priority=prio;
		this.progress=0;
		this.done=false;
	}

        /**
         * Set task item variables
         * 
         * @param String descr
         * @param String prio
         * @param String cat
         * @param String date 
         * @param String prog
         */
        public TaskItem(String descr,String prio,String cat,String date,int prog,boolean done){
		this.description=descr;
		this.date=date;
		this.category=cat;
		this.priority=prio;
		this.progress=prog;
		this.done=done;
	}
        
	/**
         * Return row as a value container
         * 
         * @return ValueContainer
         */
	public ValueContainer getRowAsVC(){
		return new ValueContainer(description,priority,category,date,progress);
	
	}



	public void setValuesfromVC(ValueContainer vc) {
		System.out.println("TaskItem: Saves VC values, Finished");
		description =vc.descr;
		date = vc.date;
		category=vc.cat;
		priority=vc.prio;
                progress=vc.progress;
	}
	
}
