package GUIElements;

import Containers.TodoEntry;

import javax.swing.*;
import java.awt.*;

public class TaskTile extends JPanel {
    public TaskTile(TodoEntry entry){
        super();
        setLayout(new GridLayout());
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(new DefaultLabel(entry.getText()));
        add(new DefaultLabel(entry.getTaskDay().toString()));
        add(new DefaultLabel(String.valueOf(entry.getPriority())));
    }

}
