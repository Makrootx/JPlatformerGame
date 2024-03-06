import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyPanel extends JPanel implements Runnable {

    final int originalTileSize = 16;
    public final int scale = 3;
    public final int tileSize = originalTileSize * scale;
    public int drawZoneX = 1354;

    int width = 300, height = 100;

    PointerInfo a;
    Point b;
    int x;
    int y;
    boolean objectIsActive = true;

    boolean isUpdatedCamera = false;

    boolean createNewObject = false;
    boolean moveObject = false;
    boolean moveCamera = false;
    boolean isCreated = false;
    boolean delete=false;

    Thread gameThread;
    int FPS = 60;

    JButton myBut1 = new JButton();
    JButton myBut2 = new JButton();
    JButton myBut3 =new JButton();
    JButton myBut4 =new JButton("Edit");
    String[] objectList = { "Platform", "Enemy", "Coin", "Player", "FinishPoint", "Spikes", "Enemy2", "Killbox"};
    JComboBox<String> myComb = new JComboBox<String>(objectList);
    JTextField myField1 = new JTextField();
    JTextField myField2 = new JTextField();
    JTextField pathField = new JTextField();
    JLabel textParametr1=new JLabel();
    JLabel textParametr2=new JLabel();
    JLabel textDeleteActive=new JLabel("Editing");

    ArrayList<Platform> platforms = new ArrayList<Platform>();
    ArrayList<Enemy> enemies =new ArrayList<Enemy>();
    ArrayList<Coin> coins =new ArrayList<Coin>();
    ArrayList<SpikePlatform> spikes=new ArrayList<SpikePlatform>();
    ArrayList<Player> players =new ArrayList<Player>();
    ArrayList<FinishPoint> finishPoints= new ArrayList<FinishPoint>();
    ArrayList<Enemy2> enemies2=new ArrayList<Enemy2>();
    KillBox killBox=null;
    ArrayList<Sprite> trashBin = new ArrayList<Sprite>();
    CameraOperator cam = new CameraOperator();
    ArrayList<Sprite> activeObject = new ArrayList<Sprite>();

    MouseAdapter myMouse = new MouseAdapter();
    FileOperator file =new FileOperator();

    public MyPanel() {
        /*
         * this.setLayout(new BorderLayout());
         * JPanel centerPanel = new JPanel();
         * centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
         * JPanel southPanel = new JPanel(new CardLayout());
         * 
         * this.add(centerPanel, BorderLayout.CENTER);
         * this.add(southPanel, BorderLayout.SOUTH);
         * 
         * myBut1.setText("loh");
         * myBut1.setSize(1000, 100);
         * // myBut1.setAlignmentY(1000);
         * myBut1.setHorizontalAlignment(JButton.CENTER);
         * myBut1.setVerticalAlignment(JButton.CENTER);
         * myBut1.addActionListener(new ActionListener() {
         * 
         * @Override
         * public void actionPerformed(ActionEvent e) {
         * System.exit(3);
         * }
         * 
         * });
         * centerPanel.add(myBut1);
         */
        // myComb.addItem(new Object());
        JPanel panel2 = new JPanel(new GridBagLayout());
        // JPanel panel1 =new JPanel();
        this.setLayout(new BorderLayout());
        this.add(panel2, BorderLayout.LINE_END);
        panel2.setBackground(Color.green);
        // this.add(panel1, BorderLayout.LINE_START);
        GridBagConstraints c = new GridBagConstraints();

        this.addMouseListener(myMouse);

        c.gridx = 1;
        c.weightx = 10;
        c.weighty = 2;
        c.gridy = 3;
        c.gridwidth = 2;
        c.ipady = 10;
        c.ipadx = 100;
        myBut1.setText("Place");
        myBut1.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                createNewObject = true;
            }

        });
        panel2.add(myBut1, c);
        c.gridx = 1;
        c.gridy = 4;
        myBut2.setText("Delete");
        myBut2.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(!delete){
                delete=true;
                textDeleteActive.setText("Deleting");
            
            }
                else{ delete=false;
                textDeleteActive.setText("Editing");}
                // objectIsActive=true;
            }

        });
        panel2.add(myBut2, c);
        c.gridx=1;
        c.gridy = 6;
        panel2.add(myComb, c);
        c.gridwidth=1;
        c.gridy = 2;
        
        panel2.add(myField1, c);
        c.gridx = 2;
        panel2.add(myField2, c);
        //c.ipadx=100;
        //c.ipady=10;
        c.gridy=5;
        c.gridx=1;
        c.gridwidth=2;
        panel2.add(textDeleteActive, c);
        c.gridwidth=1;
        c.gridx=1;
        c.gridy=1;
        c.ipady=1;
        c.ipadx=1;
        panel2.add(textParametr1, c);
        c.gridx=2;
        c.gridy=1;
        panel2.add(textParametr2, c);
        //c.ipadx=
        c.gridy=8;
        c.gridwidth=1;
        c.gridx=1;
        myBut3.setText("Save");
        myBut3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                file.setFile(pathField.getText(),platforms, enemies, coins, players, spikes, finishPoints, enemies2, killBox);
            }
            
        });
        panel2.add(myBut3, c);
        myBut4.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                resetData();
                cam.reset();
                enemies.addAll(file.GetFileDataEnemy(pathField.getText(), cam, tileSize));
                enemies2.addAll(file.GetFileDataEnemy2(pathField.getText(), cam, tileSize));
                platforms.addAll(file.GetFileDataPlatforms(pathField.getText(), cam, tileSize));
                coins.addAll(file.GetFileDataCoins(pathField.getText(), cam));
                players.addAll(file.GetFileDataPlayer(pathField.getText(), cam));
                spikes.addAll(file.GetFileDataSpikes(pathField.getText(), cam, tileSize));
                finishPoints.addAll(file.GetFileDataFinishPoint(pathField.getText(), cam));
                killBox=file.GetFileDataKillBox(pathField.getText(), cam);
            }
            
        });
        c.gridy=8;
        c.gridx=2;
        panel2.add(myBut4, c);
        c.gridy=7;
        c.gridx=1;
        c.ipadx=100;
        c.ipady=10;
        c.gridwidth=2;
        panel2.add(pathField, c);

        this.setDoubleBuffered(true);
        // this.setLayout(new MigLayout());
        this.setPreferredSize(new Dimension(500, 300));
        startGameTread();
    }

    public void startGameTread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        double drawInterwal = 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawInterwal;

        while (gameThread != null) {

            update();

            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterwal;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void update() {
        a = MouseInfo.getPointerInfo();
        b = a.getLocation();
        x = (int) b.getX();
        y = (int) b.getY() - 25;
        String selectedObject=myComb.getSelectedItem().toString();
        int parametr1=1, parametr2=1;
        try{
         parametr1=Integer.parseInt(myField1.getText());
        
         parametr2=Integer.parseInt(myField2.getText());

        //System.out.println(myComb.getSelectedItem());
        }catch(NumberFormatException e){

        }

        if(selectedObject=="Platform"){
            textParametr1.setText("Width");
            textParametr2.setText("Height");
        }else if(selectedObject=="Enemy" || selectedObject=="Enemy2"){ 
            textParametr1.setText("Left Side");
            textParametr2.setText("Right Side");
        }else if(selectedObject=="Spikes"){
            textParametr1.setText("Width");
            textParametr2.setText("");
        }else if(selectedObject=="Killbox"){
            textParametr1.setText("CoorY");
            textParametr2.setText("");
        }
        else {
            textParametr1.setText("");
            textParametr2.setText("");
        }

        if (createNewObject && !isCreated) {
            if(selectedObject=="Platform"){
            activeObject.add(new Platform(x, y, Integer.parseInt(myField1.getText()) * tileSize,
                    Integer.parseInt(myField2.getText()) * tileSize, cam));
            }
            else if(selectedObject=="Enemy"){
                activeObject.add(new Enemy(x, y, parametr1, parametr2, cam));
            }
            else if(selectedObject=="Enemy2"){
                activeObject.add(new Enemy2(x, y, parametr1, parametr2, cam));
            }
            else if(selectedObject=="Coin"){
                activeObject.add(new Coin(x, y, cam));
            }
            else if(selectedObject=="Spikes"){
                activeObject.add(new SpikePlatform(x, y, parametr1*tileSize, tileSize, cam));
            }
            else if(selectedObject=="Player"){
                activeObject.add(new Player(x, y, cam));
            }
            else if(selectedObject=="FinishPoint"){
                activeObject.add(new FinishPoint(x, y, cam));
            }
            else if(selectedObject=="Killbox"){
                killBox=new KillBox(parametr1, cam);
                createNewObject = false;
                moveObject = false;
                myMouse.mouseIsClicked = false;
                moveCamera = false;
                isCreated = false;
                activeObject.clear();
            }
            if(selectedObject!="Killbox"){
            isCreated = true;}
            // myMouse.mouseIsClicked=false;
        }

        if (x <= drawZoneX) {
            for (Platform platform : platforms) {

                if (myMouse.mouseIsClicked && !createNewObject && !moveObject && x >= cam.getDeltaX(platform.coorX)
                        && x <= cam.getDeltaX(platform.coorX) + platform.width && y >= cam.getDeltaY(platform.coorY)
                        && y <= cam.getDeltaY(platform.coorY) + platform.height) {
                    moveObject = true;
                    myComb.setSelectedItem("Platform");
                    trashBin.add(platform);
                    activeObject.add(new Platform(x, y, platform.width, platform.height, cam));
                    myMouse.mouseIsClicked = false;
                    break;

                }
            }
            for(Enemy enemy:enemies){
                //String[] data=enemy.getParametrs();
                //System.out.println(data[0]+"  "+data[1]+"  "+data[2]+"  "+data[3]);
                if (myMouse.mouseIsClicked && !createNewObject && !moveObject && x >= cam.getDeltaX(enemy.coorX)
                        && x <= cam.getDeltaX(enemy.coorX) + enemy.width && y >= cam.getDeltaY(enemy.coorY)
                        && y <= cam.getDeltaY(enemy.coorY) + enemy.height) {
                    moveObject = true;
                    myComb.setSelectedItem("Enemy");
                    trashBin.add(enemy);
                    activeObject.add(new Enemy(x, y, enemy.leftSide, enemy.rightSide, cam));
                    myMouse.mouseIsClicked = false;
                    break;
                        }
            }
            for(Enemy2 enemy2:enemies2){
                //String[] data=enemy.getParametrs();
                //System.out.println(data[0]+"  "+data[1]+"  "+data[2]+"  "+data[3]);
                if (myMouse.mouseIsClicked && !createNewObject && !moveObject && x >= cam.getDeltaX(enemy2.coorX)
                        && x <= cam.getDeltaX(enemy2.coorX) + enemy2.width && y >= cam.getDeltaY(enemy2.coorY)
                        && y <= cam.getDeltaY(enemy2.coorY) + enemy2.height) {
                    moveObject = true;
                    myComb.setSelectedItem("Enemy2");
                    trashBin.add(enemy2);
                    activeObject.add(new Enemy2(x, y, enemy2.leftSide, enemy2.rightSide, cam));
                    myMouse.mouseIsClicked = false;
                    break;
                        }
            }
            for(Coin coin: coins){
                if (myMouse.mouseIsClicked && !createNewObject && !moveObject && x >= cam.getDeltaX(coin.coorX)
                        && x <= cam.getDeltaX(coin.coorX) + coin.width && y >= cam.getDeltaY(coin.coorY)
                        && y <= cam.getDeltaY(coin.coorY) + coin.height) {
                    moveObject = true;
                    myComb.setSelectedItem("Coin");
                    trashBin.add(coin);
                    activeObject.add(new Coin(x, y, cam));
                    myMouse.mouseIsClicked = false;
                    break;
                        }
            }
            for (SpikePlatform spike : spikes) {

                if (myMouse.mouseIsClicked && !createNewObject && !moveObject && x >= cam.getDeltaX(spike.coorX)
                        && x <= cam.getDeltaX(spike.coorX) + spike.width && y >= cam.getDeltaY(spike.coorY)
                        && y <= cam.getDeltaY(spike.coorY) + spike.height) {
                    moveObject = true;
                    myComb.setSelectedItem("Spikes");
                    trashBin.add(spike);
                    activeObject.add(new SpikePlatform(x, y, spike.width, spike.height, cam));
                    myMouse.mouseIsClicked = false;
                    break;

                }
            }
            for(Player player: players){
                if (myMouse.mouseIsClicked && !createNewObject && !moveObject && x >= cam.getDeltaX(player.coorX)
                        && x <= cam.getDeltaX(player.coorX) + player.width && y >= cam.getDeltaY(player.coorY)
                        && y <= cam.getDeltaY(player.coorY) + player.height) {
                    moveObject = true;
                    myComb.setSelectedItem("Player");
                    trashBin.add(player);
                    activeObject.add(new Player(x, y, cam));
                    myMouse.mouseIsClicked = false;
                    break;
                        }
            }
            for(FinishPoint finishPoint: finishPoints){
                if (myMouse.mouseIsClicked && !createNewObject && !moveObject && x >= cam.getDeltaX(finishPoint.coorX)
                        && x <= cam.getDeltaX(finishPoint.coorX) + finishPoint.width && y >= cam.getDeltaY(finishPoint.coorY)
                        && y <= cam.getDeltaY(finishPoint.coorY) + finishPoint.height) {
                    moveObject = true;
                    myComb.setSelectedItem("FinishPoint");
                    trashBin.add(finishPoint);
                    activeObject.add(new FinishPoint(x, y, cam));
                    myMouse.mouseIsClicked = false;
                    break;
                        }
            }
            if(moveObject && delete){
                activeObject.clear();
                moveObject=false;
                delete=false;
                textDeleteActive.setText("Editing");
            }
            if (myMouse.mouseIsClicked && (createNewObject || moveObject)) {
                if(selectedObject=="Platform"){
                    if (moveObject) {
                        platforms.add(new Platform(x - cam.deltaX, y - cam.deltaY, activeObject.get(0).width,
                                activeObject.get(0).height, cam));
                    } else if (createNewObject) {
                        platforms.add(
                                new Platform(x - cam.deltaX, y - cam.deltaY,
                                        Integer.parseInt(myField1.getText()) * tileSize,
                                        Integer.parseInt(myField2.getText()) * tileSize, cam));
                    }
                }
                else if (selectedObject=="Enemy"){
                    if (moveObject) {
                        enemies.add(new Enemy(x - cam.deltaX, y - cam.deltaY, ((Enemy)activeObject.get(0)).leftSide,
                        ((Enemy)activeObject.get(0)).rightSide, cam));
                    } else if (createNewObject) {
                        enemies.add(
                                new Enemy(x - cam.deltaX, y - cam.deltaY,
                                        Integer.parseInt(myField1.getText()),
                                        Integer.parseInt(myField2.getText()), cam));
                    }
                }
                else if (selectedObject=="Enemy2"){
                    if (moveObject) {
                        enemies2.add(new Enemy2(x - cam.deltaX, y - cam.deltaY, ((Enemy2)activeObject.get(0)).leftSide,
                        ((Enemy2)activeObject.get(0)).rightSide, cam));
                    } else if (createNewObject) {
                        enemies2.add(
                                new Enemy2(x - cam.deltaX, y - cam.deltaY,
                                        Integer.parseInt(myField1.getText()),
                                        Integer.parseInt(myField2.getText()), cam));
                    }
                }
                else if(selectedObject=="Coin"){
                    if (moveObject) {
                        coins.add(new Coin(x - cam.deltaX, y - cam.deltaY, cam));
                    } else if (createNewObject) {
                        coins.add(
                                new Coin(x - cam.deltaX, y - cam.deltaY, cam));
                    }
                }
                else if(selectedObject=="Spikes"){
                    if (moveObject) {
                        spikes.add(new SpikePlatform(x - cam.deltaX, y - cam.deltaY, activeObject.get(0).width,
                                activeObject.get(0).height, cam));
                    } else if (createNewObject) {
                        spikes.add(
                                new SpikePlatform(x - cam.deltaX, y - cam.deltaY,
                                        Integer.parseInt(myField1.getText()) * tileSize,
                                        tileSize, cam));
                    }
                }
                else if(selectedObject=="Player"){
                    if (moveObject) {
                        players.add(new Player(x - cam.deltaX, y - cam.deltaY, cam));
                    } else if (createNewObject) {
                        players.add(
                                new Player(x - cam.deltaX, y - cam.deltaY, cam));
                    }
                }
                else if(selectedObject=="FinishPoint"){
                    if (moveObject) {
                        finishPoints.add(new FinishPoint(x - cam.deltaX, y - cam.deltaY, cam));
                    } else if (createNewObject) {
                        finishPoints.add(
                                new FinishPoint (x - cam.deltaX, y - cam.deltaY, cam));
                    }
                }
                createNewObject = false;
                moveObject = false;
                myMouse.mouseIsClicked = false;
                moveCamera = false;
                isCreated = false;
                activeObject.clear();
            }
            if (myMouse.mouseIsClicked && !moveCamera && (!createNewObject && !moveObject)) {
                moveCamera = true;

            } else if (myMouse.mouseIsClicked && moveCamera && (!createNewObject && !moveObject)) {
                moveCamera = false;

            }
            
            if (moveCamera) {
                if (!isUpdatedCamera) {
                    cam.currentPositionX = x;
                    cam.currentPositionY = y;
                    isUpdatedCamera = true;
                }
                textDeleteActive.setText("Moving Camera");
                cam.calculatePosition(x, y);
            } else if (!moveCamera && myMouse.mouseIsClicked) {
                textDeleteActive.setText("Editing");
                cam.setPosition(x, y);
                myMouse.mouseIsClicked = false;
                isUpdatedCamera = false;
            }
            for (Sprite trashObj : trashBin) {
                platforms.remove(trashObj);
                enemies.remove(trashObj);
                coins.remove(trashObj);
                spikes.remove(trashObj);
                players.remove(trashObj);
                finishPoints.remove(trashObj);
                enemies2.remove(trashObj);
            }
            myMouse.mouseIsClicked = false;
          }
        

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.drawRect(0, 0, 10, 10);

         if (createNewObject || moveObject) {
            activeObject.get(0).paintToMouse(g2, x, y);
        }

        
        
        for (Coin coin: coins){
            coin.paint(g2);
        }

        for (Enemy enemy: enemies){
            enemy.paint(g2);
        }
        for (SpikePlatform spike:spikes){
            spike.paint(g2);
        }
        for (FinishPoint finishPoint:finishPoints){
            finishPoint.paint(g2);
        }
        for (Player player:players){
            player.paint(g2);
        }
        for (Platform platform : platforms) {
            platform.paint(g2);
        }
        for (Enemy2 enemy2:enemies2){
            enemy2.paint(g2);
        }

        if(killBox!=null){
            killBox.paint(g2);
        }
        

    }

    public void resetData(){
        enemies.clear();
        platforms.clear();
        coins.clear();
        spikes.clear();
        players.clear();
        finishPoints.clear();
        enemies2.clear();
        killBox=null;
    }

}
