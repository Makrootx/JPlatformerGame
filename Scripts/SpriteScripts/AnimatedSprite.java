package Scripts.SpriteScripts;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Scripts.ToolsScripts.GetAnimationFrames;
import Scripts.ToolsScripts.ImageScaler;

public abstract class AnimatedSprite extends Sprite implements Animated{

    protected ArrayList<BufferedImage> runAnimation = new ArrayList<BufferedImage>();
    protected ArrayList<BufferedImage> idleAnimation = new ArrayList<BufferedImage>();
    protected ArrayList<BufferedImage> dieAnimation = new ArrayList<BufferedImage>();
    protected ArrayList<BufferedImage> scaledImages=new ArrayList<BufferedImage>();
      //      runLeft = new ArrayList<BufferedImage>();

    //protected ArrayList <ArrayList> animations =new ArrayList<ArrayList>(); 

    protected boolean hasLeftAnimation;

    protected String nameOfObject;

    protected boolean isRight;
    protected int quanOfFrames, numOfFrame;
    protected int animationStopper;
    protected double objectScale;
    protected double objectScaleHeight;
    protected ImageScaler scaler=new ImageScaler();

    protected GetAnimationFrames getFrames;

    public AnimatedSprite(int coorX, int coorY, int width, int height, String nameOfObject, int quanOfFrames, Double objectScale) {
        super(coorX, coorY, width, height, null);
        setObjectScale(objectScale);
        this.nameOfObject=nameOfObject;
        this.quanOfFrames=quanOfFrames;
        this.getFrames=new GetAnimationFrames(nameOfObject);
        if (nameOfObject!="Coin"){
            hasLeftAnimation=true;
        }
        else hasLeftAnimation=false;
        getFrames();
        getObjectFrame();
    }

    protected void setObjectScale(double objectScale){
        this.objectScale=objectScale;
        objectScaleHeight=objectScale;
    }

    protected void getObjectFrame(){
        if (hasLeftAnimation) {
            if (isRight)
                img = runAnimation.get(numOfFrame);
            else
                img = runAnimation.get(numOfFrame+quanOfFrames);
        } else {
            img = runAnimation.get(numOfFrame);
        }
    }

    protected void updateAnimation(int stopper){
        animationStopper++;
        if(animationStopper>stopper){
            numOfFrame++;
            animationStopper=0;
            if(numOfFrame==quanOfFrames){
                numOfFrame=0;
            }
        }
    }

    protected int getRealWidth(){
        //return (int)(img.getWidth(null)*objectScale);
        return img.getWidth(null);
    }

    protected int getRealHeight(){
        //return (int)(img.getHeight(null)*objectScaleHeight);
        return img.getHeight(null);
    }
    
}
