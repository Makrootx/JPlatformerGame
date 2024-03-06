package Scripts.SpriteScripts;

import java.awt.Image;

public abstract class Sprite implements Paintable{

    protected int coorX, coorY, width, height, spriteScale;
    protected Image img;

    public Sprite(int coorX, int coorY, int width, int height, Image img){
        this.coorX=coorX;
        this.coorY=coorY;
        this.width=width;
        this.height=height;
        this.img=img;
    }  

    public int getCoorX(){
        return coorX;
    }
    
    public int getCoorY(){
        return coorY;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public void addToCoorX(int addValue){
        coorX+=addValue;
    }
    
    public void addToCoorY(int addValue){
        coorY+=addValue;
    }

    public void setCoorX(int newCoorX){
        coorX=newCoorX;
    }

    
    public void setCoorY(int newCoorY){
        coorY=newCoorY;
    }
}
