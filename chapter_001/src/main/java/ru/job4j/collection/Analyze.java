package ru.job4j.collection;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Analyze {

    public Info diff(List<User> previous, List<User> current) {
        Info result = new Info();
        result.deleted = previous.size();
        for (User cur : current) {
            boolean isAdded = true;
            for (User prev : previous) {
                if (Objects.equals(cur, prev)) {
                    isAdded = false;
                    result.deleted--;
                }
                if (Objects.equals(cur, prev) && !Objects.equals(cur.name, prev.name)) {
                    result.changed++;
                }
            }
            if (isAdded) ++result.added;
        }
        return result;
    }

    public List<User> addDiff(List<User> previous, List<User> current) {
        return current.stream()
                .filter(e -> previous.stream().noneMatch(o -> Objects.equals(e, o)))
                .collect(Collectors.toList());
    }

    public List<User> deleteDiff(List<User> previous, List<User> current) {
        return previous.stream()
                .filter(e -> current.stream().noneMatch(o -> Objects.equals(e, o)))
                .collect(Collectors.toList());
    }

    public List<User> modDiff(List<User> previous, List<User> current) {
        return current.stream()
                .filter(e -> previous.stream()
                        .anyMatch(o -> Objects.equals(e, o) && !Objects.equals(e.name, o.name)))
                .collect(Collectors.toList());
    }

    public static class User {
        int id;
        String name;

        @Override
        public String toString() {
            return id + " " + name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return id == user.id;
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;

        @Override
        public String toString() {
            return "Info{" +
                    "added=" + added +
                    ", changed=" + changed +
                    ", deleted=" + deleted +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Info info = (Info) o;
            return added == info.added &&
                    changed == info.changed &&
                    deleted == info.deleted;
        }

        @Override
        public int hashCode() {
            return Objects.hash(added, changed, deleted);
        }
    }
}
