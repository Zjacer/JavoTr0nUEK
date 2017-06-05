package com.zjacer.javotr0n.server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

/**
 * @author Mateusz Zając @ zjacer@gmail.com
 */
public class GameServer extends JFrame {
    
    private JPanel contentPane;
    public JButton buttonStart;
    public JButton buttonStop;
    public JTextPane textArea;
    private ServerHandler handler;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    GameServer window = new GameServer();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public GameServer() {
        
        setTitle("JavoTr0n Server");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 400);
        buttonStart = new JButton("Start");
        buttonStop = new JButton("Stop");
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);
       
        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handler = new ServerHandler(GameServer.this);
                handler.start();
            }
        });
  
        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handler.stop();
            }
        });
        
        buttonStop.setEnabled(false);
                
        panel.add(buttonStart);
        panel.add(buttonStop);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        
        textArea = new JTextPane();
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);
        textArea.setText("Welcome in JavoTr0n server client!");
        textArea.setEnabled(false);
        textArea.setEditable(false);
        scrollPane.setViewportView(textArea);
    }
    
    void switchButtonsState() {
        buttonStart.setEnabled(!buttonStart.isEnabled());
        buttonStop.setEnabled(!buttonStop.isEnabled());
    }

    void printInformation(String text) {
        textArea.setText(textArea.getText() + "\n" + text);
    }
}
