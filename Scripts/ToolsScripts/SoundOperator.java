package Scripts.ToolsScripts;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class SoundOperator {
    Clip clip;
    URL soundURL[]=new URL[30];
    FloatControl gainControl;
    float volume=1;

    public SoundOperator(){
        soundURL[0]=getClass().getResource("/Sound/Song_1.wav");
        soundURL[1]=getClass().getResource("/Sound/blipSelect.wav");
        soundURL[2]=getClass().getResource("/Sound/hitHurt.wav");
        soundURL[3]=getClass().getResource("/Sound/jump.wav");
        soundURL[4]=getClass().getResource("/Sound/pickupCoin.wav");
        soundURL[5]=getClass().getResource("/Sound/Menu_Song.wav");
    }

    public void setFile(int i){
        try{
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip=AudioSystem.getClip();
            clip.open(ais);
            gainControl= (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        }catch(Exception e){

        }

    }

    public void setValue(float newValue){
        if(gainControl!=null){
        gainControl.setValue(newValue);
        }
        volume=newValue;
    }

    public void play(){
        gainControl.setValue(volume);
        /*clip.addLineListener(event -> {
            if (event.getType() == LineEvent.Type.STOP) {
              clip.close();
            }
          });*/
        clip.start();
    }

    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(){
        if(clip!=null){
        clip.stop();}
    }
    
}
