package ru.yandex.expressionservice;

import org.apache.log4j.Logger;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import ru.yandex.core.ServiceProperties;

import java.io.IOException;
import java.net.URI;

/**
 * ServerApp class.
 *
 */
public class ServerApp {
    // Base URI the Grizzly HTTP server will listen on
    private static String BASE_URI;
    private final static Logger logger = Logger.getLogger(ServerApp.class);
    private static HttpServer server = null;
    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() throws IOException {
        // create a resource config that scans for JAX-RS resources and providers
        // in com.example.expressionservice package
        final ResourceConfig rc = new ResourceConfig().packages("ru.yandex.expressionservice");
        BASE_URI = new ServiceProperties().getProperties();
        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }
    public static void runServer() throws IOException {
        server = startServer();
        logger.info(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            logger.info("Stopping server...");
            server.stop();
        }));
        try {
            server.start();
            Thread.currentThread().join();
        } catch (Exception e) {
            logger.error("Error when stopping server...");
        }}

    /*for tests*/
    public static void stopServer() throws IOException {
        server.stop();
    }

    public static HttpServer getServer() {
        return server;
    }

    public static void initServer(HttpServer httpServer) {
        server = httpServer;
    }

    /**
     * ServerApp method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        server = startServer();
        logger.info(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        server.stop();
    }
}

