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
import paint.shape.Dashed_rectangle;
import paint.shape.Rectangle;

public class Paint {

    private PaintPanel main;

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

        JButton bouton_rectangle = new JButton("▭");
        JButton bouton_cercle = new JButton("O");
        JButton bouton_str = new JButton("A");
        JButton btn_change_color = new JButton("Change color");
        JButton btn_selection = new JButton("Selection forme");
        JButton btn_select_line = new JButton("Selection A/Ligne");
        JButton gomme = new JButton("Clear");

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
        this.manageMain();

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

    public void manageMain(){
        main.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (main.getCursor().equals(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR))){
                    main.addFreeHand(e.getX(), e.getY());
                }
                else if (main.getCursor().equals(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR))) {
                    if (main.selection == null)
                        main.moveShapes(e);
                    else
                        main.moveDrawingsSelection(e);

                } else if (main.getCursor().equals(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR))) {
                    if (main.selection == null){
                        Dashed_rectangle rectangle = new Dashed_rectangle(e.getX(), e.getY());
                        main.addSelection(rectangle);
                    } else {
                        int w = e.getX() - main.selection.getX();
                        int h = e.getY() - main.selection.getY();
                        main.selection.changeSize(w, h);
                        main.repaint();
                    }
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (main.selection != null) {
                    if (main.selection.isInTheShape(e.getX(), e.getY()))
                        main.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                    else
                        main.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
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
