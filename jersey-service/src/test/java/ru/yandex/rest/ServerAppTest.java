package ru.yandex.rest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Admin on 01.05.2016.
 */
public class ServerAppTest {

    private static ServerApp serverApp;

    @Before
    public void setUp() throws Exception {
        serverApp = new ServerApp();
        serverApp.initServer(ServerApp.startServer());
    }

    @Test
    public void testStartServer() throws Exception {
        assert serverApp.getServer().isStarted();
    }

    @After
    public void tearDown() throws Exception {
        serverApp.stopServer();
    }
}