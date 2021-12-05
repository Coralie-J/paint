package paint.shape;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class Shape {
    protected int x,y;
    protected Color color;

    /**
     *
     * @param x abscisse de la forme
     * @param y ordonnée de la forme
     * @param c couleur de la forme
     */

    public Shape(int x, int y, Color c){
        this.x = x;
        this.y = y;
        this.color = c;
    }

    /**
     * Retourne l'abscisse de la forme
     * @return x : entier
     */

    public int getX() {
        return x;
    }

    /**
     * Retourne l'ordonnée de la forme
     * @return y
     */

    public int getY(){
        return y;
    }

    /**
     * Change la position de la forme
     * @param e évènement de souris qui a déclenché l'appel
     */

    public void mouseClik(MouseEvent e){
        this.x = e.getX();
        this.y = e.getY();
    }

    /**
     * Retourne la couleur de l'objet Shape
     * @return Objet de type Color
     */

    public Color getColor() {
        return color;
    }

    /**
     * Change la couleur de l'objet de type Shape
     * @param c
     */

    public void changeColor(Color c){
        this.color = c;
    }

    /**
     * Méthode permettant de dessiner l'objet
     * @param g objet qui permet de dessiner
     */

    public abstract void draw(Graphics g);

    /**
     * Méthode qui permet de vérifier qu'un point est dans la forme courante
     * @param x abscisse du point
     * @param y ordonnée du point
     * @return booléen
     */

    public abstract boolean isInTheShape(int x, int y);

    /**
     * Déplacer l'objet à un endroit précis
     * @param e endroit où l'objet sera déplacé
     */

    public abstract void moveShape(MouseEvent e);
}
