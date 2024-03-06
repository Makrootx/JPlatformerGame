package Scripts.ObjectsScripts;

import java.awt.Graphics2D;
import java.util.ArrayList;

import Scripts.BodyScripts.GamePanel;

public class SpikePlatform {
    private ArrayList<Spike> spikesInPlatform =new ArrayList<Spike>();

    private int width, height, sizeOfSpike;
    //private CameraOperator cam;

    public SpikePlatform(GamePanel gp, int coorX, int coorY, int width){
        spikesInPlatform.add(new Spike(gp, coorX, coorY, gp.getCam()));
        int addCoorX=spikesInPlatform.get(0).getSizeOfSpike();
        this.sizeOfSpike=addCoorX;
        this.height=sizeOfSpike;
        this.width=width;
        for(int i=1; i<width; i++){
            spikesInPlatform.add(new Spike(gp, coorX+addCoorX, coorY, gp.getCam()));
            addCoorX+=sizeOfSpike;
        }        
    }

    public void update(){
        for(Spike spike:spikesInPlatform){
            spike.update();
        }
    }

    public void paint(Graphics2D g2){
        for(Spike spike:spikesInPlatform){
            spike.paint(g2);
        }
    }

    public boolean collisionWithPlayer(int objXL, int objXR, int objYU, int objYD){
        int coorX=spikesInPlatform.get(0).getCoorX();
        int coorY=spikesInPlatform.get(0).getCoorY();
        if(objYD>=coorY && (objXR>=coorX && objXL<=coorX+width*sizeOfSpike) && objYU<=coorY+height){
            //System.out.println(1);
            return true;
        }else return false;
   }
}
