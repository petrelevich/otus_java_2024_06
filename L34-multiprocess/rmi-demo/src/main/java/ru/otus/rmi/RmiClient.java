package ru.otus.rmi;

import java.rmi.Naming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RmiClient {
    private static final Logger logger = LoggerFactory.getLogger(RmiClient.class);

    public static void main(String[] args) throws Exception {

        EchoInterface echoInterface =
                (EchoInterface) Naming.lookup(String.format("//localhost:%d/EchoServer", RmiServer.REGISTRY_PORT));
        var dataFromServer = echoInterface.echo("hello");
        logger.info("response from the server: {}", dataFromServer);
    }
}
