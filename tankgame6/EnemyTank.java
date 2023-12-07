package com.hspedu.tankgame6;

import java.util.Vector;
@SuppressWarnings({"all"})
public class EnemyTank extends Tank implements Runnable {
    //Vectorで敵戦車クラスのshotを多数の保存します
    Vector<Shot> shots = new Vector<>();
    //メンバー「Enemytank」を増加し、敵戦車のVectorを取得させます。
    Vector<EnemyTank> enemyTanks = new Vector<>();

    //1、Vector<EnemyTank>はMyPanelにあります。
    //2、
    public EnemyTank(int x, int y) {
        super(x, y);
    }

    //メソッドを新規し、MyPanelのメンバー【Vector<EnemyTank> enemyTanks = new Vector<>();】
    //をEnemyTankのメンバー【enemyTank】に設置します。
    //

    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }
    //メソッドを編成します、
    // 該当敵戦車はenemyTanks中の他の戦車と衝突もしくは重複しているかどうかを判断します。

    public boolean isTuchEnemyTank() {
        //該当戦車の方向を判断します。
        switch (this.getDirect()) {
            case 0://上
                //該当敵戦車を自車以外の全ての敵戦車と比較させます。
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //Vectorから戦車を引き出します。
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank!=this) {
                        //敵戦車の方向は　上／下　の場合　
                        //xの値の範囲　【enemyTank.getX(),enemyTank.getX()+40】
                        //yの値の範囲【enemyTank.getY(),enemyTank.getY()+60】

                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //2、該当敵戦車の左上の座標値【this.getX(),this.getY()】
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //3、該当敵戦車の右上の座標値【this.getX()+40,this.getY()】
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //敵戦車の方向は　左／右　の場合　
                            //xの値の範囲　【enemyTank.getX(),enemyTank.getX()+60】
                            //yの値の範囲【enemyTank.getY(),enemyTank.getY()+40】
                            if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                                //2、該当敵戦車の左上の座標値【this.getX(),this.getY()】
                                if (this.getX() >= enemyTank.getX()
                                        && this.getX() <= enemyTank.getX() + 60
                                        && this.getY() >= enemyTank.getY()
                                        && this.getY() <= enemyTank.getY() +40) {
                                    return true;
                                }


                                //3、該当敵戦車の右上の座標値【this.getX()+40,this.getY()】
                                if (this.getX()+40 >= enemyTank.getX()
                                        && this.getX()+40 <= enemyTank.getX() + 60
                                        && this.getY() >= enemyTank.getY()
                                        && this.getY() <= enemyTank.getY() +40) {
                                    return true;
                                }
                            }
                        }
                    }

                }
                break;
            case 1://右
                //該当敵戦車を自車以外の全ての敵戦車と比較させます。
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //Vectorから戦車を引き出します。
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank!=this) {
                        //敵戦車の方向は　上／下　の場合　
                        //xの値の範囲　【enemyTank.getX(),enemyTank.getX()+40】
                        //yの値の範囲【enemyTank.getY(),enemyTank.getY()+60】

                        if (enemyTank.getDirect() == 0 ||  enemyTank.getDirect() == 2) {
                            //2、該当敵戦車の右上の座標値【this.getX()+60,this.getY()】
                            if (this.getX()+60>= enemyTank.getX()
                                    && this.getX()+60 <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //3、該当敵戦車の右下の座標値【this.getX()+60,this.getY()+40】
                            if (this.getX() + 60 >= enemyTank.getX()
                                    && this.getX() + 60 <= enemyTank.getX() + 40
                                    && this.getY()+40 >= enemyTank.getY()
                                    && this.getY()+40 <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //敵戦車の方向は　左／右　の場合　
                            //xの値の範囲　【enemyTank.getX(),enemyTank.getX()+60】
                            //yの値の範囲【enemyTank.getY(),enemyTank.getY()+40】
                            if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                                //2、該当敵戦車の右上の座標値【this.getX()+60,this.getY()】
                                if (this.getX()+60 >= enemyTank.getX()
                                        && this.getX()+60 <= enemyTank.getX() + 60
                                        && this.getY() >= enemyTank.getY()
                                        && this.getY() <= enemyTank.getY() +40) {
                                    return true;
                                }


                                //3、該当敵戦車の右下の座標値【this.getX()+60,this.getY()+40】
                                if (this.getX()+60 >= enemyTank.getX()
                                        && this.getX()+60 <= enemyTank.getX() + 60
                                        && this.getY()+40 >= enemyTank.getY()
                                        && this.getY()+40 <= enemyTank.getY() +40) {
                                    return true;
                                }
                            }
                        }
                    }
                }
                break;
            case 2://下
                //該当敵戦車を自車以外の全ての敵戦車と比較させます。
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //Vectorから戦車を引き出します。
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank!=this) {
                        //敵戦車の方向は　上／下　の場合　
                        //xの値の範囲　【enemyTank.getX(),enemyTank.getX()+40】
                        //yの値の範囲【enemyTank.getY(),enemyTank.getY()+60】

                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //2、該当敵戦車の左下の座標値【this.getX(),this.getY()+60】
                            if (this.getX() >= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY()+60 >= enemyTank.getY()
                                    && this.getY()+60 <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //3、該当敵戦車の右下の座標値【this.getX()+40,this.getY()+60】
                            if (this.getX() + 40 >= enemyTank.getX()
                                    && this.getX() + 40 <= enemyTank.getX() + 40
                                    && this.getY()+60 >= enemyTank.getY()
                                    && this.getY()+60 <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //敵戦車の方向は　左／右　の場合　
                            //xの値の範囲　【enemyTank.getX(),enemyTank.getX()+60】
                            //yの値の範囲【enemyTank.getY(),enemyTank.getY()+40】
                            if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                                //2、該当敵戦車の左下の座標値【this.getX(),this.getY()+60】
                                if (this.getX() >= enemyTank.getX()
                                        && this.getX() <= enemyTank.getX() + 60
                                        && this.getY()+60 >= enemyTank.getY()
                                        && this.getY()+60 <= enemyTank.getY() +40) {
                                    return true;
                                }


                                //3、該当敵戦車の右下の座標値【this.getX()+40,this.getY()+60】
                                if (this.getX()+40 >= enemyTank.getX()
                                        && this.getX()+40 <= enemyTank.getX() + 60
                                        && this.getY()+60 >= enemyTank.getY()
                                        && this.getY()+60 <= enemyTank.getY() +40) {
                                    return true;
                                }
                            }
                        }
                    }

                }
                break;
            case 3://左
                //該当敵戦車を自車以外の全ての敵戦車と比較させます。
                for (int i = 0; i < enemyTanks.size(); i++) {
                    //Vectorから戦車を引き出します。
                    EnemyTank enemyTank = enemyTanks.get(i);
                    if (enemyTank!=this) {
                        //敵戦車の方向は　上／下　の場合　
                        //xの値の範囲　【enemyTank.getX(),enemyTank.getX()+40】
                        //yの値の範囲【enemyTank.getY(),enemyTank.getY()+60】

                        if (enemyTank.getDirect() == 0 || enemyTank.getDirect() == 2) {
                            //2、該当敵戦車の左上の座標値【this.getX(),this.getY()】
                            if (this.getX()>= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY() >= enemyTank.getY()
                                    && this.getY() <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //3、該当敵戦車の左下の座標値【this.getX(),this.getY()+40】
                            if (this.getX()>= enemyTank.getX()
                                    && this.getX() <= enemyTank.getX() + 40
                                    && this.getY()+40 >= enemyTank.getY()
                                    && this.getY()+40 <= enemyTank.getY() + 60) {
                                return true;
                            }
                            //敵戦車の方向は　左／右　の場合　
                            //xの値の範囲　【enemyTank.getX(),enemyTank.getX()+60】
                            //yの値の範囲【enemyTank.getY(),enemyTank.getY()+40】
                            if (enemyTank.getDirect() == 1 || enemyTank.getDirect() == 3) {
                                //2、該当敵戦車の左上の座標値【this.getX(),this.getY()】
                                if (this.getX() >= enemyTank.getX()
                                        && this.getX() <= enemyTank.getX() + 60
                                        && this.getY() >= enemyTank.getY()
                                        && this.getY() <= enemyTank.getY() +40) {
                                    return true;
                                }


                                //3、該当敵戦車の左下の座標値【this.getX(),this.getY()+40】
                                if (this.getX() >= enemyTank.getX()
                                        && this.getX() <= enemyTank.getX() + 60
                                        && this.getY()+40 >= enemyTank.getY()
                                        && this.getY()+40 <= enemyTank.getY() +40) {
                                    return true;
                                }
                            }
                        }
                    }
                }
                break;

        }
        return false;
    }

    @Override
    public void run() {
        while (true) {
            //shots size()=0,　を判断する
            //０になった場合は弾を生成し,shotsコレクションに追加し、起動します。
            if (isLive && shots.size() == 0) {
                //戦車の方向を判断し、弾を生成します。
                Shot s = null;
                switch (getDirect()) {
                    case 0:
                        s = new Shot(getX() + 20, getY(), 0);
                        break;
                    case 1:
                        s = new Shot(getX() + 60, getY() + 20, 1);
                        break;

                    case 2:
                        s = new Shot(getX() + 20, getY() + 60, 2);
                        break;

                    case 3:
                        s = new Shot(getX(), getY() + 20, 3);

                        break;
                }
                shots.add(s);
                new Thread(s).start();
            }

            switch (getDirect()){
                case 0:
                    for (int i = 0; i < 30; i++) {
                        if (isLive&&getY()>0&&!isTuchEnemyTank()){
                            moveUp();
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < 30; i++) {
                        if (isLive&&getX()+65 <= 1000&&!isTuchEnemyTank()) {
                            moveRight();
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < 30; i++) {
                        if (isLive&&getY()+60<750&&!isTuchEnemyTank()) {
                            moveDown();
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < 30; i++) {
                        if (isLive&&getX() > 0&&!isTuchEnemyTank()) {
                            moveLeft();
                            try {
                                Thread.sleep(100);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                    break;
            }
            try {
                Thread.sleep(100);         } catch (InterruptedException e) {             throw new RuntimeException(e);
           }
            setDirect((int)(Math.random()*4));//0-3
            //いつ終了するの
            if (!isLive){
                break;
            }
        }
    }
}
