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
        JFrame frame = initialize();
        frame.setContentPane(new EditEntryPanel().getJPanelFromEntry(entry,firestoreManager,parentManager));
        frame.setVisible(true);
    }

    private JFrame initialize(){
        JFrame frame = new JFrame("Edit " + entry.getText());
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize((int)d.getWidth()/4,(int)d.getHeight()/4);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return frame;
    }


}
