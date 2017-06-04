package com.zjacer.javotr0n.server;

import java.util.ArrayList;

/**
 * @author Mateusz Zając @ zjacer@gmail.com
 */
// game logic will be on server side
public class Game {
    
    // full hd divided by 4
    private final int WIDTH = 480;
    private final int HEIGHT = 270;
    private final int arena[][] = new int[WIDTH][HEIGHT];
    private ArrayList<?> players = new ArrayList<>();
    private static int playerId = 0;
    
}
