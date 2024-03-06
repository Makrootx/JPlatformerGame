package Scripts.ObjectsScripts;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Scripts.SpriteScripts.AnimatedSprite;
import Scripts.ToolsScripts.CameraOperator;

public class PlayerInit extends AnimatedSprite{

    //protected ArrayList<BufferedImage> idleAnimation = new ArrayList<BufferedImage>();
    
    private BufferedImage jumpLeft1;
    private BufferedImage jumpLeft2;

    private BufferedImage jumpRight1;
    private BufferedImage jumpRight2;

    protected boolean isFall;

    protected enum stateOfAnimation{
        Run,
        Idle,
        Die,
    };
    
    protected stateOfAnimation animState;

    protected boolean stopDroping;

    protected int deltaX, deltaY, startPointX, startPointY;

    public int screenWidth, screenHeight, cameraX, cameraY;

    protected CameraOperator cam;

    public PlayerInit(int coorX, int coorY, int width, int height, String nameOfObject, int quanOfFrames, double objectScale) {
        super(coorX, coorY, width, height, nameOfObject, quanOfFrames, objectScale);
    }

    public void getFrames() {
        runAnimation.addAll(getFrames.getRunRightFrames(quanOfFrames));
        runAnimation.addAll(getFrames.getRunLeftFrames(quanOfFrames));
        idleAnimation.addAll(getFrames.getIdleRightFrames(quanOfFrames));
        idleAnimation.addAll(getFrames.getIdleLeftFrames(quanOfFrames));
        dieAnimation.addAll(getFrames.getDieRightFrames(quanOfFrames));
        dieAnimation.addAll(getFrames.getDieLeftFrames(quanOfFrames));
        try {
            jumpLeft1=ImageIO.read(getClass().getResourceAsStream("/All_Animation/Player/Player_jump_left_1.png"));
            jumpLeft2=ImageIO.read(getClass().getResourceAsStream("/All_Animation/Player/Player_jump_left_2.png"));
            jumpRight1=ImageIO.read(getClass().getResourceAsStream("/All_Animation/Player/Player_jump_right_1.png"));
            jumpRight2=ImageIO.read(getClass().getResourceAsStream("/All_Animation/Player/Player_jump_right_2.png"));
        } catch (IOException e) {
            
            e.printStackTrace();
    }
}

    @Override
    protected int getRealWidth(){
        return (int)(img.getWidth(null)*objectScale);
        //return img.getWidth(null);
    }
    @Override
    protected int getRealHeight(){
        return (int)(img.getHeight(null)*objectScaleHeight);
        //return img.getHeight(null);
    }

    public void paint(Graphics2D g2) {
        if(stopDroping){
            switch(animState){
                case Idle:
                if(isRight){
                    img=idleAnimation.get(numOfFrame);
                }else{
                    img=idleAnimation.get(numOfFrame+quanOfFrames);
                }
                break;
                case Run:
                if(isRight){
                    img=runAnimation.get(numOfFrame);
                }else{
                    img=runAnimation.get(numOfFrame+quanOfFrames);
                }
                break;
                case Die:
                if(isRight){
                    img=dieAnimation.get(numOfFrame);

                }
                else{
                    img=dieAnimation.get(numOfFrame+quanOfFrames);
                }
                //System.out.println(numOfFrame);
                break;
            }}
            else if (animState!=stateOfAnimation.Die){
                if(isFall){
                    if(isRight){
                        img=jumpRight2;
                    }else img=jumpLeft2;
                }else{
                    if(isRight){
                        img=jumpRight1;
                    }else img=jumpLeft1;
                }
            }
            
            g2.drawImage(img, cameraX, cameraY+(width-getRealHeight()), getRealWidth(), getRealHeight(), null);
    }
    
}
