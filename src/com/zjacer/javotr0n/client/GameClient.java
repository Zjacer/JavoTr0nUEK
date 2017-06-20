package com.zjacer.javotr0n.client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
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
    private JButton connectButton, readyButton, notReadyButton, colorChooserButton;
    private GameBoard gameBoard;
    private Color playerColor = null;
    
    public GameClient() {
        
        setTitle("JavoTr0n Client");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 0, 0);
        connectButton = new JButton("CONNECT");
        colorChooserButton = new JButton("PICK LIGHTCYCLE COLOR");
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
        gameBoard = new GameBoard();
        JPanel topPanel = new JPanel();
        JPanel centerPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

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
                handler.informServerAboutReadyStatus(true);
                switchButtonsState();
            }
        });
        
        notReadyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handler.informServerAboutReadyStatus(false);
                switchButtonsState();
            }
        });
        
        colorChooserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playerColor = JColorChooser.showDialog(null, "Pick your Lightcycle color", null);
                
                if(playerColor != null) {
                    gameBoard.setPlayerColor(playerColor);
                    connectButton.setEnabled(true);
                }
            }
        });
        
        connectButton.setEnabled(false);
        readyButton.setEnabled(false);
        notReadyButton.setEnabled(false);
       
        // it wont interrupt keylistener anymore
        colorChooserButton.setFocusable(false);
        connectButton.setFocusable(false);
        readyButton.setFocusable(false);
        notReadyButton.setFocusable(false);
        portTCP.setFocusable(false);
        portUDP.setFocusable(false);
        
        topPanel.add(colorChooserButton);
        topPanel.add(connectButton);
        topPanel.add(readyButton);
        topPanel.add(notReadyButton);
        bottomPanel.add(labelTCP);
        bottomPanel.add(portTCP);
        bottomPanel.add(labelUDP);
        bottomPanel.add(portUDP);
        bottomPanel.add(ipTextArea);
        
        centerPanel.add(gameBoard);

        contentPane.add(topPanel, BorderLayout.NORTH);
        contentPane.add(centerPanel, BorderLayout.CENTER);
        contentPane.add(bottomPanel, BorderLayout.PAGE_END);
        pack();
    }
    
    public GameBoard getGameBoard() {
        return gameBoard;
    }
    
    public void switchButtonsState() {
        readyButton.setEnabled(!readyButton.isEnabled());
        notReadyButton.setEnabled(!notReadyButton.isEnabled());
    }
    
    public JLabel getIpTextArea() {
        return ipTextArea;
    }

    public void changeColorAndReadyButtonStatus() {
        //connectButton.setEnabled(false); // TODO: change method name
        colorChooserButton.setEnabled(false);
        readyButton.setEnabled(true);
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
