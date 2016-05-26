package ru.yandex.expressionservice;

import junit.framework.Assert;
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
        Assert.assertEquals(ServerApp.getServer().isStarted(),true);
    }

    @After
    public void tearDown() throws Exception {
        ServerApp.stopServer();
    }
}