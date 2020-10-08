package ru.job4j.isp;

import java.util.HashMap;
import java.util.Optional;

public class MenuTreeImpl implements MenuTree {
    private final MenuItem root;
    private final HashMap<String, MenuItem> locate = new HashMap<>();

    public MenuTreeImpl(String name) {
        this.root = new MenuItemImpl(name, null);
    }

    @Override
    public boolean add(MenuItem parent, MenuItem child) {
        boolean rsl = false;
        if (child == null) {
            return false;
        } else if (parent == null) {
            rsl = root.getItems().add(child);
        } else if (locate.containsKey(parent.getName())) {
            rsl = parent.getItems().add(child);
        }
        locate.put(child.getName(), child);
        return rsl;
    }

    @Override
    public boolean delete(MenuItem parent, MenuItem child) {
        boolean rsl = false;
        if (child == null) {
            return false;
        } else if (parent == null) {
            rsl = root.getItems().remove(child);
        } else if (locate.containsKey(parent.getName())) {
            rsl = parent.getItems().remove(child);
        }
        locate.remove(child.getName());
        return rsl;
    }

    @Override
    public MenuItem getRoot() {
        return root;
    }

    @Override
    public Optional<MenuItem> findByName(String name) {
        return Optional.of(locate.get(name));
    }
}
