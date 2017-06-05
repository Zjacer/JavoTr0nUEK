package com.zjacer.javotr0n.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Mateusz Zając @ zjacer@gmail.com
 */
public class GameClient extends JFrame {

    private JPanel contentPane;
    public ClientHandler handler;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    GameClient window = new GameClient();
                    window.setVisible(true);
                } catch (Exception e) {
                    // logic here
                }
            }
        });
    }

    public GameClient() {
        
        setTitle("JavoTr0n Client");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 400);
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);
        
        handler = new ClientHandler(this);
        handler.start();
    }
}
