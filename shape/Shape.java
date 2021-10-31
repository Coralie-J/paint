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

    public void mouseClik(MouseEvent e){
        this.x = e.getX();
        this.y = e.getY();
    }

    public Color getColor() {
        return color;
    }

    public void changeColor(Color c){
        this.color = c;
    }

    public abstract void draw(Graphics g);
}
