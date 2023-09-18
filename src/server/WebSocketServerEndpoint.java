package server;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.logging.Logger;

@ServerEndpoint(value = "/demoApp")
public class WebSocketServerEndpoint {
    static Logger logger = Logger.getLogger(WebSocketServerEndpoint.class.getName());

    @OnOpen
    public void onOpen (Session session) {
        logger.info("[SERVER]: Handshake successful, connected, session ID: " + session.getId());
    }

    @OnMessage
    public String onMessage (String message, Session session) {
        if (message.equalsIgnoreCase("terminate")) {
            try {
                session.close(new CloseReason(CloseReason.CloseCodes.NORMAL_CLOSURE, "Session successfully closed"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return message + message;
    }

    @OnClose
    public void onClose (Session session, CloseReason closeReason) {
        logger.info("[SERVER]: Session " + session.getId() + " closed, because " + closeReason);
    }
}
