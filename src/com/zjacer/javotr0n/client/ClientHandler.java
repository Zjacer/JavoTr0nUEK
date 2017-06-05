package com.zjacer.javotr0n.client;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.zjacer.javotr0n.SomeRequest;
import com.zjacer.javotr0n.SomeResponse;
import java.io.IOException;

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
        
        try {
            client.connect(5000, "127.0.0.1", 54555, 54777);
        } catch (IOException ex) {
            // logic here
        }
    }

    public void stop() {
        client.close();
        client.stop();
    }                    
}
