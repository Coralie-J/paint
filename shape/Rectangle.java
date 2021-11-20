package paint.shape;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Rectangle extends Shape {

    protected int width, height;

    public Rectangle(int x, int y, Color c, int w, int h) {
        super(x, y, c);
        this.width = w;
        this.height = h;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.drawRect(this.x, this.y, this.width, this.height);
    }

    @Override
    public boolean isInTheShape(int x, int y) {
        Point p4 = new Point(this.x + this.width, this.y + this.height);
        return x > this.x && x < p4.x && y > this.y && y < p4.y;
    }

    public void moveShape(MouseEvent e){
        int diff_x = (e.getX() - this.x) - (this.width/2);
        int diff_y = (e.getY() - this.y) - (this.height/2);
        this.y += diff_y;
        this.x += diff_x;
    }

}
