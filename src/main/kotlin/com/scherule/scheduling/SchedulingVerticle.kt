package com.scherule.scheduling;

import io.vertx.core.AbstractVerticle;

class SchedulingVerticle : AbstractVerticle() {

    override fun start() {
        vertx.createHttpServer()
                .requestHandler({
                    it.response().end("Hello Vert.x!")
                })
        .listen(8080);
    }

}