package Scripts.SpriteScripts;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Scripts.ToolsScripts.CameraOperator;
import Scripts.ToolsScripts.ImageScaler;

public class SpriteWithCamera extends Sprite implements Camera{

    protected CameraOperator cam;
    protected int startPointX, startPointY;
    protected ImageScaler scaler=new ImageScaler();

    public SpriteWithCamera(int coorX, int coorY, int width, int height, BufferedImage img, CameraOperator cam) {
        super(coorX, coorY, width, height, img);
        this.img=scaler.scaleImage(img, width, height, false);
        this.cam=cam;
        this.startPointX=coorX;
        this.startPointY=coorY;
    }

    public SpriteWithCamera(int coorX, int coorY, int width, int height, CameraOperator cam) {
        super(coorX, coorY, width, height, null);
        //this.img=scaler.scaleImage(img, width, height, false);
        this.cam=cam;
        this.startPointX=coorX;
        this.startPointY=coorY;
    }

    public void paint(Graphics2D g2) {
        g2.drawImage(img, getScreenX(), getScreenY(), null);
        //System.out.println(getScreenX());
        //System.out.println(getScreenY());
    }

    public int getScreenX() {
        return cam.getObjectCoorX(startPointX);
    }

    public int getScreenY() {
        return cam.getObjectCoorY(startPointY);
    }

    
    
}
