package Scripts.ObjectsScripts;

import java.awt.image.BufferedImage;

import Scripts.BodyScripts.GamePanel;
import Scripts.SpriteScripts.AnimatedSpriteWithCamera;

public class Enemy2 extends AnimatedSpriteWithCamera{

    protected int leftSide, rightSide;
    private int enemySpeedX;
    //private boolean stopDroping;
    //private int enemyR, enemyD, enemyU, enemyL;
    private BufferedImage deathFrame, deathFrameScaled;
    public boolean isDead;
    private int speedStopper=3;
    private int speedCounter=0;
    GamePanel gp;

    public int deathFrames=0, deathCount=0;

    public Enemy2(GamePanel gp, int coorX, int coorY, int rightSide, int leftSide) {
        super(coorX, coorY, gp.tileSize, gp.tileSize, "Enemy2", 6, 3.8, gp.getCam());
        this.gp=gp;
        this.leftSide=leftSide;
        this.rightSide=rightSide;
        deathFrame=runAnimation.get(0);
        deathFrameScaled=scaler.scaleImage(deathFrame, deathFrame.getWidth(), (int)(deathFrame.getHeight()*0.1), true);
        setEnemyParameters();
        //setEnemyBoarders();
        
    }

    private void setEnemyParameters(){
        //setObjectScale(3.5);
        //objectScale=3.5;
        isRight=true;
        enemySpeedX=2;
        //stopDroping=true;
    }

    /*private void setEnemyBoarders(){
        enemyU=startPointY;
        enemyL=startPointX;
        enemyD=startPointY+getRealHeight();
        enemyR=startPointX+getRealWidth();
    }*/

    public void update(){
        //if(!stopDroping){
            //startPointY+=3;
        //}
        
        if(startPointX+width>=rightSide && isRight){
            isRight=false;
        }
        if(startPointX<=leftSide && !isRight){
            isRight=true;
        }
        speedCounter++;
        if(speedCounter>speedStopper){
            speedCounter=0;
            if(isRight){
            startPointX+=enemySpeedX;
            }
            else startPointX-=enemySpeedX;
        }else{
            if(isRight){
            startPointX+=1;
            }
            else startPointX-=1;
        }
        
        updateAnimation(7);
        //setEnemyBoarders();
        coorX=getScreenX()+width-getRealHeight();
        coorY=getScreenY()+width-getRealHeight();
        //stopDroping=false;

    }

    public void updateDeath(int deathStopper){
        
        //setEnemyBoarders();
        if(deathCount>=deathStopper){
            objectScaleHeight=0.5;
            img=deathFrameScaled;      
            //startPointY=enemyD;
            deathFrames++;
            deathCount=0;
        }
        deathCount++;
    }

    public void colisionWithPlayer(int objXL, int objXR, int objYU, int objYD){
        //int objXR = pl.coorX + objWidth;
        //int objYD = pl.coorY + objHeight;

        //System.out.println("playe "+pl.coorX+" "+pl.coorY+" "+objYD+" "+objXR);
        //System.out.println("enem "+startPointY+" "+objYD);

        if(objYD<=coorY+18 && objXR>=coorX && objXL<=coorX+getRealWidth() && objYD>=coorY-15){
            isDead=true;
            //System.out.println("kj"+objYD+" "+startPointY);
        }
        if(objYD>=coorY+20 && objXR>=coorX && objXL<=coorX+getRealWidth() && objYU<=coorY+getRealHeight() && !isDead){
            gp.setPlayerDeath();
        }
        
    }
    
}