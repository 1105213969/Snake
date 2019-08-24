package com.zsw.snake;

import java.util.Random;

/**
 * @program: Snake
 * @description: 食物类
 * @author: shengweiz
 * @create: 2019-08-18 16:13
 **/
public class Food {

    private int x;

    private int y;

    private int size = 15;


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

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
