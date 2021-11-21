package GUIElements;

import Containers.TodoEntry;
import Managers.FirestoreManager;
import Managers.GUIManager;

import javax.swing.*;
import java.awt.*;

public class EditEntryFrame{
    private TodoEntry entry;
    private GUIManager parentManager;
    private FirestoreManager firestoreManager;
    public EditEntryFrame(TodoEntry entry,FirestoreManager firestoreManager,GUIManager parentManager){
        this.entry = entry;
        this.parentManager = parentManager;
        this.firestoreManager = firestoreManager;
        JFrame frame = initialize("Edit " + entry.getText());
        frame.setContentPane(new EditEntryPanel().getJPanelFromEntry(entry,firestoreManager,parentManager));
        frame.setVisible(true);
    }

    public EditEntryFrame(FirestoreManager firestoreManager,GUIManager parentManager){
        this.parentManager = parentManager;
        this.firestoreManager = firestoreManager;
        JFrame frame = initialize("Create new entry");
        frame.setContentPane(new EditEntryPanel().getJPanelNew(firestoreManager,parentManager));
        frame.setVisible(true);
    }

    private JFrame initialize(String titleText){
        JFrame frame = new JFrame(titleText);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize((int)d.getWidth()/4,(int)d.getHeight()/4);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
    }


}
