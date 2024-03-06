package Scripts.ToolsScripts;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ImageScaler {
    public BufferedImage scaleImage(BufferedImage originalImage, int width, int height, boolean isAlfa){
        BufferedImage scaledImage;
        if(isAlfa){
            scaledImage=new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR_PRE);
        }else{
        scaledImage=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);}
        //System.out.println(originalImage.getType());
        Graphics2D g2=scaledImage.createGraphics();
        g2.drawImage(originalImage, 0, 0, width, height, null);
        g2.dispose();
        return scaledImage;
    }
}
