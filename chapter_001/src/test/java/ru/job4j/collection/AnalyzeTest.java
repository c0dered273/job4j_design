package ru.job4j.collection;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AnalyzeTest {
    Analyze.User user1 = new Analyze.User();
    Analyze.User user2 = new Analyze.User();
    Analyze.User user3 = new Analyze.User();
    Analyze.User user35 = new Analyze.User();
    Analyze.User user4 = new Analyze.User();
    Analyze.User user5 = new Analyze.User();
    Analyze.User user6 = new Analyze.User();
    List<Analyze.User> previous = new ArrayList<>();
    List<Analyze.User> current = new ArrayList<>();

    @Before
    public void init() {
        user1.id = 111;
        user1.name = "User1";
        user2.id = 222;
        user2.name = "User2";
        user3.id = 333;
        user3.name = "User3";
        user35.id = 333;
        user35.name = "NewUser3";
        user4.id = 444;
        user4.name = "User4";
        user5.id = 555;
        user5.name = "User5";
        user6.id = 666;
        user6.name = "User6";
        previous.add(user1);
        previous.add(user2);
        previous.add(user3);
        previous.add(user4);
        current.add(user1);
        current.add(user2);
        current.add(user35);
        current.add(user5);
        current.add(user6);
    }

    @Test
    public void addDiff() {
        Analyze analyze = new Analyze();
        List<Analyze.User> expect = new ArrayList<>();
        expect.add(user5);
        expect.add(user6);
        List<Analyze.User> result = analyze.addDiff(previous, current);
        assertThat(result, is(expect));
    }

    @Test
    public void deleteDiff() {
        Analyze analyze = new Analyze();
        List<Analyze.User> expect = new ArrayList<>();
        expect.add(user4);
        List<Analyze.User> result = analyze.deleteDiff(previous, current);
        assertThat(result, is(expect));
    }

    @Test
    public void modDiff() {
        Analyze analyze = new Analyze();
        List<Analyze.User> expect = new ArrayList<>();
        expect.add(user35);
        List<Analyze.User> result = analyze.modDiff(previous, current);
        assertThat(result, is(expect));
    }

    @Test
    public void whenDiff() {
        Analyze analyze = new Analyze();
        Analyze.Info expect = new Analyze.Info();
        expect.added = 2;
        expect.deleted = 1;
        expect.changed = 1;
        assertThat(analyze.diff(previous, current), is(expect));
    }

}