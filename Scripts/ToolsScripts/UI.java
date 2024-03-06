package Scripts.ToolsScripts;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Scripts.BodyScripts.GamePanel;
import Scripts.BodyScripts.GamePanel.gameStatus;
import Scripts.ObjectsScripts.UIelements.ActiveRect;
import Scripts.ObjectsScripts.UIelements.StaticRect;
import Scripts.ObjectsScripts.UIelements.Text;
import Scripts.ObjectsScripts.UIelements.TextRect;
import Scripts.SpriteScripts.StaticSprite;

public class UI {

    protected int currentHeight, currentWidth;

    private GamePanel gp;

    private KeyHandler keyH;

    private BufferedImage backgroundImage, arrowImage, arrowHorizontalImage;

    private boolean downIsReleased, upIsReleased, rightIsReleased, leftIsReleased, enterIsReleased;

    private int settingsWidth, settingsHeight;

    private StaticRect settingsRect, levelSelectRect, deathScreenRect, victoryScreenRect;
    private int maxLevelsRectsInColumn;

    private ArrayList<StaticSprite> backgroundElements = new ArrayList<StaticSprite>();
    private StaticSprite arrow, arrowHorizontal;
    public boolean menuSoundIsActive=false;

    private enum menuOptions {
        Play,
        Settings,
        Exit,
    }

    private enum controlsOption{
        Left,
        Right,
        Jump,
    }

    private enum settingsOptions {
        FullScreen,
        Volume,
        Controls,
    }

    private enum deathScreenOptions {
        Restart,
        Exit,
    }

    private menuOptions selectedMenuOption = menuOptions.Play;

    private settingsOptions selectedSettingsOption = settingsOptions.FullScreen;

    private deathScreenOptions selectedDeathScreenOption = deathScreenOptions.Restart;

    private controlsOption selectedControlsOption=controlsOption.Left;

    private Text[] settingsText = new Text[3];
    private ActiveRect[] myActiveRects=new ActiveRect[6];
    private Text deathScreenScore;
    private Text scoreText;
    private Text[] menuText = new Text[3];
    private Text[] controlsText = new Text[3];

    private Text[] headers = new Text[6];
    private TextRect[] levelsRects = new TextRect[5];
    private TextRect[] controlsRects =new TextRect[3];
    private TextRect[] deathRects = new TextRect[2];
    private TextRect[] victoryRects = new TextRect[2];
    private int levelIndex = 1;
    private int availableLevels;
    public boolean restart=false;
    public int volumeOfSound=0;
    public boolean keyListenning=false;

    Font myFont;

    public UI(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        currentWidth = gp.screenWidth;
        currentHeight = gp.screenHeight;
        try {
            backgroundImage = ImageIO
                    .read(getClass().getResourceAsStream("/All_Animation/Menu_elements/Background_new.png"));
            arrowImage = ImageIO.read(getClass().getResource("/All_Animation/Menu_elements/Arrow.png"));
            arrowHorizontalImage = ImageIO
                    .read(getClass().getResource("/All_Animation/Menu_elements/Arrow_horizontal.png"));
        } catch (IOException e) {

        }
        backgroundElements.add(new StaticSprite(-backgroundImage.getWidth() - 5, -backgroundImage.getHeight() + 150,
                backgroundImage.getWidth() * 2, backgroundImage.getHeight() * 2, backgroundImage));
        backgroundElements.add(new StaticSprite(backgroundElements.get(0).getCoorX() + backgroundImage.getWidth() * 2,
                backgroundElements.get(0).getCoorY(), backgroundImage.getWidth() * 2, backgroundImage.getHeight() * 2,
                backgroundImage));
        arrow = new StaticSprite(0, 0, arrowImage.getWidth() * 2, arrowImage.getHeight() * 2, arrowImage);
        arrowHorizontal = new StaticSprite(0, 0, arrowHorizontalImage.getWidth() * 2,
                arrowHorizontalImage.getHeight() * 2, arrowHorizontalImage);
        getFont();
        setDeathScreenElements();
        setMenuElements();
        setSettingsElements();
        setLevelSelectElements();
        setScoreUI();
        setVictoryScreenElements();
        setControlsElements();
        availableLevels = gp.availableLevels;
        if(gp.settingsData[0]==1){
            myActiveRects[0].setActive();
            gp.isFullscreen=true;
            gp.setFullscreen();
        }else{
            gp.isFullscreen=false;
            gp.setFullscreen();
        }
        volumeOfSound=gp.settingsData[1];
        if(volumeOfSound<=3){
            for(int i=0; i<volumeOfSound; i++){
                myActiveRects[5-i].setActive();
            }
        }else{
            for(int i=0; i<volumeOfSound-1; i++){
                myActiveRects[5-i].setActive();
            }
            myActiveRects[1].setActive();
        }
    }

