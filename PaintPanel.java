package paint;

import paint.listener.MousePanelInfo;
import paint.shape.Dashed_rectangle;
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
    protected Dashed_rectangle selection;
    // protected MousePanelInfo mousePanelInfo;
    protected Color couleur;

    public PaintPanel(){
        this.x = 50;
        this.y = 50;
        this.shapes = new ArrayList<>();
        this.couleur = Color.red;
        this.drawings = new ArrayList<>();
        // this.mousePanelInfo = new MousePanelInfo();
        this.drawings.add(new FreeHand(this.couleur));
        // this.addMouseListener(this.mousePanelInfo);
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        for (FreeHand h: drawings){
            h.draw(g);
        }

        for (Shape s: shapes)
            s.draw(g);

        if (this.selection != null)
            this.selection.draw(g);

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

    public void addFreeHand(int x, int y){
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

    public Dashed_rectangle getSelection() {
        return selection;
    }

    public ArrayList<FreeHand> getDrawings() {
        return drawings;
    }

    public void moveShapes(MouseEvent e){
        for (Shape s : shapes){
            if (s.isInTheShape(e.getX(), e.getY())){
                s.moveShape(e);
            }
        }
        this.repaint();
    }

    public void moveDrawingsSelection(MouseEvent e){
        for (FreeHand h : drawings){
            if (this.selection.verifyShapeIntheRect(h) && h.points.size() > 0){
                h.moveShape(this.selection.getX(), this.selection.getY(), e);
            }
        }
        this.selection.mouseClick(e);
        this.repaint();
    }

    public void addSelection(Dashed_rectangle dashed_rectangle){
        this.selection = dashed_rectangle;
    }

    public void manageOutOfSelection(){
        this.selection = null;
        this.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        this.repaint();
    }

}
