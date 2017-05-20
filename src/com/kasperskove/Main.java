package com.kasperskove;

import spark.ModelAndView;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        Spark.init();

        Spark.get(
                "/",
                ((request, response) -> {
                    HashMap m = new HashMap();
                    m.put("name", "Kaela");
                    return new ModelAndView(m, "home.html");
                }),
                new MustacheTemplateEngine()
        );
    }
}
