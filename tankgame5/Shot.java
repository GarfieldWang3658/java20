package com.hspedu.tankgame5;

public class Shot implements Runnable{
    int x;//Xの座標
    int y;//Yの座標
    int direct = 0;//弾の方向
    int speed = 10;//弾のスピード

    boolean isLive = true;//弾の存在を確認する



    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {
        while (true){

            //0.5秒のスリープ

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //xとyの座標で方向を変えます
            switch (direct){
                case 0://上
                    y -= speed;
                    break;

                case 1://右
                    x +=speed;
                    break;

                case 2://下
                    y+=speed;
                    break;

                case 3://左
                    x-=speed;
                    break;

            }
            System.out.println("弾のX座標＝"+x+"\t弾のY座標＝"+y);
            //弾は敵戦車に命中する時に消失させます
            if (!(x>=0&&x<=1000&&y>=0&&y<=750&&isLive)){
                System.out.println("弾のスレッド消失");
                isLive=false;
                break;
            }

        }
    }
}
