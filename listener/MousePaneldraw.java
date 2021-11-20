package paint.listener;

import paint.PaintPanel;
import paint.shape.Dashed_rectangle;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MousePaneldraw implements MouseMotionListener {

    @Override
    public void mouseDragged(MouseEvent e) {
        if (e.getSource().getClass() == PaintPanel.class) {

            PaintPanel main = (PaintPanel) e.getSource();
            if (main.getCursor().equals(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR))) {
                main.addFreeHand(e.getX(), e.getY());
            } else if (main.getCursor().equals(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR))) {
                if (main.getSelection() == null)
                    main.moveShapes(e);
                else
                    main.moveDrawingsSelection(e);

            } else if (main.getCursor().equals(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR))) {
                if (main.getSelection() == null) {
                    Dashed_rectangle rectangle = new Dashed_rectangle(e.getX(), e.getY());
                    main.addSelection(rectangle);
                } else {
                    int w = e.getX() - main.getSelection().getX();
                    int h = e.getY() - main.getSelection().getY();
                    main.getSelection().changeSize(w, h);
                    main.repaint();
                }
            }
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (e.getSource().getClass() == PaintPanel.class) {
            PaintPanel main = (PaintPanel) e.getSource();
            if (main.getSelection() != null) {
                if (main.getSelection().isInTheShape(e.getX(), e.getY()))
                    main.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
                else
                    main.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
            }
        }
    }
}
