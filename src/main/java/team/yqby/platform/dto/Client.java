package team.yqby.platform.dto;

import javax.websocket.*;

/**
 * <p>
 * </p>
 * User：jumping Date： 2016/12/29 0029 Version：1.0
 */
@ClientEndpoint
public class Client {
    @OnOpen
    public void onOpen(Session session) {
        System.out.println("Connected to endpoint: " + session.getBasicRemote());
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println(message);
    }

    @OnError
    public void onError(Throwable t) {
        t.printStackTrace();
    }
}
