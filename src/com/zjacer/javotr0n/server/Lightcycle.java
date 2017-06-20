package com.zjacer.javotr0n.server;

import java.util.ArrayList;

/**
 * @author Mateusz Zając @ zjacer@gmail.com
 */
public class Lightcycle {

    int id;
    ArrayList<Integer> trailX = new ArrayList<>();
    ArrayList<Integer> trailY = new ArrayList<>();
    String color;
    private int direction = 0, trailSize = 0;
    
    public Lightcycle(int id, String color) {

        this.id = id;
        this.color = color;
        switch(id) {
            case 2: // left top corner and right direction
                trailX.add(0);
                trailY.add(0);
                direction = 0;
                break;
            case 3: // right bottom corner and left direction
                trailX.add(Game.getGameBoardWidth()-1);
                trailY.add(Game.getGameBoardHeight()-1);
                direction = 2;
                break;
            case 4: // left bottom corner and right up direction
                trailX.add(0);
                trailY.add(Game.getGameBoardHeight()-1);
                direction = 1;
                break;
            case 5: // right top corner and down direction
                trailX.add(Game.getGameBoardWidth()-1);
                trailY.add(0);
                direction = 3;
                break;
        }
    }

    void move() {
                
        // last position
        int lastX = trailX.get(trailX.size()-1);
        int lastY = trailY.get(trailY.size()-1);
        
        for (int i = trailX.size() - 1; i > 0; i--) {
            trailX.set(i, trailX.get(i-1));
            trailY.set(i, trailY.get(i-1));
        }

        switch (direction) {
            case 0: // right
                trailX.set(0, trailX.get(0) + 1);
                break;
            case 1: // up
                trailY.set(0, trailY.get(0) - 1);
                break;
            case 2: // left
                trailX.set(0, trailX.get(0) - 1);
                break;
            case 3: // down
                trailY.set(0, trailY.get(0) + 1);
                break;
        }
        
        if (trailSize >= trailX.size()) {
            trailX.add(lastX);
            trailY.add(lastY);
        }
    }
    
    void setTrailSize(int i) {
        trailSize = i;
    }
    
    void setDirection(int i) {
        direction = i;
    }
    
    void increaseTrailSize() {
        trailSize++;
    }
}
