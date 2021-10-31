package paint.listener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class ButtonListener implements ActionListener{

    private JLabel label;

    public ButtonListener(JLabel label){
        this.label = label;
    }

    public void actionPerformed(ActionEvent e){
        System.out.println(e.getActionCommand());
        System.out.println(e.paramString());
        System.out.println(e.getSource());
        this.label.setText(e.getActionCommand());

    }
}
