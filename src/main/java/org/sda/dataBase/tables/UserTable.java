package org.sda.dataBase.tables;

import org.sda.User;
import org.sda.authentication.hash.HashFunction;
import org.sda.authentication.hash.SHA256;

import java.util.Objects;

public class UserTable {

    private String username;
    private String email;
    private String country;
    private String hash;

    public UserTable(User user) {
        username = user.getUserName();
        email = user.getEmail();
        HashFunction hashFunction = new SHA256();
        hash = hashFunction.hash(user.getPassword());
    }

    public UserTable(String username, String email, String hash) {
        this.username = username;
        this.email = email;
        this.hash = hash;
    }

    public UserTable(String username, String email, String country, String hash) {
        this.username = username;
        this.email = email;
        this.hash = hash;
        this.country = country;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "UserTable{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTable userTable = (UserTable) o;
        return Objects.equals(username, userTable.username) &&
                Objects.equals(email, userTable.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email);
    }
}
