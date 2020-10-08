package ru.job4j.isp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MenuFormatterTest {

    @Test
    public void whenRecursivePrint() {
        MenuTree menuTree = new MenuTreeImpl("Root");
        MenuItem menu1 = new MenuItemImpl("RootMenu1", null);
        MenuItem menu2 = new MenuItemImpl("RootMenu2", null);
        MenuItem subMenu1 = new MenuItemImpl("SubMenu1", null);
        MenuItem subMenu2 = new MenuItemImpl("SubMenu2", null);
        MenuItem subMenu3 = new MenuItemImpl("SubMenu3", null);
        menuTree.add(null, menu1);
        menuTree.add(null, menu2);
        menuTree.add(menu1, subMenu1);
        menuTree.add(subMenu1, subMenu2);
        menuTree.add(menu2, subMenu3);
        MenuFormatter formatter = new MenuFormatter(menuTree, "---");
        List<String> result = formatter.getTreeMenu();
        List<String> expect = new ArrayList<>();
        expect.add("RootMenu1");
        expect.add("--- SubMenu1");
        expect.add("------ SubMenu2");
        expect.add("RootMenu2");
        expect.add("--- SubMenu3");
        assertThat(result, is(expect));
    }
}