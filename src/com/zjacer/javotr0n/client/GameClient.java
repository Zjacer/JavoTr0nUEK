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
    public ClientHandler handler;
    private JButton readyButton;
    private JButton notReadyButton;
    private JLabel ipTextArea;
    
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
        setBounds(100, 100, 600, 600);
        readyButton = new JButton("READY");
        notReadyButton = new JButton("NOT READY");
        ipTextArea = new JLabel("Server not found.");
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);
        
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.NORTH);
        
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
        
        panel.add(readyButton);
        panel.add(notReadyButton);
        panel.add(ipTextArea);
        
        handler = new ClientHandler(this);
        handler.start();
    }
    
    public void switchButtonsState() {
        readyButton.setEnabled(!readyButton.isEnabled());
        notReadyButton.setEnabled(!notReadyButton.isEnabled());
    }
    
    public JLabel getIpTextArea() {
        return ipTextArea;
    }
}
