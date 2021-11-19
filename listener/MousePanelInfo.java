package paint.listener;

import paint.PaintPanel;
import paint.shape.Dashed_rectangle;
import paint.shape.FreeHand;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MousePanelInfo implements MouseListener {

    public void mouseClicked(MouseEvent mouseEvent){
        if (mouseEvent.getSource().getClass() == PaintPanel.class){
            PaintPanel panel = (PaintPanel) mouseEvent.getSource();
            if (panel.getCursor().equals(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR)))
                panel.mouseClik(mouseEvent);
            else {
                panel.setCursor(panel.getCursor());
                panel.manageOutOfSelection();
            }
        }
    }


    public void mouseEntered(MouseEvent e){}

    @Override
    public void mouseExited(MouseEvent e) {}

    public void mousePressed(MouseEvent e){}

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource().getClass() == PaintPanel.class){
            PaintPanel panel = (PaintPanel) e.getSource();
            if (panel.getCursor().equals(Cursor.getDefaultCursor())) {
                FreeHand drawing = new FreeHand(panel.getCouleur());
                panel.getDrawings().add(drawing);
            }
        }
    }

}
