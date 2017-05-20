package com.kasperskove;

import java.util.ArrayList;

public class User {

    String name;
    ArrayList<Book> books = new ArrayList<>();

    public User(String name) {
        this.name = name;
    }
}