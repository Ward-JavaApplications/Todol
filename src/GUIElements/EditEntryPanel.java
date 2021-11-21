package GUIElements;

import Containers.TodoEntry;
import Managers.FirestoreManager;
import Managers.GUIManager;
import com.toedter.calendar.JCalendar;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.jar.JarEntry;

public class EditEntryPanel {

    private GUIManager parentManager;
    private FirestoreManager firestoreManager;
    private TodoEntry entry;
    private JTextField textField = null;
    private JCalendar calendar = null;
    private JSpinner spinner = null;
    private JButton button = null;
    private int entryID = 0;

    public EditEntryPanel(){    }
    public JPanel getJPanelFromEntry(TodoEntry entry,FirestoreManager firestoreManager,GUIManager parentManager){
        this.parentManager = parentManager;
        this.firestoreManager = firestoreManager;
        this.entry = entry;
        this.entryID = entry.getId();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        mainPanel.add(textPanel(entry.getText()));
        mainPanel.add(datePanel(entry.getTaskDay()));
        mainPanel.add(priorityPanel((int) entry.getPriority()));
        mainPanel.add(saveButtonPanel());
        return mainPanel;
    }

    public JPanel getJPanelNew(FirestoreManager firestoreManager,GUIManager parentManager){
        this.parentManager = parentManager;
        this.firestoreManager = firestoreManager;
        this.entryID = firestoreManager.getNewId();
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        mainPanel.add(textPanel(""));
        mainPanel.add(datePanel(new Date()));
        mainPanel.add(priorityPanel(0));
        mainPanel.add(saveButtonPanel());
        return mainPanel;

    }

    private JPanel textPanel(String text){
        JPanel panel = new JPanel(new FlowLayout());
        textField = new JTextField();
        textField.setColumns(50);
        textField.setText(text);
        panel.add(textField);
        return panel;
    }
    private JPanel datePanel(Date date){
        calendar = new JCalendar();
        calendar.setDate(date);
        JPanel datePanel = new JPanel(new FlowLayout());
        datePanel.add(calendar);
        return datePanel;
    }
    private JPanel priorityPanel(int priority){
        JPanel panel = new JPanel(new FlowLayout());
        SpinnerModel value = new SpinnerNumberModel(priority,0,5,1);
        spinner = new JSpinner(value);
        panel.add(spinner);
        return panel;
    }
    private JPanel saveButtonPanel(){
        JPanel panel = new JPanel(new FlowLayout());
        button = new JButton("Save");
        button.addActionListener(a -> saveEntry());
        panel.add(button);
        return panel;
    }

    private void saveEntry(){
        firestoreManager.changeEntry(new TodoEntry(textField.getText(),calendar.getDate(),((Number)spinner.getValue()).longValue(),entryID));
        parentManager.repaintEntrys();
    }
}