    public void getFont(){
        try{
            InputStream is=getClass().getResourceAsStream("/GameData/Quinquefive-ALoRM.ttf");
            myFont=Font.createFont(Font.TRUETYPE_FONT, is);
        }catch(FontFormatException e){

        }catch(IOException e){

        }
    }

    public void setAvailebleLevels(int newValue){
        availableLevels=newValue;
    }

    public void setMenuElements() {
        headers[0] = new Text(40, "Platformer 2D");
        menuText[0] = new Text(23, "Play");
        menuText[1] = new Text(23, "Settings");
        menuText[2] = new Text(23, "Exit");
    }

    public void setSettingsElements() {
        headers[1] = new Text(40, "Settings");
        settingsText[0] = new Text(30, "Fullscreen");
        settingsText[1] = new Text(30, "Volume");
        settingsText[2] = new Text(30, "Controls");
        settingsRect = new StaticRect(settingsWidth, settingsHeight);
        settingsRect.setOffset(20);
        for(int i=0; i<myActiveRects.length; i++){
            myActiveRects[i]=new ActiveRect(70, 70, false);
        }
        
    }

    public void setControlsElements(){
        headers[5]= new Text(40, "Controls");
        controlsText[0] = new Text(30, "Left");
        controlsText[1] = new Text(30, "Right");
        controlsText[2] = new Text(30, "Jump");
        controlsRects[0] = new TextRect(100, 70, 25, keyH.controls[0].getName());
        controlsRects[1] = new TextRect(100, 70, 25, keyH.controls[1].getName());
        controlsRects[2] = new TextRect(100, 70, 25, keyH.controls[2].getName());
    }

    public void setLevelSelectElements() {
        levelSelectRect = new StaticRect(settingsWidth, settingsHeight);
        headers[2] = new Text(40, "Levels");
        for (int i = 0; i < levelsRects.length; i++) {
            levelsRects[i] = new TextRect(70, 70, 30, String.valueOf(i + 1));
        }
        maxLevelsRectsInColumn = 4;
    }

    public void setDeathScreenElements() {
        deathScreenRect = new StaticRect(settingsWidth - 200, settingsHeight - 100);
        headers[3] = new Text(60, "Game Over");
        deathScreenScore = new Text(20, "Score is : ");
        deathRects[0] = new TextRect(200, 50, 20, "Restart");
        deathRects[1] = new TextRect(200, 50, 20, "Exit");
    }

    public void setScoreUI() {
        scoreText = new Text(20, "Score : ");
    }

    public void setVictoryScreenElements(){
        victoryScreenRect=new StaticRect(settingsWidth - 200, settingsHeight - 100);
        headers[4]=new Text(40, "Level Complete");
        victoryRects[0]=new TextRect(230, 50, 20, "Continue");
        victoryRects[1]=new TextRect(200, 50, 20, "Exit");
    }

