package com.zsw.snake;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @program: Snake
 * @description: 背景音乐播放
 * @author: shengweiz
 * @create: 2019-08-24 08:34
 **/
public class BGMusicPlayer extends Thread{

    private Player player;

    private String music;

    public BGMusicPlayer(String file) {
        this.music = file;
    }

    public void run() {
        try {
            play();
        } catch (FileNotFoundException | JavaLayerException e) {
            e.printStackTrace();
        }
    }

    public void play() throws FileNotFoundException, JavaLayerException {
        BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(music));
        player = new Player(buffer);
        player.play();
    }

}
