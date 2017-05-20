package com.kasperskove;

public class User {

    private String mName;
    private String mPassword;

    public User(String name, String password) {
        mName = name;
        mPassword = password;
    }

    public User(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }
}