    public void updateMenu() {
        gp.notYet=false;
        if(!menuSoundIsActive){
            gp.stopMusic(gp.soundBackground);
            gp.playMusic(5, gp.soundBackground);
        }
        menuSoundIsActive=true;
        headers[0].updateForCenterX(0, 60, currentWidth, currentHeight);
        int menuTextSpace = 270;
        for (int i = 0; i < menuText.length; i++) {
            menuText[i].updateForCenterX(0, currentHeight - menuTextSpace, currentWidth, currentHeight);
            menuTextSpace -= 60;
        }
        if (!keyH.downPressed)
            downIsReleased = true;
        if (!keyH.upPressed)
            upIsReleased = true;
        if (!keyH.enterPressed)
            enterIsReleased = true;
        if (keyH.downPressed && downIsReleased) {
            switch (selectedMenuOption) {
                case Play:
                    selectedMenuOption = menuOptions.Settings;
                    break;
                case Settings:
                    selectedMenuOption = menuOptions.Exit;
                    break;
                case Exit:
                    selectedMenuOption = menuOptions.Play;
                    break;
            }
            downIsReleased = false;
        }
        if (keyH.upPressed && upIsReleased) {
            switch (selectedMenuOption) {
                case Play:
                    selectedMenuOption = menuOptions.Exit;
                    break;
                case Settings:
                    selectedMenuOption = menuOptions.Play;
                    break;
                case Exit:
                    selectedMenuOption = menuOptions.Settings;
                    break;
            }
            upIsReleased = false;
        }
        if (keyH.enterPressed && enterIsReleased) {
            switch (selectedMenuOption) {
                case Play:
                    gp.notYet=true;
                    gp.gameState = gameStatus.LevelSelect;
                    enterIsReleased = false;
                    break;
                case Settings:
                    gp.notYet=true;
                    gp.gameState = gameStatus.Settings;
                    enterIsReleased = false;
                    break;
                case Exit:
                    System.exit(3);
                    break;
            }
        }
    }

    public void paintMenu(Graphics2D g2) {
        g2.setFont(myFont);
        for (StaticSprite element : backgroundElements) {
            element.paint(g2);
        }
        headers[0].paint(g2);

        switch (selectedMenuOption) {
            case Play:
                arrow.setCoorX(menuText[0].getCoorX() - arrow.getWidth() - 10);
                arrow.setCoorY(menuText[0].getCenterYForObject(arrow.getHeight()));
                break;
            case Settings:
                arrow.setCoorX(menuText[1].getCoorX() - arrow.getWidth() - 10);
                arrow.setCoorY(menuText[1].getCenterYForObject(arrow.getHeight()));
                break;
            case Exit:
                arrow.setCoorX(menuText[2].getCoorX() - arrow.getWidth() - 10);
                arrow.setCoorY(menuText[2].getCenterYForObject(arrow.getHeight()));
                break;
        }
        arrow.paint(g2);

        for (int i = 0; i < menuText.length; i++) {
            menuText[i].paint(g2);
        }

    }

