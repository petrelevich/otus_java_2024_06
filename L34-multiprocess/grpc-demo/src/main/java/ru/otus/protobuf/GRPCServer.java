package ru.otus.protobuf;

import io.grpc.ServerBuilder;
import java.io.IOException;
import ru.otus.protobuf.service.RealDBServiceImpl;
import ru.otus.protobuf.service.RemoteDBServiceImpl;

@SuppressWarnings({"squid:S106"})
public class GRPCServer {

    public static final int SERVER_PORT = 8190;

    public static void main(String[] args) throws IOException, InterruptedException {

        var dbService = new RealDBServiceImpl();
        var remoteDBService = new RemoteDBServiceImpl(dbService);

        var server =
                ServerBuilder.forPort(SERVER_PORT).addService(remoteDBService).build();
        server.start();
        System.out.println("server waiting for client connections...");
        server.awaitTermination();
    }
}
