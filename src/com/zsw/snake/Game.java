package com.zsw.snake;

import javax.swing.*;
import java.awt.*;

/**
 * @program: Snake
 * @description: 游戏主场景
 * @author: shengweiz
 * @create: 2019-08-18 09:29
 **/
public class Game extends JFrame {

    private static ImageIcon bg = new ImageIcon(Entry.class.getResource("bg.jpg"));//背景图片

    public Game() {
        super("贪吃蛇");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(bg.getIconWidth() + 30, bg.getIconHeight() + 50);
        setLocation(200, 100);
        GamePanel gamePanel = new GamePanel();
        Container c = this.getContentPane();
        c.add(gamePanel);
        this.setVisible(true);
    }
}
