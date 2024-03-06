package Scripts.ObjectsScripts;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Scripts.BodyScripts.GamePanel;
import Scripts.SpriteScripts.SpriteWithCamera;
import Scripts.ToolsScripts.CameraOperator;

public class FinishFlag extends SpriteWithCamera{

    //private BufferedImage img;
    //private GamePanel gp;

    public FinishFlag(GamePanel gp, int coorX, int coorY, CameraOperator cam) {
        super(coorX, coorY, (int)(gp.tileSize*1.5), (int)(gp.tileSize*1.5), cam);
        try{
            this.img= ImageIO.read(getClass().getResourceAsStream("/All_Animation/Finish_point/Finish_flag.png"));
        }catch(IOException e){

        }
        this.img=scaler.scaleImage((BufferedImage)img, width, height, true);
        //this.gp=gp;
    }

    public void update(){
        coorX=getScreenX();
        coorY=getScreenY();
     }
 
     public boolean colisionWithPlayer(int objXL, int objXR, int objYU, int objYD){
         if(objYD>=coorY && (objXR>=coorX && objXL<=coorX+width) && objYU<=coorY+height){
            return true; 
            //gp.levelIsComplete=true;
         }else return false;
    }
    
}
