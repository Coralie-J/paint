package paint.shape;

import java.awt.*;
import java.util.ArrayList;

public class FreeHand extends Shape {

    private class Point{
        public int x,y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
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

    public void printPoint(){
        System.out.println(
                this.points.get(points.size() - 1).x + " " +
                this.points.get(points.size() - 1).y
        );
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
}
