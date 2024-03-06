import java.awt.Color;
import java.awt.Graphics2D;

public class FinishPoint extends Sprite{

    public FinishPoint(int coorX, int coorY, CameraOperator cam) {
        super(coorX, coorY, (int)(tileSize*1.5), (int)(tileSize*1.5), cam);
        spriteColor=Color.GREEN;
    }

    @Override
    public void paint(Graphics2D g2) {
        g2.setColor(spriteColor);
        g2.fillRect(cam.getDeltaX(coorX), cam.getDeltaY(coorY), width, height);
    }

    @Override
    public void paintToMouse(Graphics2D g2, int coorX, int coorY) {
        g2.setColor(spriteColor);
        g2.fillRect(coorX, coorY, width, height);
    }

    public String[] getParametrs(){
        String[] data= new String[2];
        data[0]=String.valueOf(coorX);
        data[1]=String.valueOf(coorY);
        return data;
    } 
    
}