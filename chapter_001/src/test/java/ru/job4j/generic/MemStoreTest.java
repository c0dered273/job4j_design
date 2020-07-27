package ru.job4j.generic;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

public class MemStoreTest {

    @Test
    public void whenAddAndFindElement() {
        Store<User> store = new MemStore<>();
        User newUser = new User("id1", "First1", "Second1", "Position1");
        store.add(newUser);
        User findUser = store.findById("id1");
        assertThat(newUser, is(findUser));
    }

    @Test
    public void whenReplaceElement() {
        Store<User> store = new MemStore<>();
        User user1 = new User("id1", "First1", "Second1", "Position1");
        User user2 = new User("id2", "First2", "Second2", "Position2");
        User user3 = new User("id3", "First3", "Second3", "Position3");
        store.add(user1);
        store.add(user2);
        store.replace("id1", user3);
        User replacedUser = store.findById("id3");
        assertThat(replacedUser, is(user3));
    }

    @Test
    public void whenDeleteElement() {
        Store<User> store = new MemStore<>();
        User user1 = new User("id1", "First1", "Second1", "Position1");
        User user2 = new User("id2", "First2", "Second2", "Position2");
        User user3 = new User("id3", "First3", "Second3", "Position3");
        store.add(user1);
        store.add(user2);
        store.add(user3);
        store.delete("id2");
        User result = store.findById("id2");
        assertThat(result, nullValue());
    }

}