package com.zjacer.javotr0n.server;

import java.util.ArrayList;

/**
 * @author Mateusz Zając @ zjacer@gmail.com
 */
public class Lightcycle {

    private int id;
    //private static int playerCounter = 0;
    public ArrayList<Integer> trailX = new ArrayList<>();
    public ArrayList<Integer> trailY = new ArrayList<>();
    
    public Lightcycle(int id) {

        this.id = id;
        trailX.add(0);
        trailY.add(0);
        //playerCounter++;
    }
}
