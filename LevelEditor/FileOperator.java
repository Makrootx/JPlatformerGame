import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileOperator {

    File newFile;

    String fileName = "New_level.txt";

    public void setFile(String path, ArrayList<Platform> platforms, ArrayList<Enemy> enemies, ArrayList<Coin> coins, ArrayList<Player> players, ArrayList<SpikePlatform> spikes, ArrayList<FinishPoint> finishPoints, ArrayList<Enemy2> enemies2, KillBox killBox) {
        newFile = new File(path);
        try {
            FileWriter myWriter = new FileWriter(path);
            myWriter.write("#Platforms\n");
            for(Platform platform:platforms){
                String[] data=platform.getParametrs();
                for(int i=0; i<data.length; i++){
                    myWriter.write(data[i]);
                    if(i!=data.length-1){
                        myWriter.write(" ");
                    }
                }
                myWriter.write("\n");
            }
            myWriter.write("#Enemies\n");
            for(Enemy enemy:enemies){
                String[] data=enemy.getParametrs();
                for(int i=0; i<data.length; i++){
                    myWriter.write(data[i]);
                    if(i!=data.length-1){
                        myWriter.write(" ");
                    }
                }
                myWriter.write("\n");
            }
            myWriter.write("#Ene2mies\n");
            for(Enemy2 enemy2:enemies2){
                String[] data=enemy2.getParametrs();
                for(int i=0; i<data.length; i++){
                    myWriter.write(data[i]);
                    if(i!=data.length-1){
                        myWriter.write(" ");
                    }
                }
                myWriter.write("\n");
            }
            myWriter.write("#Coins\n");
            for(Coin coin:coins){
                String[] data=coin.getParametrs();
                for(int i=0; i<data.length; i++){
                    myWriter.write(data[i]);
                    if(i!=data.length-1){
                        myWriter.write(" ");
                    }
                }
                myWriter.write("\n");
            }
            myWriter.write("#Player\n");
            for(Player player:players){
                String[] data=player.getParametrs();
                for(int i=0; i<data.length; i++){
                    myWriter.write(data[i]);
                    if(i!=data.length-1){
                        myWriter.write(" ");
                    }
                }
                myWriter.write("\n");
            }
            myWriter.write("#FinishPoint\n");
            for(FinishPoint finishPoint:finishPoints){
                String[] data=finishPoint.getParametrs();
                for(int i=0; i<data.length; i++){
                    myWriter.write(data[i]);
                    if(i!=data.length-1){
                        myWriter.write(" ");
                    }
                }
                myWriter.write("\n");
            }
            myWriter.write("#Spikes\n");
            for(SpikePlatform spike:spikes){
                String[] data=spike.getParametrs();
                for(int i=0; i<data.length; i++){
                    myWriter.write(data[i]);
                    if(i!=data.length-1){
                        myWriter.write(" ");
                    }
                }
                myWriter.write("\n");
            }
            if(killBox!=null){
            myWriter.write("#Killbox\n");
            myWriter.write(killBox.getParametrs()[0]);
                myWriter.write("\n");
            }            
            myWriter.close();
        } catch (IOException e) {

        }
    }



    public ArrayList<Platform> GetFileDataPlatforms(String path, CameraOperator cam, int tileSize) {
        ArrayList<Platform> allPlatforms = new ArrayList<Platform>();
        File dataFile = new File(path);
        boolean readeble = false;
        int[] endData = new int[4];
        try {
            Scanner sc = new Scanner(dataFile);
            String data;
            char[] cr = new char[100];
            while (sc.hasNextLine()) {
                data = sc.nextLine();
                cr = data.toCharArray();
                if(cr.length<5){
                    continue;
                }
                if (cr[0] == '#') {
                    readeble = false;
                }
                if (cr[0] == '#' && cr[4] == 't') {
                    readeble = true;
                    continue;
                }
                if (readeble) {
                    int startIndex = 0;
                    int numberOfData = 0;
                    for (int i = 0; i < data.length(); i++) {
                        if (cr[i] == ' ') {
                            endData[numberOfData] = Integer.parseInt(data.substring(startIndex, i));
                            numberOfData++;
                            startIndex = i + 1;
                        }
                        if (i == data.length() - 1) {
                            endData[numberOfData] = Integer.parseInt(data.substring(startIndex, data.length()));
                        }
                    }
                    allPlatforms.add(new Platform(endData[0], endData[1], endData[2]*tileSize,
                            endData[3]*tileSize, cam));
                    // System.out.println(data);
                }
            }

            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }

        return (allPlatforms);

    }

    public ArrayList<SpikePlatform> GetFileDataSpikes(String path, CameraOperator cam, int tileSize) {
        ArrayList<SpikePlatform> allSpikes = new ArrayList<SpikePlatform>();
        File dataFile = new File(path);
        boolean readeble = false;
        int[] endData = new int[4];
        try {
            Scanner sc = new Scanner(dataFile);
            String data;
            char[] cr = new char[100];
            while (sc.hasNextLine()) {
                data = sc.nextLine();
                cr = data.toCharArray();
                if(cr.length<5){
                    continue;
                }
                if (cr[0] == '#') {
                    readeble = false;
                }
                if (cr[0] == '#' && cr[4] == 'k') {
                    readeble = true;
                    continue;
                }
                if (readeble) {
                    int startIndex = 0;
                    int numberOfData = 0;
                    for (int i = 0; i < data.length(); i++) {
                        if (cr[i] == ' ') {
                            endData[numberOfData] = Integer.parseInt(data.substring(startIndex, i));
                            numberOfData++;
                            startIndex = i + 1;
                        }
                        if (i == data.length() - 1) {
                            endData[numberOfData] = Integer.parseInt(data.substring(startIndex, data.length()));
                        }
                    }
                    allSpikes.add(new SpikePlatform(endData[0], endData[1], endData[2]*tileSize,
                            tileSize, cam));
                    // System.out.println(data);
                }
            }

            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }

        return (allSpikes);

    }

    public ArrayList<Coin> GetFileDataCoins(String path, CameraOperator cam) {
        ArrayList<Coin> allCoins = new ArrayList<Coin>();
        File dataFile = new File(path);
        boolean readeble = false;
        int[] endData = new int[2];
        try {
            Scanner sc = new Scanner(dataFile);
            String data;
            char[] cr = new char[100];
            while (sc.hasNextLine()) {
                data = sc.nextLine();
                cr = data.toCharArray();
                if(cr.length<5){
                    continue;
                }
                if (cr[0] == '#') {
                    readeble = false;
                }
                if (cr[0] == '#' && cr[4] == 'n') {
                    readeble = true;
                    continue;
                }
                if (readeble) {
                    int startIndex = 0;
                    int numberOfData = 0;
                    for (int i = 0; i < data.length(); i++) {
                        if (cr[i] == ' ') {
                            endData[numberOfData] = Integer.parseInt(data.substring(startIndex, i));
                            numberOfData++;
                            startIndex = i + 1;
                        }
                        if (i == data.length() - 1) {
                            endData[numberOfData] = Integer.parseInt(data.substring(startIndex, data.length()));
                        }
                    }
                    allCoins.add(new Coin(endData[0], endData[1], cam));
                    // System.out.println(data);
                }
            }

            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }

        return (allCoins);

    }

    public ArrayList<Player> GetFileDataPlayer(String path, CameraOperator cam) {
        ArrayList<Player> allPlayers = new ArrayList<Player>();
        File dataFile = new File(path);
        boolean readeble = false;
        int[] endData = new int[2];
        try {
            Scanner sc = new Scanner(dataFile);
            String data;
            char[] cr = new char[100];
            while (sc.hasNextLine()) {
                data = sc.nextLine();
                cr = data.toCharArray();
                if(cr.length<5){
                    continue;
                }
                if (cr[0] == '#') {
                    readeble = false;
                }
                if (cr[0] == '#' && cr[4] == 'y') {
                    readeble = true;
                    continue;
                }
                if (readeble) {
                    int startIndex = 0;
                    int numberOfData = 0;
                    for (int i = 0; i < data.length(); i++) {
                        if (cr[i] == ' ') {
                            endData[numberOfData] = Integer.parseInt(data.substring(startIndex, i));
                            numberOfData++;
                            startIndex = i + 1;
                        }
                        if (i == data.length() - 1) {
                            endData[numberOfData] = Integer.parseInt(data.substring(startIndex, data.length()));
                        }
                    }
                    allPlayers.add(new Player(endData[0], endData[1], cam));
                    // System.out.println(data);
                }
            }

            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }

        return (allPlayers);

    }

    public ArrayList<FinishPoint> GetFileDataFinishPoint(String path, CameraOperator cam) {
        ArrayList<FinishPoint> allFinishPoints = new ArrayList<FinishPoint>();
        File dataFile = new File(path);
        boolean readeble = false;
        int[] endData = new int[2];
        try {
            Scanner sc = new Scanner(dataFile);
            String data;
            char[] cr = new char[100];
            while (sc.hasNextLine()) {
                data = sc.nextLine();
                cr = data.toCharArray();
                if(cr.length<5){
                    continue;
                }
                if (cr[0] == '#') {
                    readeble = false;
                }
                if (cr[0] == '#' && cr[4] == 'i') {
                    readeble = true;
                    continue;
                }
                if (readeble) {
                    int startIndex = 0;
                    int numberOfData = 0;
                    for (int i = 0; i < data.length(); i++) {
                        if (cr[i] == ' ') {
                            endData[numberOfData] = Integer.parseInt(data.substring(startIndex, i));
                            numberOfData++;
                            startIndex = i + 1;
                        }
                        if (i == data.length() - 1) {
                            endData[numberOfData] = Integer.parseInt(data.substring(startIndex, data.length()));
                        }
                    }
                    allFinishPoints.add(new FinishPoint(endData[0], endData[1], cam));
                    // System.out.println(data);
                }
            }

            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }

        return (allFinishPoints);

    }

    public KillBox GetFileDataKillBox(String path, CameraOperator cam) {
        KillBox killBox;
        File dataFile = new File(path);
        boolean readeble = false;
        int[] endData = new int[1];
        endData[0]=400;
        try {
            Scanner sc = new Scanner(dataFile);
            String data;
            char[] cr = new char[100];
            while (sc.hasNextLine()) {
                data = sc.nextLine();
                cr = data.toCharArray();
                /*if(cr.length<5){
                    continue;
                }*/
                if (cr[0] == '#') {
                    readeble = false;
                }
                if (cr[0] == '#' && cr[4] == 'l') {
                    readeble = true;
                    continue;
                }
                if (readeble) {
                    endData[0]=Integer.parseInt(data);
                    killBox=new KillBox(endData[0], cam);
                    return(killBox);
                    // System.out.println(data);
                }
            }

            sc.close();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return (null);

    }

    public ArrayList<Enemy> GetFileDataEnemy(String path, CameraOperator cam, int tilesize) {
        ArrayList<Enemy> allEnemies = new ArrayList<Enemy>();
        File dataFile = new File(path);
        boolean readeble = false;
        int[] endData = new int[4];
        try {
            Scanner sc = new Scanner(dataFile);
            String data;
            char[] cr = new char[100];
            while (sc.hasNextLine()) {
                data = sc.nextLine();
                cr = data.toCharArray();
                if(cr.length<5){
                    continue;
                }
                if (cr[0] == '#') {
                    readeble = false;
                }
                if (cr[0] == '#' && cr[4] == 'm') {
                    readeble = true;
                    continue;
                }
                if (readeble) {
                    int startIndex = 0;
                    int numberOfData = 0;
                    if(cr[0]=='#'){
                        break;
                    }
                    for (int i = 0; i < data.length(); i++) {
                        if (cr[i] == ' ') {
                            endData[numberOfData] = Integer.parseInt(data.substring(startIndex, i));
                            numberOfData++;
                            startIndex = i + 1;
                        }
                        if (i == data.length() - 1) {
                            endData[numberOfData] = Integer.parseInt(data.substring(startIndex, data.length()));
                        }
                    }
                    allEnemies.add(new Enemy(endData[0], endData[1], endData[0]-endData[3], endData[2]- endData[0]-tilesize, cam));
                    // System.out.println(data);
                }
            }

            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }

        return (allEnemies);

    }

    public ArrayList<Enemy2> GetFileDataEnemy2(String path, CameraOperator cam, int tilesize) {
        ArrayList<Enemy2> allEnemies = new ArrayList<Enemy2>();
        File dataFile = new File(path);
        boolean readeble = false;
        int[] endData = new int[4];
        try {
            Scanner sc = new Scanner(dataFile);
            String data;
            char[] cr = new char[100];
            while (sc.hasNextLine()) {
                data = sc.nextLine();
                cr = data.toCharArray();
                if(cr.length<5){
                    continue;
                }
                if (cr[0] == '#') {
                    readeble = false;
                }
                if (cr[0] == '#' && cr[4] == '2') {
                    readeble = true;
                    continue;
                }
                if (readeble) {
                    int startIndex = 0;
                    int numberOfData = 0;
                    if(cr[0]=='#'){
                        break;
                    }
                    for (int i = 0; i < data.length(); i++) {
                        if (cr[i] == ' ') {
                            endData[numberOfData] = Integer.parseInt(data.substring(startIndex, i));
                            numberOfData++;
                            startIndex = i + 1;
                        }
                        if (i == data.length() - 1) {
                            endData[numberOfData] = Integer.parseInt(data.substring(startIndex, data.length()));
                        }
                    }
                    allEnemies.add(new Enemy2(endData[0], endData[1], endData[0]-endData[3], endData[2]- endData[0]-tilesize, cam));
                    // System.out.println(data);
                }
            }

            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }

        return (allEnemies);

    }
}
