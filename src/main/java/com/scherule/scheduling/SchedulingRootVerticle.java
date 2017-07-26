package com.scherule.scheduling;

import com.google.inject.Injector;
import com.google.inject.Key;
import com.rabbitmq.client.Channel;
import com.scherule.commons.MicroServiceVerticle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static com.google.inject.name.Names.named;

@SuppressWarnings("unused")
public class SchedulingRootVerticle extends MicroServiceVerticle {

    private static final Logger log = LoggerFactory.getLogger(SchedulingRootVerticle.class);

    private final Channel schedulingChannel;
    private final SchedulingJobConsumer schedulingJobConsumer;

    @SuppressWarnings("unused")
    public SchedulingRootVerticle() {
        Injector injector = SchedulingApplication.context.getInjector();
        schedulingChannel = injector.getInstance(Key.get(Channel.class, named("scheduling.channel")));
        schedulingJobConsumer = injector.getInstance(SchedulingJobConsumer.class);
    }

    @Override
    public void start() {
        try {
            schedulingChannel.queueDeclare("scheduling-queue", true, false, false, null);
            schedulingChannel.basicConsume("scheduling-queue", true, schedulingJobConsumer);
        } catch (IOException e) {
            throw new IllegalStateException("Could not bind scheduling job consumer to scheduling channel", e);
        }
    }

}