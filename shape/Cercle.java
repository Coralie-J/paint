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
        System.out.println(distance);
        return distance < this.rayon;
    }

    public void moveShape(MouseEvent e){
        Point centre = new Point(this.x + (this.width/2), this.y + (this.width/2));
        int diff_x = e.getX() - centre.x;
        int diff_y = e.getY() - centre.y;

        centre = new Point(e.getX() + diff_x, e.getY() + diff_y);
        this.y = (int) (centre.y - this.rayon);
        this.x = (int) (centre.x - this.rayon);
    }
}
