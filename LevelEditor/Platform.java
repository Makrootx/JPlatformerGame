import java.awt.Color;
import java.awt.Graphics2D;

public class Platform extends Sprite{

    public Platform(int coorX, int coorY, int width, int height, CameraOperator cam){
        super(coorX, coorY, width, height, cam);
        spriteColor=Color.BLUE;
    }

    public void paint(Graphics2D g2){
        g2.setColor(spriteColor);
        g2.fillRect(cam.getDeltaX(coorX), cam.getDeltaY(coorY), width, height);
    }

    public void paintToMouse(Graphics2D g2, int coorX, int coorY){
        g2.setColor(spriteColor);
        g2.fillRect(coorX, coorY, width, height);
    }
    public String[] getParametrs(){
        String[] data= new String[4];
        data[0]=String.valueOf(coorX);
        data[1]=String.valueOf(coorY);
        data[2]=String.valueOf(width/tileSize);
        data[3]=String.valueOf(height/tileSize);
        return data;
    } 
}
