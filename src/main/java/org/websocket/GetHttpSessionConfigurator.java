package org.websocket;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
/**
 * @author zsw
 * @date 2019/4/21 19:45
 */
public class GetHttpSessionConfigurator extends ServerEndpointConfig.Configurator {

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
       HttpSession httpSession = (HttpSession) request.getHttpSession();
       sec.getUserProperties().put(HttpSession.class.getName(),httpSession);
    }
}
