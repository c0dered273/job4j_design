package ru.job4j.tdd;

import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class GeneratorTest {
    @Ignore
    @Test
    public void whenProduce() {
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String> keys = Map.of(
                "name", "Vasya",
                "subject", "you");
        Generator generator = new GeneratorImpl();
        String result = generator.produce(template, keys);
        String expect = "I am a Vasya, Who are you?";
        assertThat(result, is(expect));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenProduceWithException() {
        String template = "I am a ${name}, Who are ${subject}. What is you ${age}?";
        Map<String, String> keys = Map.of(
                "name", "Vasya",
                "subject", "you");
        Generator generator = new GeneratorImpl();
        String result = generator.produce(template, keys);
        String expect = "I am a Vasya, Who are you?";
        assertThat(result, is(expect));
    }
}