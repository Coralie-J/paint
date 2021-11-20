package paint.shape;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Cercle extends Shape {

    protected int width;
    protected float rayon;

    public Cercle(int x, int y, Color c, int width) {
        super(x, y, c);
        this.width = width;
        this.rayon = (float) this.width/2;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.drawOval(this.x, this.y, this.width, this.width);
    }

    @Override
    public boolean isInTheShape(int x, int y) {
        Point centre = new Point((int) (this.x + this.rayon), (int) (this.y + this.rayon));
        float distance = ( ((centre.x - x) * (centre.x - x)) + ((centre.y - y) * (centre.y - y)));
        distance = (float) Math.sqrt(distance);
        return distance < this.rayon;
    }

    public void moveShape(MouseEvent e){
        int diff_x = (int) ((e.getX() - this.x) - this.rayon);
        int diff_y = (int) ((e.getY() - this.y) - this.rayon);
        this.y += diff_y;
        this.x += diff_x;
    }
}
