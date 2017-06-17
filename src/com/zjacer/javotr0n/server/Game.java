package com.zjacer.javotr0n.server;

import java.util.ArrayList;

/**
 * @author Mateusz Zając @ zjacer@gmail.com
 */
// game logic will be on server side
public class Game {

    // full hd divided by 4
    public final static int WIDTH = 480;
    public final static int HEIGHT = 270;
    public final int arena[][] = new int[WIDTH][HEIGHT];
    public ArrayList<Lightcycle> players = new ArrayList<>();

    public Game() {
        
    }
    
    public void updateGame() {
        move();
        checkBorderCollision();
    }
    
    private void move() {
        
    }

    private void checkBorderCollision() {
        
    }
    
    public static int getGameBoardWidth() {
        return WIDTH;
    }

    public static int getGameBoardHeight() {
        return HEIGHT;
    }
}
