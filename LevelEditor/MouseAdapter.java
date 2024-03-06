import java.awt.event.MouseListener;

public class MouseAdapter implements MouseListener{

    public boolean mouseIsClicked;

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        //mouseIsClicked=true;
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        mouseIsClicked=true;
        
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        
    }
    
}
