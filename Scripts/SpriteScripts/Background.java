package Scripts.SpriteScripts;

import java.awt.image.BufferedImage;

import Scripts.ToolsScripts.CameraOperator;

public class Background extends SpriteWithCamera{

    public Background(int coorX, int coorY, int width, int height, BufferedImage img, CameraOperator cam) {
        super(coorX, coorY, width, height, img, cam);
        //TODO Auto-generated constructor stub
    }

    public void update(int screenWidth, int screenHeight){
        startPointY=screenHeight+coorY;
        if (screenHeight>=700){
            startPointY=screenHeight+coorY-110;
        }
    }

    @Override
    public int getScreenX() {
        // TODO Auto-generated method stub
        return cam.getBackgroundCoorX(startPointX);
    }

    @Override
    public int getScreenY(){
        return cam.getBackgroundCoorY(startPointY);
    }
    
}