    public void updateSettings() {
        gp.notYet=false;
        headers[1].updateForCenterX(0, 30, currentWidth, currentHeight);
        settingsRect.setWidth(settingsWidth);
        settingsRect.setHeight(settingsHeight);
        settingsRect.updateForCenter(currentWidth, currentHeight);
        settingsRect.addCoorY(30);
        settingsRect.addCoorX(-7);
        settingsRect.setObjectMargins(50);
        for (int i = 0; i < settingsText.length; i++) {
            settingsText[i].update(settingsRect.getLeftBorderForObject(),
                    settingsRect.getTextCoorY(3, i + 1, settingsText[i].getHeight()));
            myActiveRects[i].updateCoors(settingsRect.getRightBorderForObject()-myActiveRects[i].getWidth(), settingsText[i].getCenterYForObject(myActiveRects[i].getHeight()));
        }
        for(int i=settingsText.length; i<myActiveRects.length; i++){
            myActiveRects[i].updateCoors(myActiveRects[settingsText.length-2].getCoorX()-(myActiveRects[i].getWidth()+10)*(i-settingsText.length+1), myActiveRects[settingsText.length-2].getCoorY());
        }
        if (!keyH.downPressed)
            downIsReleased = true;
        if (!keyH.upPressed)
            upIsReleased = true;
        if (!keyH.enterPressed)
            enterIsReleased = true;
        if (!keyH.rightPressed)
            rightIsReleased = true;
        if (!keyH.leftPressed)
            leftIsReleased = true;
        if(keyH.rightPressed && rightIsReleased){
            if(volumeOfSound<3){
            myActiveRects[5-volumeOfSound].setActive();
            volumeOfSound++;
            System.out.println(volumeOfSound);
        }else if(volumeOfSound==3){
                myActiveRects[1].setActive();
                volumeOfSound++;
            }
            rightIsReleased=false;
        }
        if(keyH.leftPressed && leftIsReleased){
            if(volumeOfSound>0 && volumeOfSound!=4){
            myActiveRects[5-volumeOfSound+1].setActive();
            volumeOfSound--;
            System.out.println(volumeOfSound);
        }else if(volumeOfSound==4){
                myActiveRects[1].setActive();
                volumeOfSound--;
            }
            leftIsReleased=false;
        }
        if (keyH.downPressed && downIsReleased) {
            switch (selectedSettingsOption) {
                case FullScreen:
                    selectedSettingsOption = settingsOptions.Volume;
                    break;
                case Volume:
                    selectedSettingsOption = settingsOptions.Controls;
                    break;
                case Controls:
                    selectedSettingsOption = settingsOptions.FullScreen;
                    break;
            }
            downIsReleased = false;
        }
        if (keyH.upPressed && upIsReleased) {
            switch (selectedSettingsOption) {
                case FullScreen:
                    selectedSettingsOption = settingsOptions.Controls;
                    break;
                case Volume:
                    selectedSettingsOption = settingsOptions.FullScreen;
                    break;
                case Controls:
                    selectedSettingsOption = settingsOptions.Volume;
                    break;
            }
            upIsReleased = false;
        }
        if (keyH.enterPressed && enterIsReleased) {
            switch (selectedSettingsOption) {
                case FullScreen:
                    myActiveRects[0].setActive();
                    if(myActiveRects[0].getIfActive()){
                        gp.isFullscreen=true;
                        gp.setFullscreen();
                    }else{
                        gp.isFullscreen=false;
                        gp.setFullscreen();
                    }
                    break;
                /*case Volume:
                    myActiveRects[1].setActive();
                    if(myActiveRects[1].getIfActive()){
                        gp.setVolume(-20f);
                        gp.setVolume(-20f);
                    }else{
                        gp.setVolume(1f);
                        gp.setVolume(1f);
                    }
                    break;*/
                case Controls:
                    gp.gameState=gameStatus.Controls;
                    break;
                case Volume:
                    break;

            }
            enterIsReleased=false;
        }
        if (keyH.escapePressed) {
            //System.out.println("1");
            gp.setFileSettings(volumeOfSound);
            gp.gameState = gameStatus.Menu;
           
        }
        switch (volumeOfSound){
            case 1:
            gp.setVolume(-40f);
            break;
            case 2:
            gp.setVolume(-20f);
            break;
            case 3:
            gp.setVolume(-10f);
            break;
            case 4:
            gp.setVolume(0f);
            break;
            case 0:
            gp.setVolume(-80f);
            break;
        }

    }

    public void paintSettings(Graphics2D g2) {
        g2.setFont(myFont);
        for (StaticSprite element : backgroundElements) {
            element.paint(g2);
        }
        headers[1].paint(g2);
        settingsRect.paint(g2);
        switch (selectedSettingsOption) {
            case FullScreen:
                arrow.setCoorX(settingsText[0].getCoorX() - arrow.getWidth() - 10);
                arrow.setCoorY(settingsText[0].getCenterYForObject(arrow.getHeight()));
                break;
            case Controls:
                arrow.setCoorX(settingsText[2].getCoorX() - arrow.getWidth() - 10);
                arrow.setCoorY(settingsText[2].getCenterYForObject(arrow.getHeight()));
                break;
            case Volume:
                arrow.setCoorX(settingsText[1].getCoorX() - arrow.getWidth() - 10);
                arrow.setCoorY(settingsText[1].getCenterYForObject(arrow.getHeight()));
                break;
        }
        arrow.paint(g2);
        for (int i = 0; i < settingsText.length; i++) {
            settingsText[i].paint(g2);
            myActiveRects[i].paint(g2);
        }
        for (int i=settingsText.length; i<myActiveRects.length; i++){
            myActiveRects[i].paint(g2);
        }
        if (keyH.escapePressed) {
            gp.gameState = gameStatus.Menu;
        }
    }

