package helper;

import model.TaskItem;

/**
 * Description of ValueContainer
 *
 * Stores the TaskItem field variables as they are passed to and from the
 * Dialog.
 *
 * @author tony bjorkman
 *
 */
public class ValueContainer {

    public String descr, prio, cat, date;
    public int progress;

    public ValueContainer() {

    }
    
    /**
     * 
     * @param descr description
     * @param prio  priority
     * @param cat   category
     * @param date  
     * @param prog  progress
     */
    public ValueContainer(String descr, String prio, String cat, String date, int prog) {
        this.descr = descr;
        this.prio = prio;
        this.cat = cat;
        this.date = date;
        this.progress = prog;
    }

    //converts the instance of a valuecontainer to a taskitem.
    public TaskItem convertToTaskItem() {
        return new TaskItem(descr, prio, cat, date, progress, false);
    }

}
