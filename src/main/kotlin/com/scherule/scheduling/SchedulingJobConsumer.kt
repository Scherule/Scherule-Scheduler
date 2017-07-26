package com.scherule.scheduling

import com.fasterxml.jackson.databind.ObjectMapper
import com.rabbitmq.client.AMQP
import com.rabbitmq.client.Channel
import com.rabbitmq.client.DefaultConsumer
import com.rabbitmq.client.Envelope
import org.slf4j.LoggerFactory
import java.nio.charset.Charset
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton


@Singleton
class SchedulingJobConsumer
@Inject constructor(
        @Named("scheduling.channel") channel: Channel,
        @Named("scheduling.schedulers") val schedulers: SchedulersLibrary,
        private val objectMapper: ObjectMapper
) : DefaultConsumer(channel) {

    companion object {
        val log = LoggerFactory.getLogger(SchedulingRootVerticle::class.java)!!
    }

    override fun handleDelivery(
            consumerTag: String,
            envelope: Envelope,
            properties: AMQP.BasicProperties,
            body: ByteArray
    ) {
        log.debug("Processing scheduling request ${body.toString(Charset.defaultCharset())}")
        val job = SchedulingJob(body.toString(Charset.defaultCharset()))
        val schedulingSolution = schedulers.getAlgorithm(job.getAlgorithmType()).schedule(job)
        log.debug("Successfully scheduled $schedulingSolution")

        val replyProps = AMQP.BasicProperties.Builder()
                .correlationId(properties.correlationId)
                .build()

        channel.basicPublish("", properties.replyTo, replyProps,
                objectMapper.writeValueAsBytes(schedulingSolution))

        channel.basicAck(envelope.deliveryTag, false)
    }

}