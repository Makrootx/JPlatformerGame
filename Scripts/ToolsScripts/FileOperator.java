package Scripts.ToolsScripts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Scripts.BodyScripts.GamePanel;
import Scripts.ObjectsScripts.Coin;
import Scripts.ObjectsScripts.Enemy;
import Scripts.ObjectsScripts.Enemy2;
import Scripts.ObjectsScripts.FinishFlag;
import Scripts.ObjectsScripts.Platform;
import Scripts.ObjectsScripts.SpikePlatform;

public class FileOperator {

    public ArrayList<Platform> GetFileDataPlatforms(String path, GamePanel gp) {
        ArrayList<Platform> allPlatforms = new ArrayList<Platform>();
        File dataFile = new File("GameData/" + path);
        boolean readeble = false;
        int[] endData = new int[4];
        try {
            Scanner sc = new Scanner(dataFile);
            String data;
            char[] cr = new char[100];
            while (sc.hasNextLine()) {
                data = sc.nextLine();
                cr = data.toCharArray();
                
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
                    allPlatforms.add(new Platform(gp, endData[0], endData[1], endData[2],
                            endData[3]));
                    // System.out.println(data);
                }
            }

            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }

        return (allPlatforms);

    }

    public ArrayList<Coin> GetFileDataCoins(String path, GamePanel gp) {
        ArrayList<Coin> allCoins = new ArrayList<Coin>();
        File dataFile = new File("GameData/" + path);
        boolean readeble = false;
        int[] endData = new int[2];
        try {
            Scanner sc = new Scanner(dataFile);
            String data;
            char[] cr = new char[100];
            while (sc.hasNextLine()) {
                data = sc.nextLine();
                cr = data.toCharArray();
                
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
                    allCoins.add(new Coin(gp, endData[0], endData[1]));
                    // System.out.println(data);
                }
            }

            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }

        return (allCoins);

    }

    public ArrayList<FinishFlag> GetFileDataFinishFlag(String path, GamePanel gp) {
        ArrayList<FinishFlag> allFinishFlags = new ArrayList<FinishFlag>();
        File dataFile = new File("GameData/" + path);
        boolean readeble = false;
        int[] endData = new int[2];
        try {
            Scanner sc = new Scanner(dataFile);
            String data;
            char[] cr = new char[100];
            while (sc.hasNextLine()) {
                data = sc.nextLine();
                cr = data.toCharArray();
                
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
                    allFinishFlags.add(new FinishFlag(gp, endData[0], endData[1], gp.getCam()));
                    // System.out.println(data);
                }
            }

            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }

        return (allFinishFlags);

    }

    public int GetFileDataKillBox(String path, GamePanel gp) {
        File dataFile = new File("GameData/" + path);
        boolean readeble = false;
        int endData=777;
        try {
            Scanner sc = new Scanner(dataFile);
            String data;
            char[] cr = new char[100];
            while (sc.hasNextLine()) {
                data = sc.nextLine();
                cr = data.toCharArray();
                
                if (cr[0] == '#') {
                    readeble = false;
                }
                if (cr[0] == '#' && cr[4] == 'l') {
                    readeble = true;
                    continue;
                }
                if (readeble) {
                    endData=Integer.valueOf(data);
                }
            }

            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        return (endData);

    }

     public ArrayList<Enemy> GetFileDataEnemy(String path, GamePanel gp) {
        ArrayList<Enemy> allEnemies = new ArrayList<Enemy>();
        File dataFile = new File("GameData/" + path);
        boolean readeble = false;
        int[] endData = new int[4];
        try {
            Scanner sc = new Scanner(dataFile);
            String data;
            char[] cr = new char[100];
            while (sc.hasNextLine()) {
                data = sc.nextLine();
                cr = data.toCharArray();
                
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
                    allEnemies.add(new Enemy(gp, endData[0], endData[1], endData[2], endData[3]));
                    // System.out.println(data);
                }
            }

            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }

        return (allEnemies);

    }
    
    public ArrayList<Enemy2> GetFileDataEnemy2(String path, GamePanel gp) {
        ArrayList<Enemy2> allEnemies = new ArrayList<Enemy2>();
        File dataFile = new File("GameData/" + path);
        boolean readeble = false;
        int[] endData = new int[4];
        try {
            Scanner sc = new Scanner(dataFile);
            String data;
            char[] cr = new char[100];
            while (sc.hasNextLine()) {
                data = sc.nextLine();
                cr = data.toCharArray();
                
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
                    allEnemies.add(new Enemy2(gp, endData[0], endData[1], endData[2], endData[3]));
                    // System.out.println(data);
                }
            }

            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }

        return (allEnemies);

    }
    
    public ArrayList<SpikePlatform> GetFileDataSpikes(String path, GamePanel gp) {
        ArrayList<SpikePlatform> allSpikes = new ArrayList<SpikePlatform>();
        File dataFile = new File("GameData/" + path);
        boolean readeble = false;
        int[] endData = new int[3];
        try {
            Scanner sc = new Scanner(dataFile);
            String data;
            char[] cr = new char[100];
            while (sc.hasNextLine()) {
                data = sc.nextLine();
                cr = data.toCharArray();
                
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
                    allSpikes.add(new SpikePlatform(gp, endData[0], endData[1], endData[2]));
                    // System.out.println(data);
                }
            }

            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }

        return (allSpikes);

    }
    public int getAvailableLevels(String path){
        File dataFile = new File("GameData/" + path);
        boolean readeble = false;
        int endData=0;
        try {
            Scanner sc = new Scanner(dataFile);
            String data;
            char[] cr = new char[100];
            while (sc.hasNextLine()) {
                data = sc.nextLine();
                cr = data.toCharArray();
                if (cr[0] == '#') {
                    readeble = false;
                }
                if (cr[0] == '#' && cr[1] == 'A') {
                    readeble = true;
                    continue;
                }
                if (readeble) {
                    endData=Integer.parseInt(String.valueOf(cr));
                    break;
                    // System.out.println(data);
                }
            }

            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }

        return (endData);

    }
    public int[] GetFileDataPlayer(String path, GamePanel gp) {
        File dataFile = new File("GameData/" + path);
        boolean readeble = false;
        int[] endData = new int[2];
        try {
            Scanner sc = new Scanner(dataFile);
            String data;
            char[] cr = new char[100];
            while (sc.hasNextLine()) {
                data = sc.nextLine();
                cr = data.toCharArray();
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
                    // System.out.println(data);
                }
            }

            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }

        return (endData);

    }

    public int[] GetFileDataSettings(String path, GamePanel gp) {
        File dataFile = new File("GameData/" + path);
        boolean readeble = false;
        int index=0;
        int[] endData = new int[2];
        try {
            Scanner sc = new Scanner(dataFile);
            String data;
            char[] cr = new char[100];
            while (sc.hasNextLine()) {
                data = sc.nextLine();
                cr = data.toCharArray();
                if (cr[0] == '#') {
                    readeble = false;
                }
                if (cr[0] == '#' && cr[4] == 'l') {
                    readeble = true;
                    index=0;
                    continue;
                }
                else if(cr[0]=='#' && cr[4]=='u'){
                    readeble = true;
                    index=1;
                    continue;
                }
                if (readeble) {
                    //data=String.valueOf(cr);
                    endData[index]=Integer.parseInt(data);
                }
            }

            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return (endData);

    }

    public ControlInfo[] GetFileDataControls(String path, GamePanel gp) {
        File dataFile = new File("GameData/" + path);
        boolean readeble = false;
        ControlInfo[] controls=new ControlInfo[3];
        int code=0;
        int index=0;
        String name=null;
        try {
            Scanner sc = new Scanner(dataFile);
            String data;
            char[] cr = new char[100];
            while (sc.hasNextLine()) {
                data = sc.nextLine();
                cr = data.toCharArray();
                if (cr[0] == '#') {
                    readeble = false;
                }
                if (cr[0] == '#' && cr[4] == 't') {
                    readeble = true;
                    continue;
                }
                if (readeble) {
                    int startIndex = 0;
                    int numberOfData=0;
                    for (int i = 0; i < data.length(); i++) {
                        if (cr[i] == ' ') {
                            name = data.substring(startIndex, i);
                            numberOfData++;
                            startIndex = i + 1;
                        }
                        if (i == data.length() - 1) {
                            
                            code = Integer.parseInt(data.substring(startIndex, data.length()));
                            
                            numberOfData++;
                        }
                        if(numberOfData==2){
                        controls[index]=new ControlInfo(code, name);
                        index++;}
                    }
                    // System.out.println(data);
                }
            }

            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }

        return (controls);

    }

    public void setFile(String path, boolean fullscreen, int volumeOfSound, ControlInfo[] controls) {
        //File newFile = new File("GameData/" + path);
        try {
            FileWriter myWriter = new FileWriter("GameData/" + path);
            myWriter.write("#Fullscreen\n");
            if(fullscreen){
                myWriter.write("1\n");
            }else myWriter.write("0\n");
            myWriter.write("#Volume\n");
            myWriter.write(volumeOfSound+"\n");
            myWriter.write("#Controls\n");
            for(int i=0; i<controls.length; i++){
                String name= controls[i].getName();
                int code=controls[i].getCode();
                myWriter.write(name+" "+code+"\n");
            }
            myWriter.write("#EndLine");
            myWriter.close();
        } catch (IOException e) {

        }
    }

    public void setFileAvailebleLevels(String path, int availebleLevels) {
        //File newFile = new File("GameData/" + path);
        try {
            FileWriter myWriter = new FileWriter("GameData/" + path);
            myWriter.write("#AvaibleLevels\n");
            myWriter.write(String.valueOf(availebleLevels));
            myWriter.close();
        } catch (IOException e) {

        }
    }
}
