package ru.job4j.io;

import org.junit.Test;

import java.security.InvalidParameterException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ConfigTest {
    @Test
    public void whenPairWithoutComment() {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("hibernate.connection.url"),
                is("jdbc:postgresql://127.0.0.1:5432/trackstudio")
        );
    }

    @Test(expected = InvalidParameterException.class)
    public void whenMissingProperty() {
        String path = "./data/app.properties";
        Config config = new Config(path);
        config.load();
        config.value("name");
    }
}