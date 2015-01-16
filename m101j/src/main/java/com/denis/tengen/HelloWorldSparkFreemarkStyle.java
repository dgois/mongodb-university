package com.denis.tengen;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dgois on 1/16/15.
 */
public class HelloWorldSparkFreemarkStyle {

    public static void main(String[] args) {

        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorldFreemarkerStyle.class, "/");

        Spark.get(new Route("/") {
            @Override
            public Object handle(Request request, Response response) {
                Template helloTemplate = null;

                StringWriter writer = new StringWriter();
                Map<String, Object> helloMap = new HashMap<String, Object>();
                helloMap.put("name", "Freemarker");

                try {
                    helloTemplate = configuration.getTemplate("hello.ftl");
                    helloTemplate.process(helloMap, writer);
                } catch (Exception e) {
                    halt(500);
                    e.printStackTrace();
                }

                return  writer;
            }
        });
    }
}
