package paint.shape;

import java.awt.*;

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
}
