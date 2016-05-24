package ru.yandex.core;

import org.junit.Test;

/**
 * Created by Admin on 24.05.2016.
 */
public class ClientPropertiesTest {
    private static String BASE_URL = "http://localhost:8080/expressions/";
    @Test
    public void testGetProperties() throws Exception {
        assert new ClientProperties().getProperties().equals(BASE_URL);
    }
}