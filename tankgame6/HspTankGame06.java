package com.hspedu.tankgame6;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;


//为了监听 键盘事件，实现接口KeyListener
public class HspTankGame06 extends JFrame  {
    MyPanel mp = null;
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        HspTankGame06 hspTankGame06 =
                new HspTankGame06();
    }
    public HspTankGame06(){
        System.out.println("キーを入力してください、１：新しいゲームを開始します　２：前回のゲームを続きます");
        String key= scanner.next();

        mp = new MyPanel(key);
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);//パネルをaddします、ゲームフィールド
        this.setSize(1300,950);
        this.addKeyListener(mp);//JFrameでキーボードイベントをリッスンします。
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);//可視化

        //JFrameの中に窓口を開くまたは閉じるの
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("窓口が閉じた事を確認しました");
                Recorder.keepRecord();
                System.exit(0);
            }
        });

    }
}
