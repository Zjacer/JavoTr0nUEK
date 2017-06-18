package com.zjacer.javotr0n.client;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.zjacer.javotr0n.SomeRequest;
import com.zjacer.javotr0n.SomeResponse;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.net.InetAddress;
import javax.swing.JOptionPane;

/**
 * @author Mateusz Zając @ zjacer@gmail.com
 */
public class ClientHandler implements KeyListener {

    private GameClient gameClient;
    private Client client;
    private int portTCP, portUDP, id = 0;
    private boolean inGame = false;
    
    public ClientHandler(GameClient gameClient, int portTCP, int portUDP) {
        this.client = new Client(portTCP, portUDP);
        this.gameClient = gameClient;
        this.portTCP = portTCP;
        this.portUDP = portUDP;
    }
    
    public void start() {

        client.start();
        Kryo kryo = client.getKryo();
        kryo.register(SomeRequest.class);
        kryo.register(SomeResponse.class);

        InetAddress address = client.discoverHost(portUDP, 5000);
        
        if(address != null) {
            try {
                System.out.println(portTCP + " " + portUDP);
                gameClient.getIpTextArea().setText("Server IP address: "+ address.toString());
                client.connect(5000, address, portTCP, portUDP);
                gameClient.changeColorAndReadyButtonStatus();
                request("Player connected!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(gameClient,
                    "Couldn't connect to server on IP address: " + address.toString() +
                    "\nCheck if ports are opened!",
                    "Connection Error!",
                    JOptionPane.ERROR_MESSAGE);
                stop();
            }
        } else {
            JOptionPane.showMessageDialog(gameClient,
                "JavoTr0n Game Server not found on LAN Network!",
                "Server not found!",
                JOptionPane.ERROR_MESSAGE);
            stop();
        }
          
        client.addListener(new Listener() {
            @Override
            public void received (Connection connection, Object object) {
                if (object instanceof SomeResponse) {
                    SomeResponse response = (SomeResponse)object;
                    handleResponse(connection, response.text);
                }
            }
        });
        
        gameClient.addKeyListener(this);
    }

    private void request(String text) {
        SomeRequest request = new SomeRequest();
        request.text = text;
        client.sendTCP(request);
    }
    
    public void stop() {
        client.close();
        client.stop();
    }
 
    private void handleResponse(Connection connection, String text) {

    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("KEY PRESSED!!!!!!!!!!!!!");
        int key = e.getKeyCode();
        int direction = -1;
 
        switch (key) {
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                direction = 0;
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                direction = 1;
                break;
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                direction = 2;
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                direction = 3;
                break;
            default:
                break;
        }
        
        if (direction != -1 && inGame) {
            request("direction;" + String.valueOf(id) + ";" + String.valueOf(direction));
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
