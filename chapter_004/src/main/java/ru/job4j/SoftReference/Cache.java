package ru.job4j.SoftReference;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class Cache {
    private final Map<String, SoftReference<List<String>>> cache;
    private final Function<String, List<String>> source;

    public Cache(Function<String, List<String>> source) {
        this.source = source;
        this.cache = new HashMap<>();
    }

    public List<String> getCachedFile(String name) {
        List<String> rsl;
        SoftReference<List<String>> listRef;
        if ((listRef = cache.get(name)) != null) {
            System.out.println("Get file from cache");
            rsl = listRef.get();
        } else {
            if ((rsl = source.apply(name)) != null) {
                System.out.println("Get file from disk");
                cache.put(name, new SoftReference<>(rsl));
            }
        }
        return rsl;
    }
}
