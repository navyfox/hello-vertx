package org.example.verticle;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.eventbus.Message;

public class MyFirstVerticle extends AbstractVerticle {

    private static final String ADDRESS_MESSAGE = "greeting";

    @Override
    public void start() throws Exception {
        super.start();
        System.out.println("Deploy MyFirstVerticle");

        vertx.eventBus().consumer(ADDRESS_MESSAGE, this::hello1);
        vertx.eventBus().consumer(ADDRESS_MESSAGE, this::hello2);

        for (int i = 0; i < 5; i++) {
            vertx.eventBus().send(ADDRESS_MESSAGE, "hello", this::response);
        }
    }

    private void response(AsyncResult<Message<String>> res) {
        if (res.succeeded()) {
            System.out.println("Answer: " + res.result().body());
        } else {
            System.out.println("Error: " + res.cause());
        }
    }

    private void hello2(final Message<String> data) {
        String message = "Handler 2: " + data.body();
        System.out.println(message);
        data.fail(404, "Not found");
//        data.reply(message);
    }

    private void hello1(final Message<String> data) {
        String message = "Handler 1: " + data.body();
        System.out.println(message);
        data.reply(message);
    }
}
