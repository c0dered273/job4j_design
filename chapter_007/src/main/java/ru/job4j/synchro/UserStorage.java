package ru.job4j.synchro;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Map;
import java.util.function.Predicate;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final Map<Integer, User> users;

    public UserStorage(Map<Integer, User> users) {
        this.users = users;
    }

    public synchronized boolean add(User user) {
        return addWithCondition(u -> !users.containsKey(u.getId()), user);
    }

    public synchronized boolean update(User user) {
        return addWithCondition(u -> users.containsKey(u.getId()), user);
    }

    public synchronized boolean delete(User user) {
        return users.remove(user.getId()) != null;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        var rsl = false;
        var fromUser = users.get(fromId);
        var toUser = users.get(toId);
        if (fromUser != null && toUser != null) {
            var fromAmount = fromUser.getAmount();
            var toAmount = toUser.getAmount();
            fromUser.setAmount(fromAmount - amount);
            toUser.setAmount(toAmount + amount);
            rsl = true;
        }
        return rsl;
    }

    private synchronized boolean addWithCondition(Predicate<User> predicate, User user){
        var rsl = false;
        if (predicate.test(user)) {
            users.put(user.getId(), user);
            rsl = true;
        }
        return rsl;
    }
}
