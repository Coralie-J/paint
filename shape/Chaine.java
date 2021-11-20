package paint.shape;

import java.awt.*;
import java.awt.event.MouseEvent;

public class Chaine extends Shape {

    protected String chaine;

    public Chaine(int x, int y, Color c, String chaine) {
        super(x, y, c);
        this.chaine = chaine;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.drawString(chaine, this.x, this.y);
    }

    @Override
    public boolean isInTheShape(int x, int y) {
        return x > this.x && x < this.x + chaine.length() && y > this.y && y <= this.y + 2;
    }

    @Override
    public void moveShape(MouseEvent e) {
        int diff_x = (e.getX() - this.x) - (chaine.length()/2);
        int diff_y = (e.getY() - this.y);
        this.y += diff_y;
        this.x += diff_x;
    }

    public void moveShape(int x, int y, MouseEvent e){
        int diff_x = (e.getX() - this.x) + (this.x - x);
        int diff_y =  (e.getY() - this.y) + (this.y - y);
        this.x += diff_x;
        this.y += diff_y;

    }
}
