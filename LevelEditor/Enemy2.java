import java.awt.Color;
import java.awt.Graphics2D;

public class Enemy2 extends Sprite{

    public int leftSide, rightSide;

    public Enemy2(int coorX, int coorY, int leftSide, int rightSide, CameraOperator cam) {
        super(coorX, coorY, tileSize, tileSize, cam);
        this.leftSide=leftSide;
        this.rightSide=rightSide;
        spriteColor=Color.MAGENTA;
    }

    @Override
    public void paint(Graphics2D g2) {
        g2.setColor(spriteColor);
        g2.fillRect(cam.getDeltaX(coorX), cam.getDeltaY(coorY), width, height);
        g2.fillRect(cam.getDeltaX(coorX-leftSide), cam.getDeltaY(coorY), 2, 10);
        g2.fillRect(cam.getDeltaX(coorX+rightSide+tileSize), cam.getDeltaY(coorY), 2, 10);
    }

    @Override
    public void paintToMouse(Graphics2D g2, int coorX, int coorY) {
        g2.setColor(spriteColor);
        g2.fillRect(coorX, coorY, width, height);
        g2.fillRect(coorX-leftSide, coorY, 2, 10);
        g2.fillRect(coorX+rightSide+tileSize, coorY, 2, 10);
    }
    
    public String[] getParametrs(){
        String[] data= new String[4];
        data[0]=String.valueOf(coorX);
        data[1]=String.valueOf(coorY);
        data[3]=String.valueOf(coorX-leftSide);
        data[2]=String.valueOf(coorX+rightSide+tileSize);
        return data;
    } 
    
}
