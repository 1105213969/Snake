package com.zsw.snake;

import java.util.Random;

/**
 * @program: Snake
 * @description: 蛇的节点
 * @author: shengweiz
 * @create: 2019-08-18 10:16
 **/
public class SnakeNode {

    private int x = 300;

    private int y = 200;

    /**
     * 1->西
     * 2->北
     * 3->东
     * 4->南
     */
    private int direction = 4;//初始方向随机

    private int size = 15;//蛇的节点尺寸

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
