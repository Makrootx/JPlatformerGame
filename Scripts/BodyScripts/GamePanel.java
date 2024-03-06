package Scripts.BodyScripts;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Scripts.ObjectsScripts.BackgroundInit;
import Scripts.ObjectsScripts.Coin;
import Scripts.ObjectsScripts.Enemy;
import Scripts.ObjectsScripts.Enemy2;
import Scripts.ObjectsScripts.FinishFlag;
import Scripts.ObjectsScripts.Platform;
import Scripts.ObjectsScripts.Player;
import Scripts.ObjectsScripts.SpikePlatform;
import Scripts.SpriteScripts.AnimatedSpriteWithCamera;
//import Scripts.SpriteScripts.Background;
import Scripts.SpriteScripts.Paintable;
import Scripts.ToolsScripts.CameraOperator;
import Scripts.ToolsScripts.FileOperator;
import Scripts.ToolsScripts.KeyHandler;
import Scripts.ToolsScripts.PlatformTileMap;
import Scripts.ToolsScripts.SoundOperator;
import Scripts.ToolsScripts.UI;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16;
    public final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    final int screenCol = 16;
    final int screenRow = 12;
    public int screenWidth = tileSize * screenCol;
    public int screenHeight = tileSize * screenRow;
    private int currentHeight = screenHeight;
    private int currentWidth = screenWidth;

    public int playerX = 300, playerY = 300;
    int FPS = 60;

    FileOperator fl = new FileOperator();

    Thread gameThread;
    KeyHandler keyHandler;
    private CameraOperator cam;
    Player pl;
    ArrayList<Platform> platforms = new ArrayList<Platform>();

    public JFrame jf;

    public enum gameStatus {
        Menu,
        Settings,
        Game,
        LevelSelect,
        Controls,
    }

    public gameStatus gameState = gameStatus.Menu;
    // private static final int NUM_FRAMES_TO_MEASURE = 60; // Number of frames to
    // measure for benchmarking
    // private static final long NANOSECONDS_IN_SECOND = 1_000_000_000L;

    public SoundOperator soundOp = new SoundOperator();
    public SoundOperator soundBackground = new SoundOperator();
    public PlatformTileMap tileMap = new PlatformTileMap();

    public ArrayList<Coin> coins = new ArrayList<Coin>();
    public ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    public ArrayList<SpikePlatform> spikes = new ArrayList<SpikePlatform>();
    public ArrayList<FinishFlag> finishFlags = new ArrayList<FinishFlag>();
    // public SpikePlatform spike;
    public ArrayList<Enemy2> enemies2 = new ArrayList<Enemy2>();
    private BackgroundInit background;
    // public Background b1, b2, b3, b4;

    public boolean isFullscreen = false;

    public int score;
    public boolean levelIsComplete = false;
    public int availableLevels = 1;
    public ArrayList<Paintable> sprites = new ArrayList<Paintable>();

    public ArrayList<AnimatedSpriteWithCamera> trashBin = new ArrayList<AnimatedSpriteWithCamera>();
    // private BufferedImage finishImage;
    // public FinishFlag flag;

    public int currentLevel = 0;
    public boolean notYet = false;

    UI ui;

    public int[] settingsData;

    public GamePanel(JFrame jf) {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLUE);
        this.jf = jf;
        // jf.setResizable(true);
        this.setDoubleBuffered(true);
        keyHandler = new KeyHandler(fl.GetFileDataControls("settings_data.txt", this));
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        settingsData = fl.GetFileDataSettings("settings_data.txt", this);
        availableLevels = fl.getAvailableLevels("game_data.txt");
        ui = new UI(this, keyHandler);
        updateAllUIScreens();
        startGameTread();
    }

    public void setLevel(int levelIndex) {
        flushAllObjects();
        levelIsComplete = false;
        int[] playerData = fl.GetFileDataPlayer("level_" + levelIndex + ".txt", this);
        cam = new CameraOperator(playerData[0], playerData[1]);
        // en2=new Enemy2(this, 500, 450, 600, 400);
        background = new BackgroundInit(cam);
        pl = new Player(this, keyHandler, playerData[0], playerData[1]);
        enemies.addAll(fl.GetFileDataEnemy("level_" + levelIndex + ".txt", this));
        enemies2.addAll(fl.GetFileDataEnemy2("level_" + levelIndex + ".txt", this));
        coins.addAll(fl.GetFileDataCoins("level_" + levelIndex + ".txt", this));
        platforms.addAll(fl.GetFileDataPlatforms("level_" + levelIndex + ".txt", this));
        spikes.addAll(fl.GetFileDataSpikes("level_" + levelIndex + ".txt", this));
        finishFlags.addAll(fl.GetFileDataFinishFlag("level_" + levelIndex + ".txt", this));
        int killBox = fl.GetFileDataKillBox("level_" + levelIndex + ".txt", this);
        if (killBox != 777) {
            pl.setKillBox(killBox);
        }
        // flag=new FinishFlag(this, 400, 450,cam );
        // spike=new SpikePlatform(this, 500, 450, 10);
        score = 0;
        playMusic(0, soundBackground);
        gameState = gameStatus.Game;
        // pl.deathAnimationIsEnded=false;
        currentLevel = levelIndex;
        if (currentLevel > availableLevels) {
            availableLevels = currentLevel;
            ui.setAvailebleLevels(availableLevels);
            fl.setFileAvailebleLevels("game_data.txt", availableLevels);
        }
    }

    public void flushAllObjects() {
        pl = null;
        stopMusic(soundBackground);
        stopMusic(soundOp);
        ui.menuSoundIsActive = false;
        enemies.clear();
        platforms.clear();
        coins.clear();
        spikes.clear();
        finishFlags.clear();
        enemies2.clear();
    }

    public void setFileSettings(int volume) {
        fl.setFile("settings_data.txt", isFullscreen, volume, keyHandler.getControlsInfo());
    }

    public void setFullscreen() {
        if (isFullscreen) {
            jf.setExtendedState(JFrame.MAXIMIZED_BOTH);
            // jf.setUndecorated(true);
        } else {
            jf.setSize(new Dimension(screenWidth, screenHeight));
            jf.setLocationRelativeTo(null);
            // jf.setUndecorated(false);
            jf.pack();
        }
    }

    public CameraOperator getCam() {
        return cam;
    }

    public void updateAllUIScreens() {
        ui.calculateCoordinates(jf.getWidth(), jf.getHeight());
        ui.updateControlsScreen();
        ui.updateLevelSelect();
        if (gameState != gameStatus.Game) {
            ui.updateMenu();
        }
        ui.updateSettings();
    }

    public void startGameTread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterwal = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        while (gameThread != null) {
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterwal;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {
        switch (gameState) {
            case Game:

                if (keyHandler.escapePressed) {
                    gameState = gameStatus.LevelSelect;
                    flushAllObjects();
                    ui.calculateCoordinates(jf.getWidth(), jf.getHeight());
                    ui.updateMenu();
                    break;
                }
                if (jf.getWidth() != pl.screenWidth || jf.getHeight() != pl.screenHeight) {
                    ui.calculateCoordinates(jf.getWidth(), jf.getHeight());
                    pl.updateCamera(jf.getWidth(), jf.getHeight());
                    background.update(jf.getWidth(), jf.getHeight());
                }
                if (levelIsComplete) {
                    ui.updateVictoryScreen();
                    // levelIsComplete=false;
                    if (ui.restart) {
                        System.out.println(2);
                        break;
                    }
                } else if (!pl.isDead) {
                    pl.update();
                    ui.updateScoreUI();
                } else if (pl.isDead) {
                    ui.updateDeathScreen();
                    if (ui.restart) {
                        break;
                    }
                    pl.playerDeathUpdate();
                }
                for (Enemy enemy : enemies) {
                    if (!enemy.isDead) {
                        enemy.update();
                        enemy.colisionWithPlayer(pl.playerLeft, pl.playerRight, pl.playerUp, pl.playerDown);
                    }
                    if (enemy.isDead) {
                        enemy.updateDeath(5);
                        if (enemy.deathFrames == 1) {

                            pl.setForceSpeed(10, 1.05f);
                            pl.setJumpSpeedToFallSpeed();
                        }

                        if (enemy.deathFrames == 5) {
                            playSE(2, soundOp);
                            score += 10;
                            trashBin.add(enemy);
                        }
                    }
                }
                for (Enemy2 enemy : enemies2) {
                    if (!enemy.isDead) {
                        enemy.update();
                        enemy.colisionWithPlayer(pl.playerLeft, pl.playerRight, pl.playerUp, pl.playerDown);
                    }
                    if (enemy.isDead) {
                        enemy.updateDeath(5);
                        if (enemy.deathFrames == 1) {

                            pl.setForceSpeed(10, 1.05f);
                            pl.setJumpSpeedToFallSpeed();
                        }

                        if (enemy.deathFrames == 5) {
                            playSE(2, soundOp);
                            score += 10;
                            trashBin.add(enemy);
                        }
                    }
                }
                for (FinishFlag flag : finishFlags) {
                    flag.update();
                    if (flag.colisionWithPlayer(pl.playerLeft, pl.playerRight, pl.playerUp, pl.playerDown)) {
                        ui.updateVictoryScreen();
                        levelIsComplete = true;
                    }
                }
                for (SpikePlatform spike : spikes) {
                    spike.update();
                    if (spike.collisionWithPlayer(pl.playerLeft, pl.playerRight, pl.playerUp, pl.playerDown)) {
                        setPlayerDeath();
                    }
                }
                // spike.update();

                for (Coin coin : coins) {
                    coin.update();
                    coin.colisionWithPlayer(pl.playerLeft, pl.playerRight, pl.playerUp, pl.playerDown);
                    if (!coin.isExisting) {
                        score += 5;
                        playSE(4, soundOp);
                        trashBin.add(coin);
                    }
                }
                for (AnimatedSpriteWithCamera object : trashBin) {
                    enemies.remove(object);
                    coins.remove(object);
                    enemies2.remove(object);
                }
                trashBin.clear();
                for (Platform platform : platforms) {
                    platform.update();

                    for (Enemy enemy : enemies) {
                        enemy.colisionDetection(platform.getStartPointX(), platform.getStartPointY(),
                                platform.getWidth(), platform.getHeight());
                    }
                    if (pl.colisionPlat(platform.getCoorX(), platform.getCoorY(), platform.getWidth(),
                            platform.getHeight()) && (keyHandler.jumpPressed || pl.forceIsAdd)) {
                        pl.changeCoorY(pl.fallSpeed);
                        pl.setJumpSpeedToFallSpeed();
                    }

                    pl.colisionDetection(platform.getCoorX(), platform.getCoorY(), platform.getWidth(),
                            platform.getHeight());
                }
                // System.out.println(levelIsComplete);
                // if(pl.isDead){

                // }
                break;

            case Menu:
                // ui.calculateCoordinates(jf.getWidth(), jf.getHeight());
                ui.updateMenu();

                break;

            case Settings:
                // ui.calculateCoordinates(jf.getWidth(), jf.getHeight());
                ui.updateSettings();
                break;

            case LevelSelect:
                // ui.calculateCoordinates(jf.getWidth(), jf.getHeight());
                ui.updateLevelSelect();
                break;
            case Controls:
                // ui.calculateCoordinates(jf.getWidth(), jf.getHeight());
                ui.updateControlsScreen();
                break;
        }
        if (jf.getWidth() != currentWidth || jf.getHeight() != currentHeight) {
            updateAllUIScreens();
            currentWidth = jf.getWidth();
            currentHeight = jf.getHeight();
        }

    }

    public void setPlayerDeath() {
        pl.isDead = true;
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);
        // if(!notYet){
        switch (gameState) {
            case Game:
                background.paint(g2);
                for (SpikePlatform spike : spikes) {
                    spike.paint(g2);
                }
                for (FinishFlag flag : finishFlags) {
                    flag.paint(g2);
                }
                for (Platform platform : platforms) {
                    platform.paintSprite(g2);
                }
                // flag.paint(g2);

                pl.paint(g2);

                for (Enemy enemy : enemies) {
                    enemy.paint(g2);
                }
                for (Enemy2 enemy : enemies2) {
                    enemy.paint(g2);
                }

                for (Coin coin : coins) {
                    coin.paint(g2);
                }
                if (pl.deathAnimationIsEnded) {
                    ui.paintDeathScreen(g2);
                } else if (levelIsComplete) {
                    ui.paintVictoryScreen(g2);
                } else
                    ui.paintScoreUI(g2);
                break;
            case Menu:
                ui.paintMenu(g2);
                break;
            case Settings:
                ui.paintSettings(g2);
                break;
            case LevelSelect:
                ui.paintLevelSelect(g2);
                break;
            case Controls:
                ui.paintControlsScreen(g2);
                break;
        }
        g2.dispose();// }
    }

    public void playMusic(int i, SoundOperator sound) {
        sound.setFile(i);
        sound.play();

        sound.loop();
    }

    public void setVolume(float newVolume) {
        soundBackground.setValue(newVolume);
        soundOp.setValue(newVolume);
    }

    public void stopMusic(SoundOperator sound) {
        sound.stop();
    }

    public void playSE(int i, SoundOperator sound) {
        sound.setFile(i);
        sound.play();
    }

}
