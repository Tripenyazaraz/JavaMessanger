package client;

import org.glassfish.tyrus.client.ClientManager;

import javax.websocket.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Logger;

@ClientEndpoint
public class WebSocketClient {
    static Logger logger = Logger.getLogger(WebSocketClient.class.getName());
    private static CountDownLatch latch;

    @OnOpen
    public void onOpen (Session session) {
        logger.info("[CLIENT]: Connection established..... \n[CLIENT]: Session ID: " + session.getId() );
        try {
            session.getBasicRemote().sendText("Server is ready.....");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @OnMessage
    public String onMessage (String message, Session session) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("[SERVER RESPONSE]: " + message);
        return scanner.nextLine();
    }

    @OnClose
    public void onClose (Session session, CloseReason closeReason) {
        logger.info("[CLIENT]: Session " + session.getId() + " close, because " + closeReason);
        latch.countDown();
    }

    @OnError
    public void onError (Session session, Throwable err) {
        logger.severe("[CLIENT]: Error!, Session ID: " + session.getId() + ", " + err.getMessage());
    }

    public static void main(String[] args) {
        latch = new CountDownLatch(1);
        ClientManager clientManager = ClientManager.createClient();
        URI uri = null;
        try {
            Properties prop = new Properties();
            InputStream input = new FileInputStream("application.properties");
            prop.load(input);
            String port = prop.getProperty("server.port");

            uri = new URI(String.format("ws://localhost:%s/java/demoApp", port));
            //  I can use session to send messages
            Session session = clientManager.connectToServer(WebSocketClient.class, uri);
            latch.await();
        } catch (URISyntaxException | DeploymentException | InterruptedException | IOException e) {
            e.printStackTrace();
        }
    }
}