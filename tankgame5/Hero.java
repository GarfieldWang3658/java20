package com.hspedu.tankgame5;

import java.util.Vector;

public class Hero extends Tank {
    Shot shot = null;//shotのオブジェクトを定義します＝＞（スレッド）

    Vector<Shot> shots = new Vector<>();
    public Hero(int x, int y) {
        super(x, y);
    }

    public void shotEnemyTank(){
        if (shots.size()==5){
            return;
        }
        //shotのオブジェクト
        //heroの座標と方向に基づいてshotのオブジェクトを定義します
        switch (getDirect()){//オブジェクトheroの方向を取得します
            case 0 ://上
                shot = new Shot(getX()+20,getY(),0);
                break;
            case 1 ://右
                shot = new Shot(getX()+60,getY()+20,1);
                break;
            case 2 ://下
                shot = new Shot(getX()+20,getY()+60,2);
                break;
            case 3 ://左
                shot = new Shot(getX(),getY()+20,3);
                break;

        }
        shots.add(shot);
        //shot スレッドを起動する
            new Thread(shot).start();

    }


}
