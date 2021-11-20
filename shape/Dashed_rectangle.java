package paint.shape;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class Dashed_rectangle extends Rectangle {

    public Dashed_rectangle(int x, int y) {
        super(x, y, Color.GRAY, 0, 0);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle2D rect = new Rectangle2D.Float( x, y, this.width, this.height);
        float[] dash = { 5F, 5F };
        Stroke dashedStroke = new BasicStroke( 1f, BasicStroke.CAP_SQUARE,
                BasicStroke.JOIN_MITER, 1F, dash, 0F );
        g2.setColor(this.color);
        g2.draw(dashedStroke.createStrokedShape(rect));
    }

    public void changeSize(int h, int w){
        this.width = w;
        this.height = h;
    }

    public void mouseClick(MouseEvent e){
        int diff_x = e.getX() - this.x;
        int diff_y = e.getY() - this.y;
        this.x += diff_x;
        this.y += diff_y;
    }

    public boolean verifyShapeIntheRect(FreeHand h){
        int i=0;
        for (FreeHand.Point p : h.points){
            if (this.isInTheShape(p.x, p.y)){
                i++;
            }
        }
        return h.points.size() == i;
    }

    public boolean verifyShapeIntheRect(Chaine c){
        return this.isInTheShape(c.x, c.y) && this.isInTheShape(c.x + c.chaine.length(), c.y);
    }

    public int getWidth(){
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }
}
