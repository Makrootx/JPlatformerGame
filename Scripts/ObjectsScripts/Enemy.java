package Scripts.ObjectsScripts;

import java.awt.image.BufferedImage;

import Scripts.BodyScripts.GamePanel;
import Scripts.SpriteScripts.AnimatedSpriteWithCamera;

public class Enemy extends AnimatedSpriteWithCamera{

    protected int leftSide, rightSide;
    private int enemySpeedX;
    private boolean stopDroping;
    private int enemyR, enemyD, enemyU, enemyL;
    private BufferedImage deathFrame, deathFrameScaled;
    public boolean isDead;
    GamePanel gp;

    public int deathFrames=0, deathCount=0;

    public Enemy(GamePanel gp, int coorX, int coorY, int rightSide, int leftSide) {
        super(coorX, coorY, gp.tileSize, gp.tileSize, "Enemy", 10, 3.5, gp.getCam());
        this.gp=gp;
        this.leftSide=leftSide;
        this.rightSide=rightSide;
        deathFrame=runAnimation.get(0);
        deathFrameScaled=scaler.scaleImage(deathFrame, deathFrame.getWidth(), (int)(deathFrame.getHeight()*0.1), true);
        setEnemyParameters();
        setEnemyBoarders();
        
    }

    private void setEnemyParameters(){
        //setObjectScale(3.5);
        //objectScale=3.5;
        isRight=true;
        enemySpeedX=2;
        stopDroping=true;
    }

    private void setEnemyBoarders(){
        enemyU=startPointY;
        enemyL=startPointX;
        enemyD=startPointY+getRealHeight();
        enemyR=startPointX+getRealWidth();
    }

    public void update(){
        if(!stopDroping){
            startPointY+=3;
        }
        
        if(startPointX+width>=rightSide && isRight){
            isRight=false;
        }
        if(startPointX<=leftSide && !isRight){
            isRight=true;
        }
        if(isRight){
            startPointX+=enemySpeedX;
        }
        else startPointX-=enemySpeedX;
        updateAnimation(5);
        setEnemyBoarders();
        coorX=getScreenX()+width-getRealHeight();
        coorY=getScreenY()+width-getRealHeight();
        stopDroping=false;

    }

    public void updateDeath(int deathStopper){
        
        setEnemyBoarders();
        if(deathCount>=deathStopper){
            objectScaleHeight=0.5;
            img=deathFrameScaled;      
            //startPointY=enemyD;
            deathFrames++;
            deathCount=0;
        }
        deathCount++;
    }

    public void colisionDetection(int objX, int objY, int objWidth, int objHeight) {
        int objXR = objX + objWidth;
        int objYD = objY + objHeight;
        if (enemyD >= objY-1 && (enemyR-8 > objX+1 && enemyL+3 < objXR-2) && enemyU < objYD-2) {
            if(enemyD>objY){
                startPointY-=enemyD-objY;
                //System.out.println("helo "+ coorY);
            }
            stopDroping = true;
        }
        if (!isRight && enemyL< objXR && enemyD > objY+2 && enemyL+3> objXR-10 && enemyU < objYD+3) {
            // System.out.println("right");
            //coorX += (int) realSpeed;
            isRight=true;
            // playerY += playerFallSpeed;
        }
        if (isRight && enemyR > objX  && enemyD > objY+2 && enemyR-4 < objX+10 && enemyU < objYD+3) {
            // System.out.println("left");
            //coorX -= (int) realSpeed;
            isRight=false;
            // playerY += playerFallSpeed;
        }
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
