package ru.yandex.core;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Admin on 24.05.2016.
 */
public class ClientPropertiesTest {
    @Test
    public void testGetProperties() throws Exception {
        String BASE_URL = "http://localhost:8080/expressions/";
        Assert.assertEquals(new ClientProperties().getProperties(),BASE_URL);
    }
}