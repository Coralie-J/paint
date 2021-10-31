package paint;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import paint.component.ColorChooser;
import paint.component.DialogBox;
import paint.component.DialogInteractif;
import paint.listener.MousePanelInfo;
import paint.shape.Cercle;
import paint.shape.Chaine;
import paint.shape.Rectangle;

public class Paint {

    private PaintPanel main;

    public Paint(){
        JFrame window = new JFrame();
        this.main = new PaintPanel();
        this.main.setLayout(new FlowLayout());
        window.setSize(900,900);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Création des boutons

        JButton bouton_rectangle = new JButton("Rectangle");
        JButton bouton_cercle = new JButton("Cercle");
        JButton bouton_str = new JButton("Chaine de caractères");
        JButton btn_change_color = new JButton("Change color");
        JButton gomme = new JButton("Clear");

        // Ajout des listeners

        this.manageButtonCercle(bouton_cercle);
        this.manageButtonGomme(gomme);
        this.manageButtonStr(bouton_str);
        this.manageButtonRectangle(bouton_rectangle);
        this.manageButtonChangeColor(btn_change_color);
        this.main.addMouseListener(new MousePanelInfo());
        this.manageMain();

        new DialogBox();

        // Ajout des composants dans le main

        main.add(new JLabel("Welcome to my paint"));

        main.add(bouton_rectangle);
        main.add(bouton_cercle);
        main.add(bouton_str);
        main.add(btn_change_color);
        main.add(gomme);
        main.add(new JLabel("Catch me"));

        window.add(main);
        window.validate();
        window.setVisible(true);
    }

    public void manageButtonRectangle(JButton bouton_rectangle){
        bouton_rectangle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color c = main.getCouleur();
                main.addShape(new Rectangle(50,50, c , 50, 89));
            }
        });
    }

    public void manageButtonCercle(JButton bouton_cercle){
        bouton_cercle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color c = main.getCouleur();
                main.addShape(new Cercle(90, 90, c, 60));
            }
        });
    }

    public void manageButtonChangeColor(JButton btn_change_color){
        btn_change_color.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.changeColor(new ColorChooser().getCouleur());
            }
        });
    }

    public void manageButtonGomme(JButton gomme){
        gomme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.clear();
            }
        });
    }

    public void manageButtonStr(JButton bouton_str){
        bouton_str.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DialogInteractif d = new DialogInteractif();
                Chaine saisie = new Chaine(60,60, main.getCouleur(),d.getChaine());
                main.addShape(saisie);
            }
        });
    }

    public void manageMain(){
        main.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                main.addFreeHand(e.getX(), e.getY());
            }

            @Override
            public void mouseMoved(MouseEvent e) {}
        });
    }


    public static void main(String[] args){
        new Paint();
    }

}
