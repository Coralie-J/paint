package paint.shape;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class Shape {
    protected int x,y;
    protected Color color;

    public Shape(int x, int y, Color c){
        this.x = x;
        this.y = y;
        this.color = c;
    }

    public int getX() {
        return x;
    }

    public int getY(){
        return y;
    }

    public void mouseClik(MouseEvent e){
        this.x = e.getX();
        this.y = e.getY();
    }

    public void moveShape(MouseEvent e, Point point){};

    public Color getColor() {
        return color;
    }

    public void changeColor(Color c){
        this.color = c;
    }

    public abstract void draw(Graphics g);

    public abstract boolean isInTheShape(int x, int y);

    public abstract void moveShape(MouseEvent e);
}
