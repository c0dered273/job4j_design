package ru.job4j.isp;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MenuTreeImplTest {

    @Test
    public void whenAddRoot() {
        MenuTree menuTree = new MenuTreeImpl("Root");
        MenuItem menu1 = new MenuItemImpl("RootMenu1", null);
        MenuItem menu2 = new MenuItemImpl("RootMenu2", null);
        menuTree.add(null, menu1);
        menuTree.add(null, menu2);
        assertThat(menuTree.getRoot().getItems(), is(List.of(menu1, menu2)));
    }

    @Test
    public void whenAddItem() {
        MenuTree menuTree = new MenuTreeImpl("Root");
        MenuItem menu1 = new MenuItemImpl("RootMenu1", null);
        MenuItem subMenu = new MenuItemImpl("SubMenu", null);
        menuTree.add(null, menu1);
        menuTree.add(menu1, subMenu);
        List<MenuItem> result = menu1.getItems();
        assertThat(result, is(List.of(subMenu)));
    }

    @Test
    public void whenDelRoot() {
        MenuTree menuTree = new MenuTreeImpl("Root");
        MenuItem menu1 = new MenuItemImpl("RootMenu1", null);
        MenuItem menu2 = new MenuItemImpl("RootMenu2", null);
        menuTree.add(null, menu1);
        menuTree.add(null, menu2);
        menuTree.delete(null, menu1);
        assertThat(menuTree.getRoot().getItems(), is(List.of(menu2)));
    }

    @Test
    public void whenDelItem() {
        MenuTree menuTree = new MenuTreeImpl("Root");
        MenuItem menu1 = new MenuItemImpl("RootMenu1", null);
        MenuItem subMenu = new MenuItemImpl("SubMenu", null);
        MenuItem subMenu2 = new MenuItemImpl("SubMenu2", null);
        menuTree.add(null, menu1);
        menuTree.add(menu1, subMenu);
        menuTree.add(menu1, subMenu2);
        menuTree.delete(menu1, subMenu2);
        List<MenuItem> result = menu1.getItems();
        assertThat(result, is(List.of(subMenu)));
    }

    @Test
    public void whenFindByName() {
        MenuTree menuTree = new MenuTreeImpl("Root");
        MenuItem menu1 = new MenuItemImpl("RootMenu1", null);
        MenuItem subMenu = new MenuItemImpl("SubMenu", null);
        menuTree.add(null, menu1);
        menuTree.add(menu1, subMenu);
        MenuItem result = menuTree.findByName(subMenu.getName()).get();
        assertThat(result, is(subMenu));
    }

    @Test
    public void whenExecAction() {
        Runnable action = () -> {
            System.out.println("Executing action");
        };
        PrintStream oldOut = System.out;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream newOut = new PrintStream(outputStream);
        System.setOut(newOut);
        MenuTree menuTree = new MenuTreeImpl("Root");
        MenuItem menu1 = new MenuItemImpl("RootMenu1", null);
        MenuItem subMenu = new MenuItemImpl("SubMenu", action);
        menuTree.add(null, menu1);
        menuTree.add(menu1, subMenu);
        MenuItem item = menuTree.findByName("SubMenu").get();
        item.run();
        String expect = "Executing action" + System.lineSeparator();
        String result = outputStream.toString();
        assertThat(result, is(expect));
        System.setOut(oldOut);
    }
}