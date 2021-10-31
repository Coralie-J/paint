package paint;

import paint.shape.FreeHand;
import paint.shape.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PaintPanel extends JPanel {

    protected int x,y;
    protected ArrayList<paint.shape.Shape> shapes;
    protected ArrayList<FreeHand> drawings;
    protected Color couleur;

    public PaintPanel(){
        this.x = 50;
        this.y = 50;
        this.shapes = new ArrayList<>();
        this.couleur = Color.red;
        this.drawings = new ArrayList<>();
        this.drawings.add(new FreeHand(this.couleur));
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        for (FreeHand h: drawings){
            h.draw(g);
        }

        for (Shape s: shapes)
            s.draw(g);

    }

    public void mouseClik(MouseEvent e){
        if (shapes.size() > 0) {
            this.shapes.get(this.shapes.size() - 1).mouseClik(e);
            this.repaint();
        }
    }

    public void mouseClic(MouseEvent e){
        this.x = e.getX();
        this.y = e.getY();
        this.repaint();
    }

    public void addShape(Shape f){
        this.shapes.add(f);
        this.repaint();
    }

    protected void addFreeHand(int x, int y){
        drawings.get(drawings.size() - 1).changeColor(this.couleur);
        drawings.get(drawings.size() - 1).addPoint(x,y);
        this.repaint();
    }

    public void changeColor(Color c){
        this.couleur = c;
    }

    public void clear(){
        this.drawings.clear();
        this.drawings.add(new FreeHand(this.couleur));
        this.shapes.clear();
        this.repaint();
    }

    public Color getCouleur() {
        return couleur;
    }

    public ArrayList<FreeHand> getDrawings() {
        return drawings;
    }
}
