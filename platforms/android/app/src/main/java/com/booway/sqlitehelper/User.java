package com.booway.sqlitehelper;

public class User {
    private int id;
    private String username;
    private String birthday;

    public User(int id, String username, String birthday) {
        this.id = id;
        this.username = username;
        this.birthday = birthday;
    }

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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
