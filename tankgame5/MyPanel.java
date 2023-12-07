package com.hspedu.tankgame5;
/*
ゲームフィールドの設定
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

//Panleに弾をリペイントさせるために、RunnableインタフェースをMyPanelに実装します。
//スレッドとして使用します

public class MyPanel  extends JPanel implements KeyListener,Runnable  {
    //Heroを定義します
    Hero hero = null;
    Vector<EnemyTank> enemyTanks = new Vector<>();
    //Nodeオブジェクトを保存するVectorを定義します、敵戦車の座標と方向性をリカバリします
    Vector<Node> nodes = new Vector<>();

    //Vectorを定義し、Bombを保存します
    Vector<com.hspedu.tankgame5.Bomb> bombs = new Vector<>();
    int enenyTankSize = 3;

    //画像を定義し、爆発の効果を表す
    //命中する時、bombsにBombオブジェクトを入れます
    Image image1=null;
    Image image2=null;
    Image image3=null;


    public MyPanel(String key) {
        nodes = Recorder.getNodesEnemyTankRec();
        //MyPanelオブジェクトのenemyTanksをRecorderのenemyTanksに設置します。
        Recorder.setEnemyTanks(enemyTanks);

        hero = new com.hspedu.tankgame5.Hero(500, 500);
        hero.setSpeed(5);

        switch (key){
            case "1":
                for (int i = 0; i < enenyTankSize; i++) {
                    com.hspedu.tankgame5.EnemyTank enemyTank = new com.hspedu.tankgame5.EnemyTank(100 * (i + 1), 0);
                    //enemyTanksをenemyTanksに設置します。!!!
                    enemyTank.setEnemyTanks(enemyTanks);
                    enemyTank.setDirect(2);
                    new Thread(enemyTank).start();
                    //
                    com.hspedu.tankgame5.Shot shot = new com.hspedu.tankgame5.Shot(enemyTank.getX()+20,enemyTank.getY()+60,enemyTank.getDirect());
                    //enemyTankのVector　メンバー
                    enemyTank.shots.add(shot);
                    //shot オブジェクトを起動する
                    new Thread(shot).start();

                    enemyTanks.add(enemyTank);
                }
                break;
            case "2"://前回のゲームを続きます

                for (int i = 0; i < nodes.size(); i++) {
                    Node node = nodes.get(i);
                   EnemyTank enemyTank = new EnemyTank(node.getX() ,node.getY());
                    //enemyTanksをenemyTanksに設置します。!!!
                    enemyTank.setEnemyTanks(enemyTanks);
                    enemyTank.setDirect(node.getDirect());
                    new Thread(enemyTank).start();
                    //
                    com.hspedu.tankgame5.Shot shot = new com.hspedu.tankgame5.Shot(enemyTank.getX()+20,enemyTank.getY()+60,enemyTank.getDirect());
                    //enemyTankのVector　メンバー
                    enemyTank.shots.add(shot);
                    //shot オブジェクトを起動する
                    new Thread(shot).start();

                    enemyTanks.add(enemyTank);
                }
                break;
            default:
                System.out.println("入力が間違いました");
        }


        //画像を初期化します。
        image1 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("bomb1.jpg"));
        image2 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("bomb2.jpg"));
        image3 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("bomb3.jpg"));

    }
    //メソッドを新規し、撃墜数を表示ます。
    public void showInfo(Graphics g){
        //プレイヤ＝のスコア
        g.setColor(Color.BLACK);
        Font font = new Font("宋体",Font.BOLD, 25);
        g.setFont(font);

        g.drawString("累計撃墜数",1020,30);
        drawTank(1020,60,g,0,0);//敵戦車
        g.setColor(Color.BLACK);//ペンをリセットする必要があります。
        g.drawString(Recorder.getAllEnemyTankNum()+"",1080,100);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void run() {
        //弾を0.1秒ごとにリペイントします
        //パネルをリペイント
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //命中を判断する
//            if (hero.shot!=null&&hero.shot.isLive){
//                //敵の戦車を走査します
//                for (int i = 0; i < enemyTanks.size(); i++) {
//                    EnemyTank enemyTank = enemyTanks.get(i);
//                    hitTank(hero.shot,enemyTank);
//                }
//            }
            hitEnemyTank();
            hitHero();
            this.repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {//Wを押すと方向変換
            hero.setDirect(0);
            if (hero.getY()>0) {
                hero.moveUp();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            hero.setDirect(1);
            if (hero.getX()+60<1000) {
                hero.moveRight();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            hero.setDirect(2);
            if (hero.getY()+60<750) {
                hero.moveDown();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            hero.setDirect(3);
            if (hero.getX()>0){
                hero.moveLeft();
            }
        }
        //リペイント
        //キー「G」を押すと、shotを執行
        if (e.getKeyCode() == KeyEvent.VK_G) {
//            if (hero.shot==null||!hero.shot.isLive) {
//                System.out.println("Gを押した、射撃開始");
//                hero.shotEnemyTank();
            //上記は一発を撃つ場合
            //下記は多発を撃てるようにします
            hero.shotEnemyTank();

            }
            this.repaint();

        }


    @Override
    public void keyReleased(KeyEvent e) {

    }
    //メソッドを作成し、敵戦車の弾に命中されたかどうかを判断する
public void hitHero(){
        //敵戦車を走査します
    for (int i = 0; i < enemyTanks.size(); i++) {
        com.hspedu.tankgame5.EnemyTank enemyTank = enemyTanks.get(i);
        //敵戦車の弾を走査します
        for (int j = 0; j < enemyTank.shots.size(); j++) {
            //shotを引き出します
            com.hspedu.tankgame5.Shot shot = enemyTank.shots.get(j);
            //shotの命中を判断します
            if (hero.isLive && shot.isLive )
                hitTank(shot,hero);
        }

    }
}



    //命中されたかどうかを判断する

    //弾を複数に撃てるようになったら、命中を判断する時、
    //弾のコレクションの全ての弾は全ての敵戦車と比較し、判断します。
    public void hitEnemyTank(){
        //弾を走査します
        for (int j = 0; j < hero.shots.size(); j++) {
            com.hspedu.tankgame5.Shot shot = hero.shots.get(j);

            if (shot!=null&&shot.isLive) {
            //敵の戦車を走査します
            for (int i = 0; i < enemyTanks.size(); i++) {
                com.hspedu.tankgame5.EnemyTank enemyTank = enemyTanks.get(i);
                hitTank(shot, enemyTank);
            }
        }
        }
    }

    public void hitTank(Shot s, Tank enemyTank){
        //命中を判断する
        switch (enemyTank.getDirect()){
            case 0:
            case 2:
        if (s.x > enemyTank.getX()&& s.x < enemyTank.getX()+40&&
                s.y>enemyTank.getY()&&s.y<enemyTank.getY()+60){
            s.isLive=false;
            enemyTank.isLive=false;
            //弾が命中すると、敵戦車を削除します
            enemyTanks.remove(enemyTank);
            //敵戦車を撃破した場合、allEnemyTankNum++
            //enemyTankの対象はheroにもなります
            if (enemyTank instanceof EnemyTank){
                Recorder.addAllEnemyTankNum();
            }
            //Bombオブジェクトを新設し、bombsのコレクションに加入します。
            com.hspedu.tankgame5.Bomb bomb = new com.hspedu.tankgame5.Bomb(enemyTank.getX(), enemyTank.getY());
            bombs.add(bomb);

        }
        break;
            case 1:
            case 3:
                if (s.x > enemyTank.getX() && s.x < enemyTank.getX() + 60 &&
                            s.y > enemyTank.getY() && s.y < enemyTank.getY() + 40) {
                    s.isLive = false;
                    enemyTank.isLive = false;
                    enemyTanks.remove(enemyTank);//弾が命中すると、敵戦車を削除します
                    if (enemyTank instanceof EnemyTank){
                        Recorder.addAllEnemyTankNum();
                    }
                    com.hspedu.tankgame5.Bomb bomb = new com.hspedu.tankgame5.Bomb(enemyTank.getX(), enemyTank.getY());
                    bombs.add(bomb);
                    }
                break;
            }

    }



    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);
        //長方形を充填します、初期化カラーは黒
        showInfo(g);
        if (hero.isLive & hero!=null) {
            drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 1);
        }
        //弾を描く
//        if (hero.shot != null&& hero.shot.isLive == true){
//            g.fill3DRect(hero.shot.x-2,hero.shot.y-2,4,4,false);
//            System.out.println("弾生成");
//        }
        //heroのshotコレクションを走査します。
        for (int i = 0; i <hero.shots.size(); i++) {
            com.hspedu.tankgame5.Shot shot = hero.shots.get(i);
            if (shot != null&& shot.isLive == true){
           g.fill3DRect(shot.x-2,shot.y-2,4,4,false);
           System.out.println("弾生成");
        }else {
                //shotオブジェクトは無効になった場合、shotsコレクションから取り除きます。
                hero.shots.remove(shot);
            }

        }

        //bombsコレクションの中にbombオブジェクトが存在すれば作製します
        for (int i = 0; i < bombs.size(); i++) {
            com.hspedu.tankgame5.Bomb bomb = bombs.get(i);
            //bombのライフ値で対象の画像を作製します
            if (bomb.life>6){
                g.drawImage(image1,bomb.x,bomb.y,60,60,this);
            }else if (bomb.life>3){
                g.drawImage(image2,bomb.x,bomb.y,60,60,this);
            }else {
                g.drawImage(image3,bomb.x,bomb.y,60,60,this);
            }
            //bombのライフ値を減少させます
            bomb.lifeDown();
            //bombのライフ値は0になる場合、bombコレクションの中から削除します
            if (bomb.life == 0){
                bombs.remove(bomb);
            }



        }

        for (int i = 0; i < enemyTanks.size(); i++) {
            //今まではVectorの中に敵戦車を引き出していますが
            //生存確認時
            com.hspedu.tankgame5.EnemyTank enemyTank = enemyTanks.get(i);
            if (enemyTank.isLive) {
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 0);
                //敵の弾を描く
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    //弾を引き出し
                    com.hspedu.tankgame5.Shot shot = enemyTank.shots.get(j);
                    //作成
                    if (shot.isLive) {//isLive=true
                        g.draw3DRect(shot.x, shot.y, 1, 3, false);
                    } else {
                        //Vectorから削除します
                        enemyTank.shots.remove(shot);
                    }
                }
            }
        }

    }
    //
    /*
    X　左上のX座標
    Y　左上のY座標
    g　ペン
    driect　進行方向
    type 　種類
     */
    public void drawTank(int x,int y,Graphics g,int direct,int type){
        switch (type){
            case 0://敵
                g.setColor(Color.cyan);
                break;
            case 1://味方
                g.setColor(Color.yellow);
                break;
        }
        //進行方向に応じて、相応型の戦車を描く
        //directで進行方向を表す（0:上、１：右、２：下、３：左、）
        switch (direct){
            case 0://上に進行
                g.fill3DRect(x,y,10,60,false);//左の車輪
                g.fill3DRect(x+30,y,10,60,false);//右の車輪
                g.fill3DRect(x+10,y+10,20,40,false);//車体
                g.fillOval(x+10,y+20,20,20);//蓋
                g.drawLine(x+20,y+30,x+20,y);//主砲
                break;
            case 1://右に進行
                g.fill3DRect(x,y,60,10,false);//左の車輪
                g.fill3DRect(x,y+30,60,10,false);//右の車輪
                g.fill3DRect(x+10,y+10,40,20,false);//車体
                g.fillOval(x+20,y+10,20,20);//蓋
                g.drawLine(x+30,y+20,x+60,y+20);//主砲
                break;
            case 2://下に進行
                g.fill3DRect(x,y,10,60,false);//左の車輪
                g.fill3DRect(x+30,y,10,60,false);//右の車輪
                g.fill3DRect(x+10,y+10,20,40,false);//車体
                g.fillOval(x+10,y+20,20,20);//蓋
                g.drawLine(x+20,y+20,x+20,y+60);//主砲
                break;
            case 3://左に進行
                g.fill3DRect(x,y,60,10,false);//左の車輪
                g.fill3DRect(x,y+30,60,10,false);//右の車輪
                g.fill3DRect(x+10,y+10,40,20,false);//車体
                g.fillOval(x+20,y+10,20,20);//蓋
                g.drawLine(x+30,y+20,x,y+20);//主砲
                break;

            default:

        }
    }

}
