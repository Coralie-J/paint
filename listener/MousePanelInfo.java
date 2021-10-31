package paint.listener;

import paint.PaintPanel;
import paint.shape.FreeHand;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MousePanelInfo implements MouseListener {

    public void mouseClicked(MouseEvent mouseEvent){
        if (mouseEvent.getSource().getClass() == PaintPanel.class){
            PaintPanel panel = (PaintPanel) mouseEvent.getSource();
            panel.mouseClik(mouseEvent);
        }

    }

    public void mouseEntered(MouseEvent e){

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e){}

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource().getClass() == PaintPanel.class){
            PaintPanel panel = (PaintPanel) e.getSource();
            FreeHand drawing = new FreeHand(panel.getCouleur());
            panel.getDrawings().add(drawing);
        }
    }

}
