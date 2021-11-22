package GUIElements;

import Containers.TodoEntry;
import Managers.FirestoreManager;
import Managers.GUIManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Objects;

public class TaskTile extends JPanel {
    private FirestoreManager parent;
    private TodoEntry thisEntry;
    private GUIManager parentGUI;
    public TaskTile(TodoEntry entry, FirestoreManager parent,GUIManager parentGUI){
        super();
        this.parent = parent;
        this.thisEntry = entry;
        this.parentGUI = parentGUI;
        setLayout(new FlowLayout());
        setBorder(BorderFactory.createLineBorder(Color.WHITE));
        setBackground(Color.BLACK);
        add(new DefaultLabel(entry.getText()).getLabel());
        add(new DefaultLabel(entry.getTaskDayParsed()).getLabel());
        add(getPriorityLabel(String.valueOf(entry.getPriority()),entry.getPriority()));
        addEditButton();
        addDeleteButton();

    }

    private void addEditButton(){
        try {
            InputStream is = new BufferedInputStream(new FileInputStream("src/assets/pencil.png"));
            Image img = ImageIO.read(is);
            ImageIcon imageIcon = new ImageIcon(img.getScaledInstance(30,30,0));
            JButton button = new JButton(imageIcon);
            button.addActionListener(a -> {
                editEntry();
            });
            add(button);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void addDeleteButton(){
        try {
            InputStream is = new BufferedInputStream(new FileInputStream("src/assets/delete.png"));
            Image img = ImageIO.read(is);
            ImageIcon imageIcon = new ImageIcon(img.getScaledInstance(30,30,0));
            JButton button = new JButton(imageIcon);
            button.addActionListener(a -> {
                deleteEntry();
            });
            add(button);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    private JLabel getPriorityLabel(String priority,long pri){
        JLabel label = new JLabel(priority);
        Color color = null;
        switch ((int)pri){
            case(0):
                color = Color.BLACK;
                break;
            case(1):
                color = Color.GRAY;
                break;
            case(2):
                color = Color.CYAN;
                break;
            case(3):
                color = Color.YELLOW;
                break;
            case(4):
                color = Color.ORANGE;
                break;
            case(5):
                color = Color.RED;
                break;
        }
        label.setForeground(color);
        return label;
    }

    private void editEntry(){
        new EditEntryFrame(thisEntry,parent,parentGUI);
    }

    private void deleteEntry(){
        parent.deleteEntry(thisEntry);
        parentGUI.repaintEntrys();
    }



}
