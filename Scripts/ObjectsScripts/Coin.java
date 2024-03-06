package Scripts.ObjectsScripts;

import Scripts.BodyScripts.GamePanel;
import Scripts.SpriteScripts.AnimatedSpriteWithCamera;

public class Coin extends AnimatedSpriteWithCamera{

    public boolean isExisting=true;

   // private GamePanel gp;

    public Coin(GamePanel gp, int coorX, int coorY) {
        super(coorX, coorY, gp.tileSize, gp.tileSize, "Coin", 5, 4.0, gp.getCam());
        //this.gp=gp;
        //setObjectScale(4);
        //objectScale=4;
    }

    public void update(){
        coorX=getScreenX()+width-getRealHeight();
        coorY=getScreenY()+width-getRealHeight();
        updateAnimation(5);
     }
 
     public void colisionWithPlayer(int objXL, int objXR, int objYU, int objYD){
         if(objYD>=coorY && (objXR>=coorX && objXL<=coorX+getRealWidth()) && objYU<=coorY+getRealHeight()){
             isExisting=false;
             //gp.score++;
         }
 
     }
    
}
