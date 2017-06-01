package com.zjacer.javotr0n.server;

import com.esotericsoftware.kryonet.Server;

/**
 * @author Mateusz Zając @ zjacer@gmail.com
 */
public class ServerHandler {

    private GameServer gameServer;
    private Server server; // KryoNet server

    public ServerHandler(GameServer gameServer) {
        this.gameServer = gameServer;
    }
    
    public void start() { }
    
    public void stop() { }
}
