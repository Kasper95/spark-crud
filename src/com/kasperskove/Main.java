package com.kasperskove;

import spark.ModelAndView;
import spark.Session;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.HashMap;

public class Main {

    private static HashMap<String, User> userHashMap = new HashMap<>();

    public static void main(String[] args) {

        Spark.init();

        Spark.get(
                "/",
                ((request, response) -> {
                    Session session = request.session();
                    String userName = session.attribute("userName");
                    User user = userHashMap.get(userName);

                    HashMap m = new HashMap<>();

                    if (user == null) {
                        return new ModelAndView(m, "login.html");
                    } else {
                        m.put("name", user.name);
                        m.put("books", user.books);
                        return new ModelAndView(user, "home.html");
                    }
                }),
                new MustacheTemplateEngine()
        );

        Spark.post(
                "/login",
                ((request, response) -> {
                    String name = request.queryParams("userName");
                    User user = userHashMap.get(name);

                    if (user == null){
                        user = new User(name);
                        userHashMap.put(name, user);
                    }

                    Session session = request.session();
                    session.attribute("userName", name);

                    response.redirect("/");
                    return "";
                })
        );

        Spark.post(
                "/logout",
                ((request, response) -> {
                    Session session = request.session();
                    session.invalidate();
                    response.redirect("/");
                    return "";
                })
        );

        Spark.post(
                "/home",
                ((request, response) -> {

                    Session session = request.session();
                    String name = session.attribute("loginName");
                    User user = userHashMap.get(name);

                    String title = request.queryParams("title");
                    String author = request.queryParams("author");
                    Book newBook = new Book (title, author);
                    user.books.add(newBook);

                    response.redirect("/");
                    return "";
                })
        );
    }
}