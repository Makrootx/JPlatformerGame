package Scripts.ObjectsScripts.UIelements;
import java.awt.Color;
import java.awt.Graphics2D;

public class TextRect {

    private int coorX, coorY, width, height, fontSize;
    //private String txt;
    private Text myText;
    

    public TextRect( int coorX, int coorY, int width, int height, int fontSize, String txt){
        this.coorX=coorX;
        this.coorY=coorY;
        this.width=width;
        this.height=height;
        this.fontSize=fontSize;
        //this.txt=txt;
        myText=new Text(fontSize, txt);
    }

    public TextRect(int width, int height, int fontSize, String txt){
        this.coorX=0;
        this.coorY=0;
        this.width=width;
        this.height=height;
        this.fontSize=fontSize;
        //this.txt=txt;
        myText=new Text(fontSize, txt);
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

    public int getObjCenterX(int objWidth){
        return coorX+width/2-objWidth/2;
    }

    public void updateCoors(int coorX, int coorY){
        this.coorX=coorX;
        this.coorY=coorY;
    }

    public void paint(Graphics2D g2){
        int textWidth, textHeight;
        g2.setColor(Color.BLACK);
        g2.fillRect(coorX, coorY, width, height);
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(coorX+5, coorY+5, width-10, height-10);
        g2.setColor(Color.BLACK);
        textWidth=myText.getWidth(g2);
        textHeight=myText.getHeight(g2);
        myText.update(coorX+width/2-textWidth/2+(int)(fontSize/13), coorY+height/2-textHeight/2-(int)(fontSize/3.5));
        //g2.setColor(Color.WHITE);
        //g2.fillRect(coorX+width/2-textWidth/2, coorY+height/2-textHeight/2, 2, 2);
        myText.paint(g2);

    }

    public void setText(String txt){
        myText.setText(txt);
    }

    public void paintWithoutText(Graphics2D g2){
        g2.setColor(Color.BLACK);
        g2.fillRect(coorX, coorY, width, height);
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(coorX+5, coorY+5, width-10, height-10);
    }

  /*   public int getTextWidth(String txt, Graphics2D g2){
        int length=(int)g2.getFontMetrics().getStringBounds(txt, g2).getWidth();

        return(length);
    }

    public int getTextHeight(String txt, Graphics2D g2){

        int height=(int)g2.getFontMetrics().getStringBounds(txt, g2).getHeight();
        

        return(height);
    }*/
}