    public void updateControlsScreen(){
        headers[5].updateForCenterX(0, 30, currentWidth, currentHeight);
        settingsRect.setWidth(settingsWidth);
        settingsRect.setHeight(settingsHeight);
        settingsRect.updateForCenter(currentWidth, currentHeight);
        settingsRect.addCoorY(30);
        settingsRect.addCoorX(-7);
        settingsRect.setObjectMargins(50);
        for (int i = 0; i < controlsText.length; i++) {
            controlsText[i].update(settingsRect.getLeftBorderForObject(),
                    settingsRect.getTextCoorY(3, i + 1, controlsText[i].getHeight()));
            controlsRects[i].updateCoors(settingsRect.getRightBorderForObject()-controlsRects[i].getWidth(), controlsText[i].getCenterYForObject(controlsRects[i].getHeight()));
        }
        if (!keyH.downPressed)
            downIsReleased = true;
        if (!keyH.upPressed)
            upIsReleased = true;
        if (!keyH.enterPressed)
            enterIsReleased = true;
        if (keyH.downPressed && downIsReleased) {
            switch (selectedControlsOption) {
                case Left:
                    selectedControlsOption=controlsOption.Right;
                    break;
                case Right:
                    selectedControlsOption=controlsOption.Jump;
                    break;
                case Jump:
                    selectedControlsOption=controlsOption.Left;
                    break;
            }
            downIsReleased = false;
        }
        if (keyH.upPressed && upIsReleased) {
            switch (selectedControlsOption) {
                case Left:
                    selectedControlsOption=controlsOption.Jump;
                    break;
                case Right:
                    selectedControlsOption=controlsOption.Left;
                    break;
                case Jump:
                    selectedControlsOption=controlsOption.Right;
                    break;
            }
            upIsReleased = false;
        }
        
        if (keyH.enterPressed && enterIsReleased) {
            keyListenning=true;
            enterIsReleased=false;
        }
        if(keyListenning && enterIsReleased){
            switch (selectedControlsOption) {
                case Left:
                    keyH.setKeyListenning(0);
                    keyListenning=false;
                    //controlsRects[0].setText(keyH.getCharOfControl(1));
                    break;
                case Right:
                    keyH.setKeyListenning(1);
                    //controlsRects[1].setText(keyH.getCharOfControl(2));
                    keyListenning=false;
                    break;
                case Jump:
                    keyH.setKeyListenning(2);
                    //controlsRects[2].setText(keyH.getCharOfControl(0));
                    keyListenning=false;
                    break;
            }
        }
        if(keyH.getKeyListenning()==false){
            controlsRects[keyH.getIndexOfControl()].setText(keyH.getCharOfControl(keyH.getIndexOfControl()));
            //controlsRects[1].setText(keyH.getCharOfControl(1));
            //controlsRects[2].setText(keyH.getCharOfControl(2));
        }
        /*if(!keyH.getKeyListenning() && enterIsReleased){
            keyListenning=false;
        }*/
        if (keyH.escapePressed) {
            gp.gameState = gameStatus.Settings;
            gp.setFileSettings(volumeOfSound);
        }



    }

    public void paintControlsScreen(Graphics2D g2){
        g2.setFont(myFont);
        for (StaticSprite element : backgroundElements) {
            element.paint(g2);
        }
        headers[5].paint(g2);
        settingsRect.paint(g2);
        switch (selectedControlsOption) {
            case Left:
                arrow.setCoorX(controlsText[0].getCoorX() - arrow.getWidth() - 10);
                arrow.setCoorY(controlsText[0].getCenterYForObject(arrow.getHeight()));
                break;
            case Right:
                arrow.setCoorX(controlsText[1].getCoorX() - arrow.getWidth() - 10);
                arrow.setCoorY(controlsText[1].getCenterYForObject(arrow.getHeight()));
                break;
            case Jump:
                arrow.setCoorX(controlsText[2].getCoorX() - arrow.getWidth() - 10);
                arrow.setCoorY(controlsText[2].getCenterYForObject(arrow.getHeight()));
                break;
        }
        arrow.paint(g2);
        for (int i = 0; i < controlsText.length; i++) {
            controlsText[i].paint(g2);
            controlsRects[i].paint(g2);
        }
    }

