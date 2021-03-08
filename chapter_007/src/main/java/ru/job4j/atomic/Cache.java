package ru.job4j.atomic;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Thread safe cache based on ConcurrentHashMap.
 */
public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    /**
     * Atomic adding entry to cache.
     *
     * @return true if entry id is absent in cache
     */
    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    /**
     * Atomic update with entry version check. If versions are different,
     * throws OptimisticException.
     *
     * @param model Entry data class
     * @return true if update success
     */
    public boolean update(Base model) {
        return memory.computeIfPresent(model.getId(), (id, oldModel) -> {
            if (oldModel.getVersion() != model.getVersion()) {
                throw new OptimisticException("Versions are not equal");
            }
            var newModel = new Base(id, oldModel.getVersion() + 1);
            newModel.setName(model.getName());
            return newModel;
        }) != null;
    }

    /**
     * Atomic delete entry.
     *
     * @param model Entry data class
     * @return true if entry present and deleted
     */
    public boolean delete(Base model) {
        return memory.remove(model.getId()) != null;
    }

    /**
     * Atomic get entry by id.
     *
     * @param id Entry id
     * @return Base if present, null otherwise
     */
    public Base get(int id) {
        return memory.get(id);
    }
}
