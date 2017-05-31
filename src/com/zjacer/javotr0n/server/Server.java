package com.zjacer.javotr0n.server;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author Mateusz Zając @ zjacer@gmail.com
 */
public class Server extends JFrame {
    
    private JPanel contentPane;
    public JButton buttonStart;
    public JButton buttonStop;
    public JTextArea textArea;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Server window = new Server();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Server() {
        
        setTitle("JavoTr0n Server");
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
            public void actionPerformed(ActionEvent e) {
                textArea.setText(textArea.getText() + "\nServer is running.");
                buttonStart.setEnabled(false);
                buttonStop.setEnabled(true);
            }
        });
  
        buttonStop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                textArea.setText(textArea.getText() + "\nServer is offline.");
                buttonStart.setEnabled(true);
                buttonStop.setEnabled(false);
            }
        });
        
        buttonStop.setEnabled(false);
                
        panel.add(buttonStart);
        panel.add(buttonStop);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);
        textArea = new JTextArea();
        textArea.setText("Server offline.");
        textArea.setEnabled(false);
        textArea.setEditable(false);
        scrollPane.setViewportView(textArea);
    }
}
