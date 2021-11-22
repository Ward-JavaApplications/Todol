package GUIElements;

import javax.swing.*;
import java.awt.*;

public class DefaultLabel{
    private JLabel label = null;
    DefaultLabel(String text){
        label = new JLabel(text,SwingConstants.CENTER);
        label.setForeground(Color.WHITE);
        label.setFont(label.getFont().deriveFont(100));
    }
    public JLabel getLabel(){
        return label;
    }
}
