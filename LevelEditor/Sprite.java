import java.awt.Color;
import java.awt.Graphics2D;

public abstract class Sprite {
    public int coorX, coorY, width, height;
    public CameraOperator cam;
    public Color spriteColor;
    public static int tileSize=16*3;

    public Sprite(int coorX, int coorY, int width, int height, CameraOperator cam){
        this.coorX=coorX;
        this.coorY=coorY;
        this.width=width;
        this.height=height;
        this.cam=cam;
        spriteColor=Color.BLUE;
    }


    public abstract void paint(Graphics2D g2);

    public abstract void paintToMouse(Graphics2D g2, int coorX, int coorY);

    public abstract String[] getParametrs();
}
