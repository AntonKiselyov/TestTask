package ru.yandex.expressionservice;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Admin on 01.05.2016.
 */
public class ServerAppTest {


    @Before
    public void setUp() throws Exception {
        ServerApp.initServer(ServerApp.startServer());
    }

    @Test
    public void testStartServer() throws Exception {
        assert ServerApp.getServer().isStarted();
    }

    @After
    public void tearDown() throws Exception {
        ServerApp.stopServer();
    }
}