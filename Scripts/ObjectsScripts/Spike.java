package Scripts.ObjectsScripts;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Scripts.BodyScripts.GamePanel;
import Scripts.SpriteScripts.SpriteWithCamera;
import Scripts.ToolsScripts.CameraOperator;

public class Spike extends SpriteWithCamera{

    GamePanel gp;
    private int sizeOfSpike;

    public Spike(GamePanel gp, int coorX, int coorY, CameraOperator cam) {
        super(coorX, coorY, (int)(gp.tileSize*1), (int)(gp.tileSize*1), cam);
        sizeOfSpike=(int)(gp.tileSize*1);
        try{
            this.img= ImageIO.read(getClass().getResourceAsStream("/All_Animation/Spikes/Spike_1.png"));
        }catch(IOException e){

        }
        this.img=scaler.scaleImage((BufferedImage)img, width, height, true);
        this.gp=gp;
    }

    public int getSizeOfSpike(){
        return sizeOfSpike;
    }

    public void update(){
        coorX=getScreenX();
        coorY=getScreenY();
     }
 
     /*public void colisionWithPlayer(int objXL, int objXR, int objYU, int objYD){
         if(objYD>=coorY && (objXR>=coorX && objXL<=coorX+width) && objYU<=coorY+height){
             gp.levelIsComplete=true;
         }
    }*/
}
