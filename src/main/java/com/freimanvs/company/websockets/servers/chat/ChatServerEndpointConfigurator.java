package com.freimanvs.company.websockets.servers.chat;

import javax.websocket.server.ServerEndpointConfig.Configurator;

public class ChatServerEndpointConfigurator extends Configurator {

    private static ChatServerEndpoint chatServer = new ChatServerEndpoint();

    @Override
    public <T> T getEndpointInstance(Class<T> endpointClass) throws InstantiationException {
        return (T)chatServer;
    }
}