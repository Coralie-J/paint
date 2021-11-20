package paint.shape;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class FreeHand extends Shape {

    protected class Point{
        public int x,y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        public String toString(){
            return "(" + x + "," + y + ")";
        }
    }

    public ArrayList<Point> points;

    public FreeHand(Color c) {
        super(0, 0, c);
        points = new ArrayList<>();
    }

    public void addPoint(int x, int y){
        this.points.add(new Point(x, y));
    }

    @Override
    public void draw(Graphics g) {

        for (int i=0; i < this.points.size() - 1; i++){
            Point p1 = this.points.get(i);
            Point p2 = this.points.get(i+1);
            g.setColor(this.color);
            g.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
    }

    @Override
    public boolean isInTheShape(int x, int y) {
        return false;
    }

    public void moveShape(int x, int y, MouseEvent e){
        int diff_x = (e.getX() - this.points.get(0).x) + (this.points.get(0).x - x);
        int diff_y =  (e.getY() - this.points.get(0).y) + (this.points.get(0).y - y);

        for (int i = 0; i < this.points.size(); i++) {
            this.points.get(i).x += diff_x;
            this.points.get(i).y += diff_y;
        }
    }


    @Override
    public void moveShape(MouseEvent e){
        int diff_x = e.getX() - this.points.get(0).x;
        int diff_y = e.getY() - this.points.get(0).y;
        for (int i = 0; i < this.points.size(); i++) {
            this.points.get(i).x += diff_x;
            this.points.get(i).y += diff_y;
        }
    }
}
