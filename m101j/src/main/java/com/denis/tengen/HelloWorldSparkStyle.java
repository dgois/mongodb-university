package com.denis.tengen;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import static spark.Spark.*;

/**
 * Created by dgois on 1/16/15.
 */
public class HelloWorldSparkStyle {


    public static void main(String[] args) {
        Spark.get(new Route("/"){
            @Override
            public Object handle(Request request, Response response) {
                return "Hello World From Spark";
            }
        });
    }
}
