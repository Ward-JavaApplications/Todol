package GUIElements;

import javax.swing.*;
import java.awt.*;

public class DefaultLabel extends JLabel {
    DefaultLabel(String text){
        super(text,SwingConstants.CENTER);
        super.setFont(this.getFont().deriveFont(100));
        super.repaint();
        super.revalidate();

    }
}
