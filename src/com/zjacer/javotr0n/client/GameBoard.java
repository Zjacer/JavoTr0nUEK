package com.zjacer.javotr0n.client;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.HashMap;
import javax.swing.JPanel;
import com.zjacer.javotr0n.server.Game;

/**
 * @author Mateusz Zając @ zjacer@gmail.com
 */
public class GameBoard extends JPanel {
    
    private final int WIDTH = Game.getGameBoardWidth();
    private final int HEIGHT = Game.getGameBoardHeight();
    private final int SIZE = 12;
    private static final Color BGCOLOR = Color.BLACK;
    private static final Color BLOCKCOLOR = Color.WHITE;
    private int[][] arena = new int[WIDTH][HEIGHT];
    int myID = 123123;
    Color myColor = Color.RED;
    HashMap<Integer, Color> otherColor = new HashMap<>();

    public GameBoard(Color playerColor) {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(WIDTH*SIZE, HEIGHT*SIZE));
        //myColor = playerColor;
        myColor = Color.RED;
    }
    
    public GameBoard() {
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(WIDTH*SIZE, HEIGHT*SIZE));
        //myColor = playerColor;
        myColor = Color.RED;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {

                // border
                if (arena[x][y] == -1) {
                    g.setColor(BLOCKCOLOR);
                } else if (arena[x][y] == myID) {
                    g.setColor(myColor);
                }  else if (arena[x][y] > 1) {
                    g.setColor(otherColor.get(arena[x][y]));
                } else {                           
                    g.setColor(BGCOLOR);
                }

                g.fillRect(x*SIZE, y*SIZE, SIZE, SIZE);
            }
        }
    }

    void setArena(int[][] arena) {
        this.arena = arena;
    }
}
