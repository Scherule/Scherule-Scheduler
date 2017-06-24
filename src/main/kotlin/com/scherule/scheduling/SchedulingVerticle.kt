package com.scherule.scheduling;

import com.scherule.commons.MicroServiceVerticle
import io.vertx.core.json.JsonObject
import io.vertx.servicediscovery.ServiceDiscovery
import io.vertx.servicediscovery.ServiceDiscoveryOptions

class SchedulingVerticle : MicroServiceVerticle() {

    override fun start() {
        val discovery = ServiceDiscovery.create(vertx,
                ServiceDiscoveryOptions()
                        .setAnnounceAddress("scherule-announcing")
                        .setName("scheduling"))

        val scheduleBusName = config().getString("bus.schedule", "schedule")
        vertx.eventBus().consumer<JsonObject>(scheduleBusName)
                .handler { message ->
                    val quote = message.body()
                    System.out.println("Schedule!");
                }

//        val record = EventBusService.createRecord("bus.schedule", scheduleBusName, contentClass)

        vertx.createHttpServer()
                .requestHandler({
                    it.response().end("Hello Vert.x!")
                })
        .listen(config().getInteger("http.port"), {
            if (it.succeeded()) {
                System.out.println("Server started");
            } else {
                System.out.println("Cannot start the server: " + it.cause());
            }
        })

        discovery.close()
    }

}