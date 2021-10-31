package paint.component;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogBox {

    private static JDialog dialog;

    public DialogBox(){
        JFrame frame = new JFrame();
        dialog = new JDialog(frame, "Welcome to my custom Paint ! ", true);
        dialog.setLayout(new FlowLayout());
        dialog.setLocation(850, 400);

        JButton button_answer = new JButton("Yep");

        button_answer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogBox.dialog.setVisible(false);
            }
        });

        dialog.add(new JLabel("Are you an artist ?"));
        dialog.add(button_answer);
        dialog.setSize(300, 200);
        dialog.setVisible(true);
    }
}
