package com.zjacer.javotr0n.server;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

/**
 * @author Mateusz Zając @ zjacer@gmail.com
 */
public class GameServer extends JFrame {
    
    private JPanel contentPane;
    private JButton buttonStart, buttonStop;
    private JLabel labelTCP, labelUDP;
    private JTextArea portUDP, portTCP;
    private JTextPane textArea;
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
        labelTCP = new JLabel("TCP: ");
        labelUDP = new JLabel("UDP: ");
        portTCP = new JTextArea("54555", 1, 4);
        portUDP = new JTextArea("54777", 1, 4);
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);
       
        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handler = new ServerHandler(GameServer.this, 
                                            Integer.parseInt(portTCP.getText()), 
                                            Integer.parseInt(portUDP.getText())
                );
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
        panel.add(labelTCP);
        panel.add(portTCP);
        panel.add(labelUDP);
        panel.add(portUDP);


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
