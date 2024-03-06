package Scripts.ToolsScripts;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class GetAnimationFrames {

    private String nameOfObject;

    public GetAnimationFrames(String nameOfObject){
        this.nameOfObject=nameOfObject;
        
    }

    public ArrayList<BufferedImage> getIdleRightFrames(int quanOfFrames){
        ArrayList<BufferedImage> frames=new ArrayList<BufferedImage>();
        for(int i=1; i<=quanOfFrames; i++){
            try{
                //String str="All_Animation\\"+nameOfObject+"\\"+nameOfObject+"_idle_right_"+String.valueOf(i)+".png";
                frames.add(ImageIO.read(getClass().getResourceAsStream("/All_Animation/"+nameOfObject+"/"+nameOfObject+"_idle_right_"+String.valueOf(i)+".png")));
            }catch(IOException e){

            }
        }
        return frames;
    }

    public ArrayList<BufferedImage> getIdleLeftFrames(int quanOfFrames){
        ArrayList<BufferedImage> frames=new ArrayList<BufferedImage>();
        for(int i=1; i<=quanOfFrames; i++){
            try{
                //String str="All_Animation\\"+nameOfObject+"\\"+nameOfObject+"_idle_"+String.valueOf(i)+".png";
                frames.add(ImageIO.read(getClass().getResourceAsStream("/All_Animation/"+nameOfObject+"/"+nameOfObject+"_idle_left_"+String.valueOf(i)+".png")));
            }catch(IOException e){

            }
        }
        return frames;
    }

    public ArrayList<BufferedImage> getRunRightFrames(int quanOfFrames){
        ArrayList<BufferedImage> frames=new ArrayList<BufferedImage>();
        for(int i=1; i<=quanOfFrames; i++){
            try{
                frames.add(ImageIO.read(getClass().getResourceAsStream("/All_Animation/"+nameOfObject+"/"+nameOfObject+"_run_right_"+String.valueOf(i)+".png")));
            }catch(IOException e){

            }
        }
        return frames;
    }

    public ArrayList<BufferedImage> getRunLeftFrames(int quanOfFrames){
        ArrayList<BufferedImage> frames=new ArrayList<BufferedImage>();
        for(int i=1; i<=quanOfFrames; i++){
            try{
                frames.add(ImageIO.read(getClass().getResourceAsStream("/All_Animation/"+nameOfObject+"/"+nameOfObject+"_run_left_"+String.valueOf(i)+".png")));
            }catch(IOException e){

            }
        }
        return frames;
    }

    public ArrayList<BufferedImage> getJumpFrames(int quanOfFrames){
        ArrayList<BufferedImage> frames=new ArrayList<BufferedImage>();
        for(int i=1; i<=quanOfFrames; i++){
            try{
                frames.add(ImageIO.read(getClass().getResourceAsStream("/All_Animation/"+nameOfObject+"/"+nameOfObject+"_jump_"+String.valueOf(i)+".png")));
            }catch(IOException e){

            }
        }
        return frames;
    }

    public ArrayList<BufferedImage> getDieLeftFrames(int quanOfFrames){
        ArrayList<BufferedImage> frames=new ArrayList<BufferedImage>();
        for(int i=1; i<=quanOfFrames; i++){
            try{
                frames.add(ImageIO.read(getClass().getResourceAsStream("/All_Animation/"+nameOfObject+"/"+nameOfObject+"_die_left_"+String.valueOf(i)+".png")));
            }catch(IOException e){

            }
        }
        return frames;
    }

    public ArrayList<BufferedImage> getDieRightFrames(int quanOfFrames){
        ArrayList<BufferedImage> frames=new ArrayList<BufferedImage>();
        for(int i=1; i<=quanOfFrames; i++){
            try{
                frames.add(ImageIO.read(getClass().getResourceAsStream("/All_Animation/"+nameOfObject+"/"+nameOfObject+"_die_right_"+String.valueOf(i)+".png")));
            }catch(IOException e){

            }
        }
        return frames;
    }
    
}
