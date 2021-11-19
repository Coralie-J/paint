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
        return false;
    }

    @Override
    public void moveShape(MouseEvent e) {

    }
}
