package Scripts.ToolsScripts;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



public class KeyHandler implements KeyListener{

    public boolean upPressed, downPressed, rightPressed, leftPressed, jumpPressed, escapePressed=false, escapeReleased=false;
    public boolean enterPressed=false;
    public char lastReleasedButton;
    public int codeOfButton;
    public ControlInfo[] controls=new ControlInfo[3];
    private boolean keyListening=false;
    private int indexOfControl=0;
    //public int[] controllsCodes=new int[3];


    public KeyHandler(ControlInfo[] controls){
        this.controls=controls;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code =e.getKeyCode();
        if(code==KeyEvent.VK_W || code==KeyEvent.VK_UP){
            upPressed=true;
        }
        if(code==controls[0].getCode() || code==KeyEvent.VK_LEFT){
            leftPressed=true;
        }
        if(code==controls[1].getCode() || code==KeyEvent.VK_RIGHT){
            rightPressed=true;
        }
        if(code==KeyEvent.VK_S || code==KeyEvent.VK_DOWN){
            downPressed=true;
        }
        if(code==controls[2].getCode()){
            jumpPressed=true;
        }
        if(code==KeyEvent.VK_ESCAPE){
            escapePressed=true;
        }
        if(code==KeyEvent.VK_ENTER){
            enterPressed=true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code =e.getKeyCode();
        if(code==KeyEvent.VK_W || code==KeyEvent.VK_UP){
            upPressed=false;
        }
        if(code==controls[0].getCode() || code==KeyEvent.VK_LEFT){
            leftPressed=false;
        }
        if(code==controls[1].getCode() || code==KeyEvent.VK_RIGHT){
            rightPressed=false;
        }
        if(code==KeyEvent.VK_S || code==KeyEvent.VK_DOWN){
            downPressed=false;
        }
        if(code==controls[2].getCode()){
           jumpPressed=false;
        }
        if(code==KeyEvent.VK_ESCAPE){
            escapePressed=false;
            escapeReleased=true;
        }
        if(code==KeyEvent.VK_ENTER){
            enterPressed=false;
        }
        //System.out.println(keyListening);
        if(keyListening==true && e.getKeyChar()!=' '){
            controls[indexOfControl].setNewInfo(code, String.valueOf(e.getKeyChar()));
            keyListening=false;
        }else if (keyListening){
            controls[indexOfControl].setNewInfo(code, "SP");
            keyListening=false;
        }
        //System.out.println(keyListening);
        
    }

    public void setKeyListenning(int indexOfControl){
        keyListening=true;
        this.indexOfControl=indexOfControl;
    }

    public ControlInfo[] getControlsInfo(){
        return controls;
    }

    public String getCharOfControl(int index){
        return controls[index].getName();
    }

    public boolean getKeyListenning(){
        return keyListening;
    }
    public int getIndexOfControl(){
        return indexOfControl;
    }

    
}
