package Scripts.ObjectsScripts;

import java.awt.Graphics2D;

import Scripts.BodyScripts.GamePanel;
import Scripts.ToolsScripts.KeyHandler;

public class Player extends PlayerInit{

    public int playerRight, playerDown, playerUp, playerLeft;
    private int playerSpeedX;
    public int jumpSpeed, fallSpeed;
    private float realJumpSpeed, realFallSpeed;
    private float realSpeedX;

    private int standartFallSpeed, standartJumpSpeed, standartSpeedX, maxSpeedX, maxFallSpeed;
    public boolean isDead=false;
    public boolean forceIsAdd=false;
    private boolean jumpSoundIsPlayed=false;
    private int forceSpeed=0;
    private float realForceSpeed=0, forceSpeedDivider=1;

    private boolean highJump=false;
    public boolean deathAnimationIsEnded;

    private int killBox;
    private boolean isKillBox=false;

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH, int playerX, int playerY) {
        super(playerX, playerY, gp.tileSize, gp.tileSize, "Player", 4, 4);
        cam=gp.getCam();
        this.gp = gp;
        this.keyH = keyH;

        setValues();
        deathAnimationIsEnded=false;

        screenWidth=gp.screenWidth;
        screenHeight=gp.screenHeight;

        setPlayerParameters();
        
        playerX-=screenWidth/2;
        playerY-=screenHeight/2;
        cameraX=screenWidth/2-width;
        cameraY=screenHeight/2-height;

        setPlayerBoarders(cameraX, cameraY);

        startPointX=playerX+cameraX;
        startPointY=playerY+cameraY;
        deltaX=startPointX-screenWidth+width*2;
        deltaY=startPointY-screenHeight-height;
    }

    private void setValues(){
        //setObjectScale(4);
        //objectScale=4;
        isRight=true;
        isFall=true;

    }

    public void setKillBox(int coorY){
        isKillBox=true;
        killBox=coorY;
    }

    private void setPlayerParameters(){
        standartSpeedX=3;
        maxSpeedX=5;
        playerSpeedX = standartSpeedX;
        realSpeedX = playerSpeedX;

        standartFallSpeed=3;
        maxFallSpeed=12;
        fallSpeed=standartFallSpeed;
        realFallSpeed=fallSpeed;
        
        standartJumpSpeed=15;
        jumpSpeed=standartJumpSpeed;

        stopDroping=false;
    }

    private void setPlayerBoarders(int cameraX, int cameraY){
        playerUp=cameraY;
        playerDown=cameraY+height;
        playerLeft=cameraX;
        playerRight=cameraX+width;
    }
    
    public void updateCamera(int newWidth, int newHeight){
        cameraX=newWidth/2-width;
        cameraY=newHeight/2-height;
        setPlayerBoarders(cameraX, cameraY);
        deltaX=startPointX-newWidth+newWidth/2+width*2;
        deltaY=startPointY-newHeight+newHeight/2-height;
        screenHeight=newHeight;
        screenWidth=newWidth;
        
    }


    public void setJumpSpeedToFallSpeed(){
        jumpSpeed=standartFallSpeed;
    }

    private void resetJumpSpeed(){
        jumpSpeed=standartJumpSpeed;
        realJumpSpeed=jumpSpeed;
    }

    private void resetFallSpeed(){
        fallSpeed=standartFallSpeed;
        realFallSpeed=fallSpeed;
    }


    public void addVerticalForce(int force){
        coorY-=force;
    }

    public void setForceSpeed(float forceSpeed, float forceSpeedDivider){
        if(!forceIsAdd){
            this.forceSpeedDivider=forceSpeedDivider;
            this.realForceSpeed=forceSpeed;
            this.forceSpeed=(int)realFallSpeed;
        }
        forceIsAdd=true;
    }

    public void playerDeathUpdate(){
        animState=stateOfAnimation.Die;
        keyH.jumpPressed=false;
        keyH.leftPressed=false;
        keyH.rightPressed=false;
        if(!stopDroping){
            coorY+=fallSpeed;
        }
        if(stopDroping && numOfFrame!=quanOfFrames-1){
            
            updateAnimation(10);
            
        }else deathAnimationIsEnded=true;
    }

    public int getCoorY(){
        return coorY;
    }

    public void update() {
        
        //deathAnimationIsEnded=false;
        int player1X = coorX;
        playerSpeedX = (int) realSpeedX;


        if(keyH.jumpPressed){
            if(!jumpSoundIsPlayed)
                gp.playSE(3, gp.soundOp);
            jumpSoundIsPlayed=true;
            
            highJump=true;
            
        }
        if(highJump && jumpSpeed>standartFallSpeed){
            
            stopDroping=false;
            isFall=false;
            coorY-=jumpSpeed;
            realJumpSpeed=(float) (realJumpSpeed/1.03);
            jumpSpeed=(int) realJumpSpeed;
        }else
        if((highJump && jumpSpeed<=standartFallSpeed) || (!highJump && !forceIsAdd)){
            isFall=true;
            if(fallSpeed<maxFallSpeed){
            realFallSpeed*=1.07;
            setJumpSpeedToFallSpeed();
            fallSpeed=(int) realFallSpeed;}
        }
        if(stopDroping){
            if(!keyH.jumpPressed){
                resetJumpSpeed();
                jumpSoundIsPlayed=false;
            }
            
            
            resetFallSpeed();
        }

        if(forceIsAdd){
            resetFallSpeed();
            isFall=false;
            coorY-=forceSpeed;
            realForceSpeed/=forceSpeedDivider;
            forceSpeed=(int)realForceSpeed;
            if(forceSpeed<=standartFallSpeed){
                forceIsAdd=false;
            }
        }

        //System.out.println(jumpSpeed+"   "+fallSpeed);

        highJump=false;
       
        if (!stopDroping) {
            coorY += fallSpeed;
        }


        /*if (keyH.upPressed) {
            coorY -= playerSpeedY;
        }
        if (keyH.downPressed) {
            coorY += playerSpeedY;
        }*/


        if (keyH.leftPressed) {
            if(isRight) numOfFrame=0;
            isRight=false;
            coorX -= playerSpeedX;
        }
        if (keyH.rightPressed) {
            if(!isRight) numOfFrame=0;
            isRight=true;
            coorX += playerSpeedX;
        }
        if (player1X == coorX) {
            animState=stateOfAnimation.Idle;
            realSpeedX = standartSpeedX;
        } else if (player1X != coorX && realSpeedX <= maxSpeedX) {
            animState=stateOfAnimation.Run;
            realSpeedX *= 1.05;
            player1X = coorX;
        }
        
        stopDroping = false;
        
        updateAnimation(7);
        /*if(isDead){
            numOfFrame=0;
            animationStopper=0;
            animState=stateOfAnimation.Die;
            //isFall=false;
            
        }*/

        cam.setDeltaX(cam.getStartPointX()-coorX-deltaX);
        cam.setDeltaY(cam.getStartPointY()-coorY-deltaY);
        if(isKillBox){
            if(coorY>=killBox) isDead=true;
        }
        
        
    }

    public void resetAnimation(){
        numOfFrame=0;
        animationStopper=0;
    }

    public void colisionDetection(int objX, int objY, int objWidth, int objHeight) {
        int objXR = objX + objWidth;
        int objYD = objY + objHeight;

        if (keyH.leftPressed && playerLeft < objXR+1 && playerDown > objY+2 && playerLeft+3> objXR-10 && playerUp < objYD+3) {
            objXR-=3;
            coorX+=playerSpeedX;
        }
        if (keyH.rightPressed && playerRight > objX-1  && playerDown > objY+2 && playerRight-4 < objX+10 && playerUp < objYD+3) {
            objX+=3;
            coorX-=playerSpeedX;
        }

        if (playerDown >= objY-1 && (playerRight-8 > objX+1 && playerLeft+3 < objXR-2) && playerUp < objYD-2) {
            if(playerDown>objY){
                coorY-=playerDown-objY;
            }
            stopDroping = true;
        }
        
        cam.setDeltaX(cam.getStartPointX()-coorX-deltaX);
        cam.setDeltaY(cam.getStartPointY()-coorY-deltaY); 
    }

    public boolean colisionPlat(int objX, int objY, int objWidth, int objHeight){
        int objXR = objX + objWidth;
        int objYD = objY + objHeight;
        if (playerDown > objY+fallSpeed && (playerRight - 8 > objX + 1 && playerLeft + 3 < objXR - 2) && playerUp < objYD+10) {
            return true;
        }
        else return false;
    }

    public void changeCoorY(int deltaY){
        coorY+=deltaY;
    }

    public int[] getPlayerBoarders(){
        int[] boarders =new int[4];
        boarders[0]=playerLeft;
        boarders[1]=playerUp;
        boarders[2]=playerRight;
        boarders[3]=playerDown;
        return boarders;
    }

    public void paintDebug(Graphics2D g2){
        g2.drawRect(playerLeft, playerUp, playerRight-playerLeft, playerDown-playerUp);
    }

}
