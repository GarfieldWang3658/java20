package com.hspedu.tankgame6;

import java.io.*;
import java.util.Vector;

public class Recorder {
    //変数を定義し、撃墜した敵戦車の数を記録します。
    private static int allEnemyTankNum=0;
    //IOオブジェクトを定義します。
    private static FileWriter fw = null;
    private static BufferedWriter bw = null;
    private static BufferedReader br = null;
    private static String recordFile = "/Users/garfield/Documents/JavaE-Leanning/Chapter20/src/myRecord.txt";

    //ゲームを退出時、allEnemyTnakNumをrecordFileに保存します。
    //KeepRecordをアップグレードします。敵戦車の座標を保存します。
    private static Vector<EnemyTank>enemyTanks=null;
    //Vectorを定義し、MyPanelの敵戦車Verctorオブジェクトを指します
    private static Vector<Node> nodes = new Vector<>();

    //記録のファイルに戻ります。
    public static String getRecordFile() {
        return recordFile;
    }

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }
    //メソッドを追加し、recordFileを読み取ります
    public static Vector<Node>getNodesEnemyTankRec(){
        try {
            br = new BufferedReader(new FileReader(recordFile));
            allEnemyTankNum = Integer.parseInt(br.readLine());
            //ファイルを循環に読み取り、nodesのクラスを生成します。
            String line ="";
            while((line=br.readLine())!=null){
                String[] xyd = line.split(" ");
                Node node = new Node(Integer.parseInt(xyd[0]), Integer.parseInt(xyd[1]), Integer.parseInt(xyd[2]));
                nodes.add(node);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (br!=null){
                try {
                    br.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }


        return nodes;
    }

    public static void keepRecord(){
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(recordFile));
            bw.write(allEnemyTankNum + "\r\n");
            //bw.newLine();
            //敵戦車のVectorを走査します
            //属性を定義し、setで敵戦車のVectorを取得します。
            for (int i = 0; i < enemyTanks.size(); i++) {
                //敵戦車を引き出す
                EnemyTank enemyTank = enemyTanks.get(i);
                if (enemyTank.isLive) {
                    //敵戦車の情報を保存します。
                    String record = enemyTank.getX() + " " + enemyTank.getY() + " " + enemyTank.getDirect();
                    bw.write(record + "\r\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bw!=null){
                try {
                    bw.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                }

        }
    }

    public static int getAllEnemyTankNum() {
        return allEnemyTankNum;
    }

    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recorder.allEnemyTankNum = allEnemyTankNum;
    }
    //
    public static void addAllEnemyTankNum(){
        Recorder.allEnemyTankNum++;
    }
}
