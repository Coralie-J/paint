package paint;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import paint.component.ColorChooser;
import paint.component.DialogBox;
import paint.component.DialogInteractif;
import paint.listener.MousePanelInfo;
import paint.listener.MousePaneldraw;
import paint.shape.Cercle;
import paint.shape.Chaine;
import paint.shape.Rectangle;

public class Paint {

    private PaintPanel main;

    /**
     * Constitue l'interface
     */

    public Paint(){
        JFrame window = new JFrame();
        this.main = new PaintPanel();
        this.main.setLayout(new BorderLayout());
        window.setSize(1200,800);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JToolBar toolBar = new JToolBar("Toolbox", SwingConstants.VERTICAL);
        toolBar.setLayout(new GridLayout(4, 2));


        JPanel menu_panel = new JPanel();
        menu_panel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        menu_panel.add(new JLabel("Welcome to my paint"));
        menu_panel.add(new JLabel("Catch me"));

        // Création des boutons

        JButton bouton_rectangle = new JButton();
        JButton bouton_cercle = new JButton();
        JButton bouton_str = new JButton();
        JButton btn_change_color = new JButton("Change color");

        JButton btn_selection = new JButton();
        JButton btn_select_line = new JButton();
        JButton gomme = new JButton();

        // Add icons

        try {

            Image img = ImageIO.read(getClass().getResource("./icons/rectangle.png"));
            Image newimg = img.getScaledInstance( 40, 30,  java.awt.Image.SCALE_SMOOTH );
            ImageIcon icon = new ImageIcon(newimg);
            bouton_rectangle.setIcon(icon);

            Image img2 = ImageIO.read(getClass().getResource("./icons/cercle.png"));
            newimg = img2.getScaledInstance( 30, 30,  java.awt.Image.SCALE_SMOOTH );
            icon = new ImageIcon(newimg);
            bouton_cercle.setIcon(icon);

            img2 = ImageIO.read(getClass().getResource("./icons/lettre_a.jpg"));
            newimg = img2.getScaledInstance( 20, 20,  java.awt.Image.SCALE_SMOOTH );
            icon = new ImageIcon(newimg);
            bouton_str.setIcon(icon);

            img2 = ImageIO.read(getClass().getResource("./icons/selection_main.png"));
            newimg = img2.getScaledInstance( 20, 20,  java.awt.Image.SCALE_SMOOTH );
            icon = new ImageIcon(newimg);
            btn_selection.setIcon(icon);

            img2 = ImageIO.read(getClass().getResource("./icons/selection.png"));
            newimg = img2.getScaledInstance( 30, 30,  java.awt.Image.SCALE_SMOOTH );
            icon = new ImageIcon(newimg);
            btn_select_line.setIcon(icon);

            img2 = ImageIO.read(getClass().getResource("./icons/gomme.png"));
            newimg = img2.getScaledInstance( 40, 50,  java.awt.Image.SCALE_SMOOTH );
            icon = new ImageIcon(newimg);
            gomme.setIcon(icon);

        }  catch (Exception e){
            e.printStackTrace();
        }

        // Ajout des listeners

        this.manageButtonCercle(bouton_cercle);
        this.manageButtonGomme(gomme);
        this.manageButtonStr(bouton_str);
        this.manageButtonRectangle(bouton_rectangle);
        this.manageButtonChangeColor(btn_change_color);
        this.manageButtonSelection(btn_selection);
        this.manageButtonSelectionLigne(btn_select_line);

        toolBar.add(bouton_rectangle);
        toolBar.add(bouton_cercle);
        toolBar.add(bouton_str);
        toolBar.add(btn_change_color);
        toolBar.add(btn_selection);
        toolBar.add(btn_select_line);
        toolBar.add(gomme);
        this.main.addMouseListener(new MousePanelInfo());
        this.main.addMouseMotionListener(new MousePaneldraw());

        new DialogBox();

        // Ajout des composants dans le main

        main.add(menu_panel, BorderLayout.NORTH);
        main.add(toolBar, BorderLayout.WEST);

        window.add(main);
        window.validate();
        window.setVisible(true);
    }

    public void manageButtonRectangle(JButton bouton_rectangle){
        bouton_rectangle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                Color c = main.getCouleur();
                main.addShape(new Rectangle(250,100, c , 75, 100));
            }
        });
    }

    public void manageButtonCercle(JButton bouton_cercle){
        bouton_cercle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                Color c = main.getCouleur();
                main.addShape(new Cercle(350, 90, c, 150));
            }
        });
    }

    public void manageButtonChangeColor(JButton btn_change_color){
        btn_change_color.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                main.changeColor(new ColorChooser().getCouleur());
            }
        });
    }

    public void manageButtonGomme(JButton gomme){
        gomme.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                main.clear();
            }
        });
    }

    public void manageButtonStr(JButton bouton_str){
        bouton_str.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                DialogInteractif d = new DialogInteractif();
                if (d.getChaine() != null && ! d.getChaine().trim().equals("")) {
                    Chaine saisie = new Chaine(320, 90, main.getCouleur(), d.getChaine());
                    main.addShape(saisie);
                }
            }
        });
    }

    public void manageButtonSelection(JButton btn_selection){
        btn_selection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
            }
        });
    }

    public void manageButtonSelectionLigne(JButton btn_selection){
        btn_selection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            }
        });
    }


    public static void main(String[] args){
        new Paint();
    }

}
