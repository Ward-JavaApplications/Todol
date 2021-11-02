import java.util.HashMap;
import java.util.Map;

public class TodoEntry {
    private String text;
    private String taskDay;
    private int priority;
    private int id;

    public TodoEntry(String text, String taskDay, int priority) {
        this.text = text;
        this.taskDay = taskDay;
        this.priority = priority;
    }

    public TodoEntry(String text, String taskDay, int priority, int id) {
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

    public String getTaskDay() {
        return taskDay;
    }

    public void setTaskDay(String taskDay) {
        this.taskDay = taskDay;
    }

    public int getPriority() {
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

    public HashMap<String,String> getAsHashMap(){
        HashMap<String,String> data = new HashMap<>();
        data.put("priority", String.valueOf(priority));
        data.put("task",text);
        data.put("taskDay",taskDay);
        return data;

    }
}
