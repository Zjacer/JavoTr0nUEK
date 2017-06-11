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
    private int portTCP, portUDP;
    
    public ServerHandler(GameServer gameServer, int portTCP, int portUDP) {
        this.gameServer = gameServer;
        
        this.portTCP = portTCP;
        this.portUDP = portUDP;
    }
    
    public void start() { 
        
        server = new Server();
        server.start();
        
        try {
            //System.out.println(portTCP + " " + portUDP);
            server.bind(portTCP, portUDP);
            gameServer.switchButtonsState();
            gameServer.printInformation("Server started on TCP port: " 
                                        + portTCP + " UDP port: " + portUDP);

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

                    respond("Welcomeeee! :)", connection);
                }
            }
        });
    }
    
    private void respond(String text, Connection connection) {
        SomeResponse response = new SomeResponse();
        response.text = text;
        connection.sendTCP(response);
    }
    
    public void stop() { 
        server.stop();
        gameServer.switchButtonsState();
        gameServer.printInformation("Server stopped!");
    }
}
