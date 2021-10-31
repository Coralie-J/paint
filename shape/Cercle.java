package paint.shape;

import java.awt.*;

public class Cercle extends Shape {

    protected int width;

    public Cercle(int x, int y, Color c, int width) {
        super(x, y, c);
        this.width = width;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(this.color);
        g.drawOval(this.x, this.y, this.width, this.width);
    }
}
