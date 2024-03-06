public class CameraOperator {

    int deltaX, deltaY;
    int currentPositionX, currentPositionY;
    int deltaPositionX, deltaPositionY;

    public CameraOperator(){

    }

    public void calculatePosition(int updatePositionX, int updatePositionY){
        deltaPositionX=(updatePositionX-currentPositionX);
        deltaPositionY=updatePositionY-currentPositionY;
    }

    public void setPosition(int updatePositionX, int updatePositionY){
        deltaX+=deltaPositionX;
        deltaY+=deltaPositionY;
        deltaPositionX=0;
        deltaPositionY=0;
        //currentPositionX=updatePositionX;
        //currentPositionY=updatePositionY;
    }

    public void reset(){
        deltaX=0;
        deltaY=0;
        deltaPositionX=0;
        deltaPositionY=0;
        currentPositionX=0;
        currentPositionY=0;
    }

    public int getDeltaX(int objectPositionX){
        return objectPositionX+deltaX+deltaPositionX;
    }

    public int getDeltaY(int objectPositionY){
        return objectPositionY+deltaY+deltaPositionY;
    }
}
