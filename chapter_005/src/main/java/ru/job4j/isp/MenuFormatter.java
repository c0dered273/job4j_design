package ru.job4j.isp;

import java.util.ArrayList;
import java.util.List;

public class MenuFormatter {
    private final List<MenuItem> rootMenu;
    private final String tab;
    private int count;


    public MenuFormatter(MenuTree menuTree, String tab) {
        this.rootMenu = menuTree.getRoot().getItems();
        this.tab = tab;
    }

    public List<String> getTreeMenu() {
        return recursiveFormat(rootMenu);
    }

    private List<String> recursiveFormat(List<MenuItem> rootList) {
        List<String> rsl = new ArrayList<>();
        for (MenuItem item : rootList) {
            rsl.add(getTab() + item.getName());
            count++;
            if (item.getItems().isEmpty()) {
                count = 0;
                continue;
            }
            rsl.addAll(recursiveFormat(item.getItems()));
        }
        return rsl;
    }

    private String getTab() {
        String formTab = tab.repeat(count);
        if (count > 0) formTab += " ";
        return formTab;
    }
}
