package Scripts.ObjectsScripts;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
//import java.io.IOException;
import java.util.ArrayList;

//import javax.imageio.ImageIO;

import Scripts.BodyScripts.GamePanel;
import Scripts.SpriteScripts.SpriteWithCamera;
import Scripts.ToolsScripts.CameraOperator;

public class Platform {
    GamePanel gp;
    int coorX, coorY, width, height;

    ArrayList<BufferedImage> tileMap = new ArrayList<BufferedImage>();
    ArrayList<SpriteWithCamera> tiles = new ArrayList<SpriteWithCamera>();
    public int deltaX = 0, deltaY = 0;

   // private int sizeOfTileMap = 24;

    protected int startPointX, startPointY;

    protected CameraOperator cam;

    public Platform(GamePanel gp, int coorX, int coorY, int width, int height) {
        this.gp = gp;
        cam=gp.getCam();
        this.coorX = coorX;
        startPointX = coorX;
        this.coorY = coorY;
        startPointY = coorY;
        this.width = width;
        this.height = height;
        //getTileMap();
        setTiles();
    }

    private void setTiles() {
        if (width >= 2 && height >= 2) {
            for (int j = 0; j < width; j++) {
                for (int k = 0; k < height; k++) {
                    int tileY, tileX;
                    if (j == 0)
                        tileX = 1;
                    else if (j > 0 && j < width - 1)
                        tileX = 2;
                    else
                        tileX = 3;
                    if (k == 0)
                        tileY = 0;
                    else if (k == 1)
                        tileY = 1;
                    else if (k > 1 && k < height - 1)
                        tileY = 2;
                    else
                        tileY = 3;
                    if (height == 2 && k == 1)
                        tileY = 4;
                    if (width == 2 && j == 1)
                        tileX = 3;
                    tiles.add(new SpriteWithCamera(coorX + gp.tileSize * j, coorY + gp.tileSize * k, gp.tileSize, gp.tileSize,
                            gp.tileMap.getTile(tileY * 3 + tileX - 1), cam));
                }
            }
        } else if (width == 1 && height >= 2) {
            int tileNum = 18;
            for (int j = 0; j < height; j++) {
                if (j < 3) {
                    tileNum++;
                }
                if (j == height - 1) {
                    tileNum++;
                }
                if (height == 2 && j == 1)
                    tileNum = 24;
                tiles.add(
                        new SpriteWithCamera(coorX, coorY + gp.tileSize * j, gp.tileSize, gp.tileSize, gp.tileMap.getTile(tileNum - 1), cam));
            }
        } else if (height == 1 && width >= 2) {
            int tileNum = 15;
            for (int j = 0; j < width; j++) {
                if (j < 2) {
                    tileNum++;
                }
                if (j == width - 1) {
                    tileNum++;
                }
                if (width == 2 && j == 1)
                    tileNum = 18;
                tiles.add(
                        new SpriteWithCamera(coorX + gp.tileSize * j, coorY, gp.tileSize, gp.tileSize, gp.tileMap.getTile(tileNum - 1), cam));
            }

        } else
            tiles.add(new SpriteWithCamera(coorX, coorY, gp.tileSize, gp.tileSize, gp.tileMap.getTile(22), cam));
    }

    public void update(){
        coorX=cam.getObjectCoorX(startPointX);
        coorY=cam.getObjectCoorY(startPointY);
    }

    public void paintSprite(Graphics2D g2) {
        //int Y = 0, X = 0;
        coorX = cam.getObjectCoorX(startPointX);
        coorY = cam.getObjectCoorY(startPointY);
        for (SpriteWithCamera tile : tiles) {
            /*tile.coorX = cam.getObjectCoorX(startPointX) + gp.tileSize * X;
            tile.coorY = cam.getObjectCoorY(startPointY) + gp.tileSize * Y;
            Y++;
            if (Y >= height) {
                X++;
                Y = 0;
            }*/
            tile.paint(g2);
        }

    }


    /*private void getTileMap() {
        for (int i = 1; i <= sizeOfTileMap; i++) {
            try {

                //String
                //str=System.getProperty("user.dir")+"\\All_Animation\\Tiles\\" + "Tile_" + String.valueOf(i) + ".png";
                tileMap.add(ImageIO.read(getClass()
                        .getResourceAsStream("/All_Animation/Tiles/" + "Tile_" + String.valueOf(i) + ".png")));
            } catch (IOException e) {

            }
        }
    }*/

    public int getCoorX(){
        return coorX;
    }

    public void setCoorX(int coorX){
        this.coorX=coorX;
    }

    public int getCoorY(){
        return coorY;
    }

    public void setCoorY(int coorY){
        this.coorY=coorY;
    }

    public int getWidth(){
        return width*gp.tileSize;
    }

    public int getHeight(){
        return height*gp.tileSize;
    }

    public int getStartPointX(){
        return startPointX;
    }

    public int getStartPointY(){
        return startPointY;
    }
}
