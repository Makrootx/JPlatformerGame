package Scripts.ToolsScripts;

public class CameraOperator {
    int startPointX, startPointY, deltaX, deltaY;

    public CameraOperator(int startPointX, int startPointY){
        this.startPointX=startPointX;
        this.startPointY=startPointY;
        deltaX=0;
        deltaY=0;
    }

    public int getObjectCoorX(int objectStartPointX){
        return objectStartPointX+deltaX;
    }

    public int getObjectCoorY(int objectStartPointY){
        return objectStartPointY+deltaY;
    }

    public void setDeltaX(int deltaX){
        this.deltaX=deltaX;
        //this.deltaX=0;
    }

    public void setDeltaY(int deltaY){
        this.deltaY=deltaY;
        //this.deltaY=0;
    }

    public int getStartPointX(){
        return startPointX;
    }

    public int getStartPointY(){
        return startPointY;
    }

    public void debugCamera(){
        System.out.println(deltaX);
        System.out.println(deltaY);
    }

    public int getBackgroundCoorX(int objectStartPointX){
        return objectStartPointX+deltaX/10;
    }

    public int getBackgroundCoorY(int objectStartPointY){
        return objectStartPointY+deltaY/40;
    }
}
