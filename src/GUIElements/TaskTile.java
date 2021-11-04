package GUIElements;

import Containers.TodoEntry;

import javax.swing.*;
import java.awt.*;

public class TaskTile extends JPanel {
    public TaskTile(TodoEntry entry){
        super();
        setLayout(new FlowLayout());
        add(new JLabel(entry.getText()));
        add(new JLabel(entry.getTaskDay().toString()));
        add(new JLabel(String.valueOf(entry.getPriority())));
    }
}
