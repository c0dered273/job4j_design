package ru.job4j.generic;

import java.util.Objects;

public class User extends Base {
    private String firstName;
    private String lastName;
    private String position;

    public User(String id, String firstName, String lastName, String position) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User user = (User) obj;
        return super.getId().equals(user.getId()) &&
                firstName.equals(user.getFirstName()) &&
                lastName.equals(user.getLastName()) &&
                position.equals(user.getPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.getId());
    }
}
