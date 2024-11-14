package ru.otus.sockets;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SocketClient {
    private static final Logger logger = LoggerFactory.getLogger(SocketClient.class);
    private static final int PORT = 8090;
    private static final String HOST = "localhost";

    public static void main(String[] args) {
        new SocketClient().go();
    }

    private void go() {
        try {
            try (Socket clientSocket = new Socket(HOST, PORT)) {
                PrintWriter outputStream = new PrintWriter(clientSocket.getOutputStream(), true);

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                for (int idx = 0; idx < 3; idx++) {
                    String msg = String.format("##%d - I Believe", idx);
                    logger.info("sending to server: {}", msg);
                    outputStream.println(msg);

                    String responseMsg = in.readLine();
                    logger.info("server response: {}", responseMsg);
                    Thread.sleep(TimeUnit.SECONDS.toMillis(3));

                    logger.info("");
                }

                logger.info("\nstop communication");
                outputStream.println("stop");
            }
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        } catch (Exception ex) {
            logger.error("error", ex);
        }
    }
}
