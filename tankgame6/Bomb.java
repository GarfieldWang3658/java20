package com.hspedu.tankgame6;

public class Bomb {
    int x;
    int y;
    int life=9;//ライフ
    boolean isLive = true;

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void lifeDown(){//bombの画像効果を引き出せる
        if (life>0){
            life--;
        }else {
            isLive=false;
        }
    }
}