    public void updateLevelSelect() {
        gp.notYet=false;
        levelSelectRect.setWidth(settingsWidth);
        levelSelectRect.setHeight(settingsHeight);
        levelSelectRect.updateForCenter(currentWidth, currentHeight);
        levelSelectRect.addCoorY(30);
        levelSelectRect.addCoorX(-7);
        headers[2].updateForCenterX(0, 30, currentWidth, currentHeight);
        levelSelectRect.setObjectMargins(50);
        levelSelectRect.setObjectUpperMargins(40);
        for (int i = 0; i < maxLevelsRectsInColumn; i++) {
            levelsRects[i].updateCoors(
                    levelSelectRect.getObjectCoorX(maxLevelsRectsInColumn, i + 1, levelsRects[i].getWidth()),
                    levelSelectRect.getTextCoorY(5, 1, levelsRects[i].getHeight()));
        }
        for (int i = maxLevelsRectsInColumn; i < levelsRects.length; i++) {
            levelsRects[i]
                    .updateCoors(
                            levelSelectRect.getObjectCoorX(maxLevelsRectsInColumn, i - maxLevelsRectsInColumn + 1,
                                    levelsRects[i].getWidth()),
                            levelSelectRect.getTextCoorY(5, 3, levelsRects[i].getHeight()));
        }
        if (!keyH.enterPressed)
            enterIsReleased = true;
        //System.out.println(levelIndex);
        if (keyH.enterPressed && enterIsReleased) {
            if (levelIndex <= availableLevels) {
                gp.setLevel(levelIndex);
                enterIsReleased = false;
            }
        }
        if (!keyH.rightPressed)
            rightIsReleased = true;
        if (!keyH.leftPressed)
            leftIsReleased = true;
        if (keyH.rightPressed && rightIsReleased) {
            switch (levelIndex) {
                case 1:
                    levelIndex += 1;
                    break;
                case 2:
                    levelIndex += 1;
                    break;
                case 3:
                    levelIndex += 1;
                    break;
                case 4:
                    levelIndex += 1;
                    break;
                case 5:
                    levelIndex = 1;
                    break;
            }
            rightIsReleased = false;
        }
        if (keyH.leftPressed && leftIsReleased) {
            switch (levelIndex) {
                case 1:
                    levelIndex = 5;
                    break;
                case 2:
                    levelIndex -= 1;
                    break;
                case 3:
                    levelIndex -= 1;
                    break;
                case 4:
                    levelIndex -= 1;
                    break;
                case 5:
                    levelIndex -= 1;
                    break;
            }
            leftIsReleased = false;
        }
        if (keyH.escapePressed) {
            gp.gameState = gameStatus.Menu;
        }

    }

    public void paintLevelSelect(Graphics2D g2) {
        g2.setFont(myFont);
        backgroundElements.get(0).paint(g2);
        backgroundElements.get(1).paint(g2);
        levelSelectRect.paint(g2);
        headers[2].paint(g2);
        for (int i = 0; i < levelsRects.length; i++) {
            if (i + 1 <= availableLevels) {
                levelsRects[i].paint(g2);
            } else
                levelsRects[i].paintWithoutText(g2);
        }
        arrowHorizontal.setCoorX(levelsRects[levelIndex - 1].getObjCenterX(arrowHorizontal.getWidth()));
        arrowHorizontal.setCoorY(levelsRects[levelIndex - 1].getCoorY() - arrowHorizontal.getHeight() - 10);
        arrowHorizontal.paint(g2);

    }

