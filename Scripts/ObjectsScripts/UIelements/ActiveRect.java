package Scripts.ObjectsScripts.UIelements;

import java.awt.Color;
import java.awt.Graphics2D;

public class ActiveRect {
    private int coorX, coorY, width, height;
    private boolean isActive;
    private int sizeOfMargins=20;
    

    public ActiveRect(int coorX, int coorY, int width, int height, boolean isActive){
        this.coorX=coorX;
        this.coorY=coorY;
        this.width=width;
        this.height=height;
        this.isActive=isActive;
    }

    public ActiveRect(int width, int height, boolean isActive){
        this.coorX=0;
        this.coorY=0;
        this.width=width;
        this.height=height;
        this.isActive=isActive;
    }

    public int getCoorY(){
        return coorY;
    }

    public int getCoorX(){
        return coorX;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public int getObjCenterX(int objWidth){
        return coorX+width/2-objWidth/2;
    }

    public void updateCoors(int coorX, int coorY){
        this.coorX=coorX;
        this.coorY=coorY;
    }

    public boolean getIfActive(){
        return isActive;
    }

    public void setActive(){
        if(isActive){
            isActive=false;
        }else isActive=true;
    }

    public void paint(Graphics2D g2){
        g2.setColor(Color.BLACK);
        g2.fillRect(coorX, coorY, width, height);
        if(isActive){
            g2.setColor(Color.WHITE);
        g2.fillRect(coorX+sizeOfMargins/2, coorY+sizeOfMargins/2, width-sizeOfMargins, height-sizeOfMargins);}
    }
}
