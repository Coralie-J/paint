package paint.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MousePaneldraw implements MouseMotionListener {
    @Override
    public void mouseDragged(MouseEvent e) {
        System.out.println("Dragged");
        System.out.println(e.getPoint());

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println("Moved");
        System.out.println(e.getPoint());
    }
}
