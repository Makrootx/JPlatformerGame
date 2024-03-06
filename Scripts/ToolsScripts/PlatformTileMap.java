package Scripts.ToolsScripts;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class PlatformTileMap {
    private int sizeOfTileMap=24;
    private ArrayList<BufferedImage> tileMap=new ArrayList<BufferedImage>();

    public PlatformTileMap(){
        getTileMap();
    }

    public BufferedImage getTile(int numberOfTile){
        return tileMap.get(numberOfTile);
    }

    private void getTileMap() {
        for (int i = 1; i <= sizeOfTileMap; i++) {
            try {

                //String
                //str=System.getProperty("user.dir")+"\\All_Animation\\Tiles\\" + "Tile_" + String.valueOf(i) + ".png";
                tileMap.add(ImageIO.read(getClass()
                        .getResourceAsStream("/All_Animation/Tiles/" + "Tile_" + String.valueOf(i) + ".png")));
            } catch (IOException e) {

            }
        }
    }
}
