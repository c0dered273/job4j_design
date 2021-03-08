package ru.job4j.atomic;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class CacheTest {
    @Test
    public void whenAddThenDeleteEntry() {
        Cache cache = new Cache();
        Base model1 = new Base(1, 1);
        model1.setName("model1");
        Base model2 = new Base(2, 1);
        model2.setName("model2");
        List<Base> expect = new ArrayList<>();
        expect.add(model2);
        cache.add(model1);
        cache.add(model2);
        cache.delete(model1);
        List<Base> result = new ArrayList<>();
        result.add(cache.get(model2.getId()));
        assertThat(result, is(expect));
    }

    @Test
    public void whenAddThenUpdateEntry() {
        Cache cache = new Cache();
        Base model1 = new Base(1, 1);
        model1.setName("model1");
        cache.add(model1);
        model1.setName("newModel1");
        cache.update(model1);
        Base updatedModel = cache.get(model1.getId());
        assertThat(updatedModel.getVersion(), is(2));
    }

    @Test (expected = OptimisticException.class)
    public void whenUpdateNoneExistEntry() {
        Cache cache = new Cache();
        Base model1 = new Base(1, 1);
        model1.setName("model1 v1");
        Base model2 = new Base(1, 2);
        model2.setName("model1 v2");
        cache.add(model1);
        cache.update(model2);
    }
}