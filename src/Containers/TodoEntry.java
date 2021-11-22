package Containers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TodoEntry {
    private String text;
    private Date taskDay;
    private long priority;
    private int id;

    public TodoEntry(String text, Date taskDay, long priority) {
        this.text = text;
        this.taskDay = taskDay;
        this.priority = priority;
    }

    public TodoEntry(String text, Date taskDay, long priority, int id) {
        this.text = text;
        this.taskDay = taskDay;
        this.priority = priority;
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTaskDay() {
        return taskDay;
    }

    public String getTaskDayParsed(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE dd MMM yyyy");
        return dateFormat.format(taskDay);
    }

    public void setTaskDay(Date taskDay) {
        this.taskDay = taskDay;
    }

    public long getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HashMap<String,Object> getAsHashMap(){
        HashMap<String,Object> data = new HashMap<>();
        data.put("priority", priority);
        data.put("task",text);
        data.put("taskDay",taskDay.getTime());
        return data;

    }
}
