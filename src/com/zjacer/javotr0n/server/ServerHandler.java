package com.zjacer.javotr0n.server;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
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
        
        server.addListener(new Listener() {
            @Override
            public void received (Connection connection, Object object) {
                if (object instanceof SomeRequest) {
                    SomeRequest request = (SomeRequest)object;
                    System.out.println(request.text);

                    SomeResponse response = new SomeResponse();
                    response.text = "Thanks";
                    connection.sendTCP(response);
                }
            }
        });
    }
    
    public void stop() { 
        server.stop();
        gameServer.switchButtonsState();
        gameServer.printInformation("Server stopped!");
    }
}
