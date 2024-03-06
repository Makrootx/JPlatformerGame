package Scripts.SpriteScripts;

import java.awt.Graphics2D;


public class AnimatedSpriteStatic extends AnimatedSprite{

    public AnimatedSpriteStatic(int coorX, int coorY, int width, int height, String nameOfObject, int quanOfFrames, double objectScale) {
        super(coorX, coorY, width, height, nameOfObject, quanOfFrames, objectScale);
    }

    public void getFrames() {
        if(hasLeftAnimation){
            runAnimation.addAll(getFrames.getRunRightFrames(quanOfFrames));
            runAnimation.addAll(getFrames.getRunLeftFrames(quanOfFrames));
        }
        else{
            runAnimation.addAll(getFrames.getRunRightFrames(quanOfFrames));
        }
    }

    public void paint(Graphics2D g2) {
        getObjectFrame();
        g2.drawImage(img, coorX+(width-(int)(img.getHeight(null)*objectScale)), coorY+(width-(int)(img.getHeight(null)*objectScale)), (int)(img.getWidth(null)*objectScale), (int)(img.getHeight(null)*objectScale), null);
    }
    
    
}
