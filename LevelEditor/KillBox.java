import java.awt.Color;
import java.awt.Graphics2D;

public class KillBox extends Sprite{

    public KillBox(int coorY, CameraOperator cam){
        super(0, coorY, 1500, 16, cam);
        spriteColor=Color.ORANGE;
    }

    public void paint(Graphics2D g2){
        g2.setColor(spriteColor);
        g2.fillRect(0, cam.getDeltaY(coorY), width, height);
    }

    public void paintToMouse(Graphics2D g2, int coorX, int coorY){
        g2.setColor(spriteColor);
        g2.fillRect(0, coorY, width, height);
    }
    public String[] getParametrs(){
        String[] data= new String[1];
        data[0]=String.valueOf(coorY);
        return data;
    } 
}
