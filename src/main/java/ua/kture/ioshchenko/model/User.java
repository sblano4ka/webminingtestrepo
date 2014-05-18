package ua.kture.ioshchenko.model;

import javax.persistence.*;


public class User {


    private long id;

    private String email;

    private String password;

    private String dropBoxAccessToken;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDropBoxAccessToken() {
        return dropBoxAccessToken;
    }

    public void setDropBoxAccessToken(String dropBoxAccessToken) {
        this.dropBoxAccessToken = dropBoxAccessToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}
