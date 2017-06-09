package com.zjacer.javotr0n.client;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.zjacer.javotr0n.SomeRequest;
import com.zjacer.javotr0n.SomeResponse;
import java.io.IOException;
import java.net.InetAddress;

/**
 * @author Mateusz Zając @ zjacer@gmail.com
 */
public class ClientHandler {

    private GameClient gameServer;
    Client client = new Client(54555, 54777);

    public ClientHandler(GameClient gameServer) {
        this.gameServer = gameServer;
    }

    public void start() {

        client.start();
        Kryo kryo = client.getKryo();
        kryo.register(SomeRequest.class);
        kryo.register(SomeResponse.class);
        
        // LAN server discovery
        InetAddress address = client.discoverHost(54777, 5000);
        System.out.println(address);
    
        try {
            client.connect(5000, address, 54555, 54777);
        } catch (IOException ex) {
            System.out.println("No server!");
        }
        
        client.addListener(new Listener() {
            @Override
            public void received (Connection connection, Object object) {
                if (object instanceof SomeResponse) {
                    SomeResponse response = (SomeResponse)object;
                    System.out.println(response.text);
                }
            }
        });
    }

    public void stop() {
        client.close();
        client.stop();
    }                    
}
