package org.example;

import io.vertx.core.Vertx;
import org.example.verticle.MyFirstVerticle;

public class Starter {

    public static void main(String arg[]) {
        System.out.println("Start app");
        Vertx.vertx().deployVerticle(new MyFirstVerticle());
    }
}
