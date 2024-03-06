package Scripts.ObjectsScripts.UIelements;

import java.awt.Color;
import java.awt.Graphics2D;

public class StaticRect {

    private int coorX, coorY, width, height;
    private int offset;
    private Color innerRectColor=Color.GRAY, outerRectColor=Color.BLACK;
    private int elementMargins=60, elementUpperMargins=20;

    public StaticRect(int coorX, int coorY, int width, int height, int offset){
        this.coorX=coorX;
        this.coorY=coorY;
        this.width=width;
        this.height=height;
        this.offset=offset;
    }

    public StaticRect(int width, int height){
        this.offset=10;
        this.width=width;
        this.height=height;
    }

    public void setCoorX(int coorX){
        this.coorX=coorX;
    }

    public void addCoorX(int addValue){
        this.coorX+=addValue;
    }    

    public void setCoorY(int coorY){
        this.coorY=coorY;
    }

    public void addCoorY(int addValue){
        this.coorY+=addValue;
    }

    public void setWidth(int width){
        this.width=width;
    }

    public void setHeight(int height){
        this.height=height;
    }

    public void setOffset(int offset){
        this.offset=offset;
    }

    public void setObjectMargins(int margins){
        this.elementMargins=margins;
    }

    public void setObjectUpperMargins(int margins){
        this.elementUpperMargins=margins;
    }

    public int getCoorX(){
        return coorX;
    }

    public int getCoorY(){
        return coorY;
    }

    public int getInnerCoorX(){
        return coorX+offset/2;
    }

    public int getInnerCoorY(){
        return coorY+offset/2;
    }

    public int getLeftBorderForObject(){
        return getInnerCoorX()+elementMargins;
    }
    public int getRightBorderForObject(){
        return getInnerCoorX()+width-offset/2-elementMargins;
    }

    public void setOuterColor(Color outerColor){
        this.outerRectColor=outerColor;
    }

    public void setInnerColor(Color innerColor){
        this.innerRectColor=innerColor;
    }

    public void update(int coorX, int coorY, int width, int height){
        this.coorX=coorX;
        this.coorY=coorY;
        this.width=width;
        this.height=height;
    }

    public void updateForCenter(int screenWidth, int screenHeight){
        this.coorX=screenWidth/2-width/2;
        this.coorY=screenHeight/2-height/2;
    }

    public void paint(Graphics2D g2){
        g2.setColor(outerRectColor);
        g2.fillRect(coorX, coorY, width, height);
        g2.setColor(innerRectColor);
        g2.fillRect(coorX+offset/2, coorY+offset/2, width-offset, height-offset);
    }

    public int getTextCoorY(int quantityOfAllTextes, int numberOfText, int heightOfText){
        int heightWithMargins=height-offset-elementUpperMargins*2-heightOfText/2;
        double spaceBetween=(heightWithMargins-heightOfText*quantityOfAllTextes)/(quantityOfAllTextes-1);
        double objCoorY=(coorY+offset/2)+heightOfText*(numberOfText-1)+spaceBetween*(numberOfText-1)+elementUpperMargins; 
        return (int)objCoorY;
    }

    public int getObjectCoorX(int quantityOfAllObjects, int numberOfObjects, int widthOfObject){
        int widthWithMargins=width-offset-elementMargins*2;
        double spaceBetween=(widthWithMargins-widthOfObject*quantityOfAllObjects)/(quantityOfAllObjects-1);
        double objCoorX=(coorX+offset/2)+widthOfObject*(numberOfObjects-1)+spaceBetween*(numberOfObjects-1)+elementMargins;
        return (int)objCoorX;
    }
    
}
