package Scripts.ObjectsScripts.UIelements;

import java.awt.Color;
import java.awt.Graphics2D;

public class Text {

    protected int coorX, coorY, coorYD, width=1, height=1, fontSize;
    protected int currentHeight, currentWidth;
    protected String txt;


    public Text(int coorX, int coorY, int fontSize, String txt){
        this.coorX=coorX;
        this.coorY=coorY;
        this.fontSize=fontSize;
        this.txt=txt;
    }

    public Text(int fontSize, String txt){
        this.fontSize=fontSize;
        this.txt=txt;
    }

    public void updateForCenter(int addCoorX, int addCoorY, int currentWidth, int currentHeight){
        this.currentWidth=currentWidth;
        this.currentHeight=currentHeight;
        this.coorY=currentHeight/2-height/2+addCoorY;
        this.coorYD=coorY+height;
        this.coorX=currentWidth/2-width/2+addCoorX;
    }

    public void updateForCenterX(int addCoorX, int coorY, int currentWidth, int currentHeight){
        this.currentWidth=currentWidth;
        this.currentHeight=currentHeight;
        this.coorY=coorY;
        this.coorYD=coorY+height;
        this.coorX=currentWidth/2-width/2+addCoorX;
    }

    public void update(int newCoorX, int newCoorY){
        coorX=newCoorX;
        coorY=newCoorY;
        coorYD=coorY+height;
    }

    public int getWidth(Graphics2D g2){
        g2.setFont(g2.getFont().deriveFont((float)fontSize));
        //g2.setFont(new Font("QuinqueFive", Font.BOLD, fontSize));
        int length=(int)g2.getFontMetrics().getStringBounds(txt, g2).getWidth();

        return(length);
    }

    public int getHeight(Graphics2D g2){
        g2.setFont(g2.getFont().deriveFont((float)fontSize));
        //g2.setFont(new Font("QuinqueFive", Font.BOLD, fontSize));
        int height=(int)g2.getFontMetrics().getStringBounds(txt, g2).getHeight();
        

        return(height);
    }

    public void paint(Graphics2D g2){
        g2.setFont(g2.getFont().deriveFont((float)fontSize));
        //g2.setFont(new Font("QuinqueFive", Font.BOLD, fontSize));
        this.width=getWidth(g2);
        this.height=getHeight(g2);
        g2.setColor(Color.GRAY);
        g2.drawString(txt, coorX+4, coorYD+4);
        g2.setColor(Color.BLACK);
        g2.drawString(txt, coorX, coorYD);
    }

    public int getCoorX(){

        return coorX;
    }

    public int getCoorY(){

        return coorY;
    }

    public int getCoorYDown(){
        return coorYD;
    }

    public int getHeight(){
        return height;
    }

    public int getXForText(Graphics2D g2){
        int x;

        int length=(int)g2.getFontMetrics().getStringBounds(txt, g2).getWidth();
        x=currentWidth/2-length/2;

        return(x);
    }

    public int getYForText(String txt, Graphics2D g2){
        int y;

        int height=(int)g2.getFontMetrics().getStringBounds(txt, g2).getHeight();
        y=currentHeight/2-height/2;

        return(y);
    }

    public int getCenterYForObject(int objHeight){
        int objCoorY=0;
        objCoorY=coorYD-height/2-objHeight/2+(int)(fontSize/3.5);
        return objCoorY;
    }

    public void setText(String txt){
        this.txt=txt;
    }

}
