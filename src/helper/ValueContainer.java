package helper;

import model.TaskItem;

/** Description of ValueContainer
 * 
 * Stores the TaskItem field variables as they are passed to and from the Dialog. 
 * 
 * @author tony bjorkman
 *
 */

public class ValueContainer {
public String descr,prio,cat,date;

public ValueContainer(){
	
}

public ValueContainer(String descr,String prio,String cat,String date){
	this.descr=descr;
	this.prio=prio;
	this.cat=cat;
	this.date=date;
}

public TaskItem convertToTaskItem(){
	return new TaskItem(descr,prio,cat,date);
}
	
}
