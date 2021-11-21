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
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        add(new DefaultLabel(entry.getText()));
        add(new DefaultLabel(entry.getTaskDay().toString()));
        add(new DefaultLabel(String.valueOf(entry.getPriority())));
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

    private void editEntry(){
        new EditEntryFrame(thisEntry,parent,parentGUI);
    }

    private void deleteEntry(){
        parent.deleteEntry(thisEntry);
        parentGUI.repaintEntrys();
    }



}
