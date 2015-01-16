package com.denis.tengen;

import com.mongodb.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.io.StringWriter;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dgois on 1/16/15.
 */
public class HelloWorldMongoDbSparkFreemarkStyle {

    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));

        DB database = client.getDB("course");
        final DBCollection collection = database.getCollection("course");


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

                    DBObject document = collection.findOne();

                    helloTemplate = configuration.getTemplate("hello.ftl");
                    helloTemplate.process(document, writer);
                } catch (Exception e) {
                    halt(500);
                    e.printStackTrace();
                }

                return writer;
            }
        });
    }
}
