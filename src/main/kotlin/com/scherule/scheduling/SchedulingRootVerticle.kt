package com.scherule.scheduling;

import com.scherule.commons.MicroServiceVerticle
import io.vertx.rx.java.RxHelper
import io.vertx.rxjava.core.eventbus.MessageConsumer
import org.slf4j.LoggerFactory

class SchedulingRootVerticle : MicroServiceVerticle() {

    companion object {
        val log = LoggerFactory.getLogger(SchedulingRootVerticle::class.qualifiedName)
    }

    private lateinit var messageConsumer: MessageConsumer<String>

    override fun start() {
        super.start()

        messageConsumer = rxVertx.eventBus().consumer("scheduling")

        messageConsumer.toObservable().subscribe {
            log.info("Hello " + it.body())
        }

    }

}