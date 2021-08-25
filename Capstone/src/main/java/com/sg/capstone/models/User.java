package com.sg.capstone.models;

import java.util.Objects;
import java.util.Set;

/**
 *
 * @author kylerudy
 */
public class User {
  private int id;

    private String username;

    private String password;   

    private boolean enabled;

    private Set<Role> roles;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.username);
        hash = 89 * hash + Objects.hashCode(this.password);
        hash = 89 * hash + (this.enabled ? 1 : 0);
        hash = 89 * hash + Objects.hashCode(this.roles);
        return hash;
    }

    @Override
    public boolean equals(Object other) {

        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (this.getClass() != other.getClass()) {
            return false;
        }

        final User that = (User) other;

        if (this.getId() != that.getId()) {
            return false;
        }
        if (!this.getPassword().equals(that.getPassword())) {
            return false;
        }
        if (!this.getUsername().equals(that.getUsername())) {
            return false;
        }

        return Objects.equals(this.getRoles(), that.getRoles());

    }  
}