    public void updateDeathScreen() {
        restart=false;
        deathScreenRect.setWidth(settingsWidth - 200);
        deathScreenRect.setHeight(settingsHeight - 100);
        deathScreenRect.updateForCenter(currentWidth, currentHeight);
        deathScreenRect.addCoorY(30);
        deathScreenRect.addCoorX(-7);
        headers[3].updateForCenterX(0, 30, currentWidth, currentHeight);
        deathScreenScore.setText("Score is : " + String.valueOf(gp.score));
        deathScreenRect.setObjectMargins(20);
        deathScreenRect.setObjectUpperMargins(40);
        deathScreenScore.updateForCenterX(0, deathScreenRect.getTextCoorY(3, 1, deathScreenScore.getHeight()),
                currentWidth, currentHeight);
        deathRects[0].updateCoors(deathScreenRect.getObjectCoorX(2, 1, deathRects[0].getWidth()),
                deathScreenRect.getTextCoorY(3, 3, deathRects[0].getHeight()));
        deathRects[1].updateCoors(deathScreenRect.getObjectCoorX(2, 2, deathRects[1].getWidth()),
                deathScreenRect.getTextCoorY(3, 3, deathRects[1].getHeight()));
        if (!keyH.rightPressed)
            rightIsReleased = true;
        if (!keyH.leftPressed)
            leftIsReleased = true;
        if (!keyH.enterPressed)
            enterIsReleased = true;
        if (keyH.rightPressed && rightIsReleased) {
            switch (selectedDeathScreenOption) {
                case Exit:
                    selectedDeathScreenOption = deathScreenOptions.Restart;
                    //System.out.println("aha");
                    break;
                case Restart:
                    selectedDeathScreenOption = deathScreenOptions.Exit;
                    break;

            }
            rightIsReleased = false;
        }
        if (keyH.leftPressed && leftIsReleased) {
            switch (selectedDeathScreenOption) {
                case Exit:
                    selectedDeathScreenOption = deathScreenOptions.Restart;
                    break;
                case Restart:
                    selectedDeathScreenOption = deathScreenOptions.Exit;
                    break;
            }
            leftIsReleased = false;
        }
        if (keyH.enterPressed && enterIsReleased) {
            switch (selectedDeathScreenOption) {
                case Exit:
                    gp.notYet=true;
                    gp.gameState = gameStatus.Menu;
                    break;
                case Restart:
                    gp.setLevel(gp.currentLevel);
                    restart=true;
                    break;

            }
            enterIsReleased=false;
        }
        //System.out.println(rightIsReleased);
    }

    public void paintDeathScreen(Graphics2D g2) {
        g2.setFont(myFont);
        deathScreenRect.paint(g2);
        headers[3].paint(g2);
        deathScreenScore.paint(g2);
        deathRects[0].paint(g2);
        deathRects[1].paint(g2);
        switch (selectedDeathScreenOption) {
            case Exit:
                arrowHorizontal.setCoorX(deathRects[1].getObjCenterX(arrowHorizontal.getWidth()));
                arrowHorizontal.setCoorY(deathRects[1].getCoorY() - arrowHorizontal.getHeight() - 10);
                break;
            case Restart:
                arrowHorizontal.setCoorX(deathRects[0].getObjCenterX(arrowHorizontal.getWidth()));
                arrowHorizontal.setCoorY(deathRects[0].getCoorY() - arrowHorizontal.getHeight() - 10);
                break;
        }
        arrowHorizontal.paint(g2);
        // arrowHorizontal.setCoorX(levelsRects[levelIndex-1].getObjCenterX(arrowHorizontal.getWidth()));
        // arrowHorizontal.setCoorY(levelsRects[levelIndex-1].getCoorY()-arrowHorizontal.getHeight()-10);

    }

    public void updateScoreUI() {
        scoreText.setText("Score : " + String.valueOf(gp.score));
        scoreText.updateForCenterX(0, 40, currentWidth, currentHeight);
    }

    public void paintScoreUI(Graphics2D g2) {
        g2.setFont(myFont);
        scoreText.paint(g2);
    }

