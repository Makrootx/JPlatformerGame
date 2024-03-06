package Scripts.ObjectsScripts;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import Scripts.SpriteScripts.Background;
import Scripts.ToolsScripts.CameraOperator;
import Scripts.ToolsScripts.ImageScaler;

public class BackgroundInit {

    private BufferedImage backgroundImage;
    private ImageScaler scaler=new ImageScaler();
    private Background[] background=new Background[4];

    public BackgroundInit(CameraOperator cam){
        try{
            backgroundImage=ImageIO.read(getClass().getResourceAsStream("/All_Animation/Menu_elements/Background_new.png"));
            backgroundImage=scaler.scaleImage(backgroundImage, backgroundImage.getWidth()*2, backgroundImage.getHeight()*2, false);
        }catch (IOException e){
            e.printStackTrace();
        }
        background[0]=new Background(-500, -1200, backgroundImage.getWidth(), backgroundImage.getHeight(), backgroundImage, cam);
        for(int i=1; i<background.length; i++){
            background[i]=new Background(background[i-1].getCoorX() + background[i-1].getWidth(), background[0].getCoorY(), backgroundImage.getWidth(), backgroundImage.getHeight(), backgroundImage, cam);
        }
    }

    public void update(int screenWidth, int screenHeight){
        for(int i=0; i<background.length; i++){
            background[i].update(screenWidth, screenHeight);
        }
    }

    public void paint(Graphics2D g2){
        for(int i=0; i<background.length; i++){
            background[i].paint(g2);
        }
    }
    
}
