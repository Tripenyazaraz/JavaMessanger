package server;

import org.glassfish.tyrus.server.Server;

import javax.websocket.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;
import java.util.logging.Logger;


public class WebSocketServer {
    static Logger logger = Logger.getLogger(WebSocketServer.class.getName());

    public static void main(String[] args) {
        try {
            Properties prop = new Properties();
            InputStream input = new FileInputStream("application.properties");
            prop.load(input);
            int port = Integer.parseInt(prop.getProperty("server.port"));

            Server server = new Server("localhost", port, "", WebSocketServerEndpoint.class);

            server.start();
            logger.info("[SERVER]: Server is up and running on port " + port);
            logger.info("[SERVER]: Press 't' to terminate server...");
            Scanner scanner = new Scanner(System.in);
            String inp = scanner.nextLine();
            scanner.close();
            if (inp.equalsIgnoreCase("t")) {
                logger.info("[SERVER]: Server successfully terminated");
                server.stop();
            } else {
                logger.severe("[SERVER]: Invalid input!");
            }
        } catch (DeploymentException | IOException e) {
            e.printStackTrace();
        }
    }
}