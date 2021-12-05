package paint;

import paint.shape.Chaine;
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

        if (this.selection != null)
            this.selection.draw(g);

    }

    /**
     * Déplace le dernier element de l'attribut shapes de l'objet courant
     * @param e evènement
     */

    public void mouseClik(MouseEvent e){
        if (shapes.size() > 0) {
            this.shapes.get(this.shapes.size() - 1).mouseClik(e);
            this.repaint();
        }
    }

    /**
     * Ajoute un objet de type Shape dans l'arraylist d'objet Shape de l'objet courant
     * @param f Objet de type Shape à ajouter
     */

    public void addShape(Shape f){
        this.shapes.add(f);
        this.repaint();
    }

    /**
     * Ajoute un nouveau point dans l'arraylist de freehand
     * @param x abscisse du point à ajouter
     * @param y ordonnée du point à ajouter
     */

    public void addFreeHand(int x, int y){
        drawings.get(drawings.size() - 1).changeColor(this.couleur);
        drawings.get(drawings.size() - 1).addPoint(x,y);
        this.repaint();
    }

    public void changeColor(Color c){
        this.couleur = c;
    }

    /**
     * Efface tous les dessins
     */

    public void clear(){
        this.drawings.clear();
        this.drawings.add(new FreeHand(this.couleur));
        this.shapes.clear();
        this.repaint();
    }

    /**
     * Retourne la couleur du crayon
     * @return objet de type Color
     */

    public Color getCouleur() {
        return couleur;
    }

    /**
     * Retourne les dessins à main levée
     * @return l'arraylist avec les objets de type Freehand
     */

    public ArrayList<FreeHand> getDrawings() {
        return drawings;
    }

    /**
     * Déplace les figures de l'attribut shapes de l'objet courant si la souris est dans une figure
     * @param e
     */

    public void moveShapes(MouseEvent e){
        for (Shape s : shapes){
            if (s.isInTheShape(e.getX(), e.getY())){
                s.moveShape(e);
            }
        }
        this.repaint();
    }

    /**
     * Déplace les dessins à main levée et les textes de l'interface en fonction d'une selection
     * @param e evénement qui a provoqué le déplacement de la selection
     */

    public void moveDrawingsSelection(MouseEvent e){
        for (FreeHand h : drawings){
            if (this.selection.verifyShapeIntheRect(h) && h.points.size() > 0){
                h.moveShape(this.selection.getX(), this.selection.getY(), e);
            }
        }
        for (Shape s: shapes){
            if (s.getClass() == Chaine.class){
                Chaine c = (Chaine) s;
                if (this.selection.verifyShapeIntheRect(c))
                    c.moveShape(this.selection.getX(), this.selection.getY(),e);
            }
        }
        this.selection.mouseClick(e);
        this.repaint();
    }

    /**
     * Initialise la selection avec le rectangle
     * @param dashed_rectangle rectangle initialisé
     */

    public void addSelection(Dashed_rectangle dashed_rectangle){
        this.selection = dashed_rectangle;
    }

    /**
     * Supprime la selection
     */

    public void manageOutOfSelection(){
        this.selection = null;
        this.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        this.repaint();
    }

    /**
     * Retourne la selection de l'objet courant
     * @return selection
     */

    public Dashed_rectangle getSelection() {
        return selection;
    }
}
