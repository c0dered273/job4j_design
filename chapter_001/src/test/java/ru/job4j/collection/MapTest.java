package ru.job4j.collection;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class MapTest {
    @Test
    public void mapTesting() {
        User user1 = new User("Name1", 2, new GregorianCalendar(1888, Calendar.DECEMBER,1));
        User user2 = new User("Name1", 2, new GregorianCalendar(1888, Calendar.DECEMBER,1));
        Map<User, Object> map = new HashMap<>();
        map.put(user1, new Object());
        map.put(user2, new Object());
        System.out.println(map);
    }
}
