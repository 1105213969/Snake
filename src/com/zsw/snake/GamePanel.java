package com.zsw.snake;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


/**
 * @program: Snake
 * @description:
 * @author: shengweiz
 * @create: 2019-08-18 09:53
 **/
public class GamePanel extends JPanel {

    private static BufferedImage map;

    private static BufferedImage bg;

    private static BufferedImage foodImg;

    private List<SnakeNode> snake = new LinkedList<>();

    private Food food = null;

    private static boolean overFlag = false;//是否结束

    private static boolean eatFoodFlag = false;//是否吃到食物

    static {
        try {
            map = ImageIO.read(GamePanel.class.getResource("map.jpg"));//地图
            bg = ImageIO.read(GamePanel.class.getResource("bg.jpg"));//背景图片
            foodImg = ImageIO.read(GamePanel.class.getResource("food.jpg"));//食物图片
        } catch (IOException e) {
            System.out.println("图片加载失败");
            e.printStackTrace();
        }
    }

    private final static int WIDTH = bg.getWidth();//宽度

    private final static int HEIGHT = bg.getHeight();//高度

    private static int S = 15;//蛇每次移动距离

    private static int sleepTime = 100;

    private static int score = 0;//得分

    public void initSnake() {
        snake.add(new SnakeNode());
        SnakeNode node2 = new SnakeNode();
        node2.setX(snake.get(0).getX());
        node2.setY(snake.get(0).getY() - node2.getSize());
        snake.add(node2);
    }

    public void initFood() {
        food = new Food();
        food.setX(new Random().nextInt(WIDTH));
        food.setY(new Random().nextInt(HEIGHT));
    }

    public GamePanel() {
        //new BGMusicPlayer("C:\\Users\\周胜伟\\Music\\bg.mp3").start();//播放背景音乐
        initSnake();
        new Thread(()->{
            boolean flag = true;
            while (flag) {
                move();
                if (isDead()) {
                    flag = false;
                    overFlag = true;
                }
                if (isHitFood()) {
                    //长大
                    eatFoodFlag = true;
                    grow();
                }
                repaint();
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        this.addKeyListener(new KeyAdapter() { //创建监听器，监听按下的按键
            public void keyPressed(KeyEvent e) {
                int c = e.getKeyCode();//获取按下的键
                System.out.println("" + c);
                changeDirection(c);
            }
        });
        this.setFocusable(true);
    }

    public void grow() {//长大
        SnakeNode node = new SnakeNode();
        node.setDirection(snake.get(snake.size() - 1).getDirection());
        switch (snake.get(0).getDirection()) {
            case 1 : //蛇头向左
                node.setY(snake.get(0).getY());
                node.setX(snake.get(0).getX() - S);
                break;
            case 2: //向上
                node.setX(snake.get(0).getX());
                node.setY(snake.get(0).getY() - S);
                break;
            case 3: //向右
                node.setY(snake.get(0).getY());
                node.setX(snake.get(0).getX() + S);
                break;
            case 4: //向下
                node.setX(snake.get(0).getX());
                node.setY(snake.get(0).getY() + S);
                break;
            default:break;
        }
        snake.add(0, node);
        food = null;//食物重新生成
        sleepTime -= 5;//速度变快
        score += 10;//分数加10
    }

    public void changeDirection(int c) { //改变蛇的方向
        switch(c) {
            case 37:
                if (snake.get(0).getDirection() == 3)
                    break;
                snake.get(0).setDirection(1);
                break;
            case 39:
                if (snake.get(0).getDirection() == 1)
                    break;
                snake.get(0).setDirection(3);
                break;
            case 38:
                if (snake.get(0).getDirection() == 4)
                    break;
                snake.get(0).setDirection(2);
                break;
            case 40:
                if (snake.get(0).getDirection() == 2)
                    break;
                snake.get(0).setDirection(4);
                break;
            default:break;
        }
    }

    public void move() {
        SnakeNode node = snake.get(snake.size() - 1);
        snake.remove(snake.size() - 1);//删除尾节点
        node.setDirection(snake.get(0).getDirection());//得到头节点的方向
        switch (snake.get(0).getDirection()) {
            case 1 : //蛇头向左
                node.setY(snake.get(0).getY());
                node.setX(snake.get(0).getX() - S);
                break;
            case 2: //向上
                node.setX(snake.get(0).getX());
                node.setY(snake.get(0).getY() - S);
                break;
            case 3: //向右
                node.setY(snake.get(0).getY());
                node.setX(snake.get(0).getX() + S);
                break;
            case 4: //向下
                node.setX(snake.get(0).getX());
                node.setY(snake.get(0).getY() + S);
                break;
            default:break;
        }
        snake.add(0, node);
    }

    boolean isDead() {//判断是否死亡
        return isHeatBody() || isHeatWall();
    }

    boolean isHeatWall() {//判断是否撞墙
        SnakeNode node = snake.get(0);
        if (node.getX() <= 0 || node.getY() <= 0 || node.getX() >= WIDTH || node.getY() >= HEIGHT) {
            System.out.println("撞墙");
            return true;
        }
        return false;
    }

    boolean isHeatBody() {//判断蛇头是否接触到身体
        SnakeNode node = snake.get(0);
//        for (int i = 2; i < snake.size(); ++i) {
//            if ((Math.abs(node.getX() - snake.get(i).getX()) == 0
//                    && Math.abs(node.getY() - snake.get(i).getY()) == 0) && snake.size() > 4) {
//                System.out.println("蛇头碰到身体");
//                return true;
//            }
//        }
        return false;
    }

    public boolean isHitFood() {//是否吃到食物
        SnakeNode node = snake.get(0);
        if (food != null) {
            if (Math.abs(node.getX() - food.getX()) < 20 && Math.abs(node.getY() - food.getY()) < 20) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        //绘制背景图片
        g.drawImage(map, 0, 0, WIDTH+30, HEIGHT+50, null);
        //画蛇
        g.setColor(Color.GREEN);
        for (int i = 0; i < snake.size(); ++i) {
            g.fillOval(snake.get(i).getX(), snake.get(i).getY(), snake.get(i).getSize(), snake.get(i).getSize());
        }
        if (food == null) {
            food = new Food();
            food.setX(new Random().nextInt(WIDTH));
            food.setY(new Random().nextInt(HEIGHT));
            g.drawImage(foodImg, food.getX(), food.getY(), food.getSize(), food.getSize(), null);
        } else {
            g.drawImage(foodImg, food.getX(), food.getY(), food.getSize(), food.getSize(), null);
        }
        g.setColor(Color.black);
        g.drawString("SCORE:" + score, 20, 20);
        if (overFlag) {//游戏结束
            g.setColor(Color.red);
            g.setFont(new Font("宋体",Font.BOLD,60));
            g.drawString("游戏结束", WIDTH / 2 - 100, HEIGHT / 2);
        }
    }

 }
