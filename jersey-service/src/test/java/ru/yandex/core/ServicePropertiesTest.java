package ru.yandex.core;

import org.junit.Test;

/**
 * Created by Admin on 24.05.2016.
 */
public class ServicePropertiesTest {

    @Test
    public void testGetProperties() throws Exception {
        String BASE_URL = "http://localhost:8080/expressions/";
        assert (new ServiceProperties().getProperties()).equals(BASE_URL);
    }
}