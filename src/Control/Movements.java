package Control;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Movements implements MouseListener{
    Component comp;

    public Movements(Component comp){
        this.comp = comp;
    }

//İŞLEM YÖNTEMLERİ:
    @Override
    public void mouseClicked(MouseEvent e) {
    }
    @Override
    public void mousePressed(MouseEvent e) {
    }
    @Override
    public void mouseReleased(MouseEvent e) {
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        GUISeeming.mouseOnMovementRefresh(comp);
    }
    @Override
    public void mouseExited(MouseEvent e) {
        GUISeeming.mouseOffMovementRefresh(comp);
    }
}
