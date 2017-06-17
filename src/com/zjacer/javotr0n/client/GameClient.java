package com.zjacer.javotr0n.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * @author Mateusz Zając @ zjacer@gmail.com
 */
public class GameClient extends JFrame {

    private JPanel contentPane;
    private ClientHandler handler;
    private JLabel ipTextArea, labelTCP, labelUDP;
    private JTextArea portUDP, portTCP;
    private JButton connectButton, readyButton, notReadyButton;
    private GameBoard gameBoard;

    public GameClient() {
        
        setTitle("JavoTr0n Client");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 700);
        connectButton = new JButton("CONNECT");
        readyButton = new JButton("READY");
        notReadyButton = new JButton("NOT READY");
        labelTCP = new JLabel("TCP: ");
        labelUDP = new JLabel("UDP: ");
        portTCP = new JTextArea("54555", 1, 4);
        portUDP = new JTextArea("54777", 1, 4);
        ipTextArea = new JLabel("Not connected.");
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);

        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handler = new ClientHandler(GameClient.this, 
                                            Integer.parseInt(portTCP.getText()), 
                                            Integer.parseInt(portUDP.getText())
                );
                handler.start();
            }
        });
  
        readyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchButtonsState();
            }
        });
        
        notReadyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchButtonsState();
            }
        });
        
        notReadyButton.setEnabled(false);
        
        panel.add(connectButton);
        panel.add(readyButton);
        panel.add(notReadyButton);
        panel.add(labelTCP);
        panel.add(portTCP);
        panel.add(labelUDP);
        panel.add(portUDP);
        panel.add(ipTextArea);
        
        gameBoard = new GameBoard();
        contentPane.add(gameBoard, BorderLayout.CENTER);
    }
    
    public void switchButtonsState() {
        readyButton.setEnabled(!readyButton.isEnabled());
        notReadyButton.setEnabled(!notReadyButton.isEnabled());
    }
    
    public JLabel getIpTextArea() {
        return ipTextArea;
    }
    
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
}
