package com.zjacer.javotr0n.server;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Server;
import com.zjacer.javotr0n.SomeRequest;
import com.zjacer.javotr0n.SomeResponse;

/**
 * @author Mateusz Zając @ zjacer@gmail.com
 */
public class ServerHandler {

    private GameServer gameServer;
    private Server server; // KryoNet server

    public ServerHandler(GameServer gameServer) {
        this.gameServer = gameServer;
    }
    
    public void start() { 
        
        server = new Server();
        server.start();
        
        try {
            server.bind(54555, 54777);
            gameServer.switchButtonsState();
            gameServer.printInformation("Server started!");

            Kryo kryo = server.getKryo();
            kryo.register(SomeRequest.class);
            kryo.register(SomeResponse.class);
            
        } catch (Exception e) {
            // DO SOMETHING HERE LATER
        }
    }
    
    public void stop() { 
        server.stop();
        gameServer.switchButtonsState();
        gameServer.printInformation("Server stopped!");
    }
}