    public void updateVictoryScreen(){
        restart=false;
        victoryScreenRect.setWidth(settingsWidth - 200);
        victoryScreenRect.setHeight(settingsHeight - 100);
        victoryScreenRect.updateForCenter(currentWidth, currentHeight);
        victoryScreenRect.addCoorY(30);
        victoryScreenRect.addCoorX(-7);
        headers[4].updateForCenterX(0, 30, currentWidth, currentHeight);
        deathScreenScore.setText("Score is : " + String.valueOf(gp.score));
        victoryScreenRect.setObjectMargins(20);
        victoryScreenRect.setObjectUpperMargins(40);
        deathScreenScore.updateForCenterX(0, victoryScreenRect.getTextCoorY(3, 1, deathScreenScore.getHeight()),
                currentWidth, currentHeight);
        victoryRects[0].updateCoors(victoryScreenRect.getObjectCoorX(2, 1, victoryRects[0].getWidth()),
                victoryScreenRect.getTextCoorY(3, 3, victoryRects[0].getHeight()));
        victoryRects[1].updateCoors(victoryScreenRect.getObjectCoorX(2, 2, victoryRects[1].getWidth()),
                victoryScreenRect.getTextCoorY(3, 3, victoryRects[1].getHeight()));
            if (!keyH.rightPressed)
                rightIsReleased = true;
            if (!keyH.leftPressed)
                leftIsReleased = true;
            if (!keyH.enterPressed)
                enterIsReleased = true;
            if (keyH.rightPressed && rightIsReleased) {
                switch (selectedDeathScreenOption) {
                    case Exit:
                        selectedDeathScreenOption = deathScreenOptions.Restart;
                        //System.out.println("aha");
                        break;
                    case Restart:
                        selectedDeathScreenOption = deathScreenOptions.Exit;
                        break;
    
                }
                rightIsReleased = false;
            }
            if (keyH.leftPressed && leftIsReleased) {
                switch (selectedDeathScreenOption) {
                    case Exit:
                        selectedDeathScreenOption = deathScreenOptions.Restart;
                        break;
                    case Restart:
                        selectedDeathScreenOption = deathScreenOptions.Exit;
                        break;
                }
                leftIsReleased = false;
            }
            if (keyH.enterPressed && enterIsReleased) {
                switch (selectedDeathScreenOption) {
                    case Exit:
                    gp.notYet=true;
                        gp.gameState = gameStatus.Menu;
                        break;
                    case Restart:
                        if(gp.currentLevel==5){
                            gp.gameState=gameStatus.Menu;
                        }else{
                        gp.setLevel(gp.currentLevel+1);
                        restart=true;}
                        //restart=true;
                        break;
    
                }
                enterIsReleased=false;
            }
    }

    public void paintVictoryScreen(Graphics2D g2) {
        g2.setFont(myFont);
        victoryScreenRect.paint(g2);
        headers[4].paint(g2);
        deathScreenScore.paint(g2);
        victoryRects[0].paint(g2);
        victoryRects[1].paint(g2);
        switch (selectedDeathScreenOption) {
            case Exit:
                arrowHorizontal.setCoorX(victoryRects[1].getObjCenterX(arrowHorizontal.getWidth()));
                arrowHorizontal.setCoorY(victoryRects[1].getCoorY() - arrowHorizontal.getHeight() - 10);
                break;
            case Restart:
                arrowHorizontal.setCoorX(victoryRects[0].getObjCenterX(arrowHorizontal.getWidth()));
                arrowHorizontal.setCoorY(victoryRects[0].getCoorY() - arrowHorizontal.getHeight() - 10);
                break;
        }
        arrowHorizontal.paint(g2);
        // arrowHorizontal.setCoorX(levelsRects[levelIndex-1].getObjCenterX(arrowHorizontal.getWidth()));
        // arrowHorizontal.setCoorY(levelsRects[levelIndex-1].getCoorY()-arrowHorizontal.getHeight()-10);

    }

    public void calculateCoordinates(int newWidth, int newHeight) {
        backgroundElements.get(0).addToCoorX((newWidth - currentWidth) / 2);
        backgroundElements.get(0).addToCoorY((newHeight - currentHeight) / 2);
        backgroundElements.get(1).addToCoorX((newWidth - currentWidth) / 2);
        backgroundElements.get(1).addToCoorY((newHeight - currentHeight) / 2);
        currentWidth = newWidth;
        currentHeight = newHeight;
        if (currentWidth >= 1000) {
            settingsWidth = currentWidth - 800;
            settingsHeight = currentHeight - 300;
        } else {
            settingsWidth = currentWidth - 80;
            settingsHeight = currentHeight - 180;
        }
    }

    
}
