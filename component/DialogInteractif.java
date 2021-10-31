package paint.component;

import javax.swing.*;

public class DialogInteractif {

    private String saisie;

    public DialogInteractif(){
        JFrame frame = new JFrame();
        this.saisie = JOptionPane.showInputDialog(frame,"Entrez votre chaine de caractères:");
    }

    public String getChaine() {
        return saisie;
    }
}
