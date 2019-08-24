package com.zsw.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @program: Snake
 * @description: 游戏入口
 * @author: shengweiz
 * @create: 2019-08-17 22:33
 **/
public class Entry extends JFrame {

    private static ImageIcon bg = new ImageIcon(Entry.class.getResource("bg.jpg"));//背景图片

    public Entry() {
        super("贪吃蛇");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(bg.getIconWidth() + 30, bg.getIconHeight() + 50);
        setLocation(200, 100);
    }

    public static void main(String[] args) {
        Entry entry = new Entry();
        Container c = entry.getContentPane();
        c.setLayout(null);
        JLabel bgLable = new JLabel(bg);
        bgLable.setBounds(0, 0, bg.getIconWidth(),
                bg.getIconHeight());//bg图片标签
        JButton startBtn = new JButton("开始游戏");//开始按钮
        JButton quitBtn = new JButton("退出游戏");//推出按钮
        startBtn.setBounds(300, 150, 100, 50);
        quitBtn.setBounds(300, 220, 100, 50);
        c.add(startBtn);
        c.add(quitBtn);
        c.add(bgLable);

        startBtn.addActionListener((ActionEvent e)->{
            //开始游戏
            new Game();
        });
        quitBtn.addActionListener((ActionEvent e)->{
            //退出游戏
            System.exit(0);
        });
        entry.setVisible(true);
    }
}
