package paint.component;

import javax.swing.*;
import java.awt.*;

public class ColorChooser {

    private Color couleur;

    public ColorChooser(){
        JFrame frame = new JFrame();
        couleur = JColorChooser.showDialog(frame, "Choose a color", Color.RED);
    }

    public Color getCouleur() {
        return couleur;
    }
}
