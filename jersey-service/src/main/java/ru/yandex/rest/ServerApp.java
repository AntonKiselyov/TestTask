package ru.yandex.rest;

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
class ServerApp {
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
        // in com.example.rest package
        final ResourceConfig rc = new ResourceConfig().packages("ru.yandex.rest");
        BASE_URI = new ServiceProperties().getProperties();
        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

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
    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        logger.info(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl\nHit enter to stop it...", BASE_URI));
        System.in.read();
        server.stop();
    }
}

