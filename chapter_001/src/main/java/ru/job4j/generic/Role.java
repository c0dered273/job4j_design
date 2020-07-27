package ru.job4j.generic;

import java.util.Objects;

public class Role extends Base {
    private String roleName;
    private String roleValue;

    public Role(String id, String roleName, String roleValue) {
        super(id);
        this.roleName = roleName;
        this.roleValue = roleValue;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleValue() {
        return roleValue;
    }

    public void setRoleValue(String roleValue) {
        this.roleValue = roleValue;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Role role = (Role) obj;
        return super.getId().equals(role.getId()) &&
                roleName.equals(role.getRoleName()) &&
                roleValue.equals(role.getRoleValue());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.getId());
    }
}
