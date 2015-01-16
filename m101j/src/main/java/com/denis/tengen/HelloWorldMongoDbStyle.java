package com.denis.tengen;

import com.mongodb.*;

import java.net.UnknownHostException;

/**
 * Created by dgois on 1/16/15.
 */
public class HelloWorldMongoDbStyle {

    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient(new ServerAddress("localhost", 27017));

        DB database = client.getDB("course");
        DBCollection collection = database.getCollection("course");

        DBObject document = collection.findOne();
        System.out.println(document);
    }
}
