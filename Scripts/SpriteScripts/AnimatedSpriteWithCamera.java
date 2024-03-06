package Scripts.SpriteScripts;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import Scripts.ToolsScripts.CameraOperator;

public class AnimatedSpriteWithCamera extends AnimatedSprite implements Camera{

    protected CameraOperator cam;

    protected int startPointX, startPointY;

    public AnimatedSpriteWithCamera(int coorX, int coorY, int width, int height, String nameOfObject,
            int quanOfFrames, Double objectScale, CameraOperator cam) {
        super(coorX, coorY, width, height, nameOfObject, quanOfFrames, objectScale);
        startPointX=coorX;
        startPointY=coorY;
        this.cam=cam;
    }

    
    public void getFrames() {
        if(hasLeftAnimation){
            runAnimation.addAll(getFrames.getRunRightFrames(quanOfFrames));
            runAnimation.addAll(getFrames.getRunLeftFrames(quanOfFrames));
        }
        else{
            runAnimation.addAll(getFrames.getRunRightFrames(quanOfFrames));
        }
        for(BufferedImage image:runAnimation){
            //System.out.println(objectScale);
            scaledImages.add(scaler.scaleImage(image, (int)(image.getWidth()*objectScale), (int)(image.getHeight()*objectScale), true));
           // image=scaler.scaleImage(image, (int)(image.getWidth()*objectScale), (int)(image.getHeight()*objectScale));
           // System.out.println(image.getWidth());
        }
        runAnimation.clear();
        for(BufferedImage image:scaledImages){
            runAnimation.add(image);
        }
        scaledImages.clear();
    }

    
    public void paint(Graphics2D g2) {
        getObjectFrame();
        //System.out.println(img.getWidth(null));
        g2.drawImage(img, getScreenX(), getScreenY()+(width-img.getHeight(null)), null);
        //g2.drawRect(getScreenX(), getScreenY()+(width-getRealHeight()), getRealWidth(), getRealHeight());
        //g2.drawRect(getScreenX(), getScreenY(), getRealWidth(), getRealHeight());
    }

    
    public int getScreenX() {
        return cam.getObjectCoorX(startPointX);   
    }


    public int getScreenY() {
        return cam.getObjectCoorY(startPointY);
    }
    
}
