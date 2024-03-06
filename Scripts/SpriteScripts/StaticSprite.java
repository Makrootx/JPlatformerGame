package Scripts.SpriteScripts;

import java.awt.Graphics2D;
import java.awt.Image;

public class StaticSprite extends Sprite{

    public StaticSprite(int coorX, int coorY, int width, int height, Image img) {
        super(coorX, coorY, width, height, img);
    }

    public void paint(Graphics2D g2) {
        g2.drawImage(img, coorX, coorY, width, height, null);
    }
    
}
