package com.scherule.scheduling

import com.rabbitmq.client.AMQP
import com.rabbitmq.client.Channel
import com.rabbitmq.client.DefaultConsumer
import com.rabbitmq.client.Envelope
import com.scherule.scheduling.algorithms.SchedulingAlgorithm
import org.slf4j.LoggerFactory
import java.nio.charset.Charset
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton


@Singleton
class SchedulingJobConsumer
@Inject constructor(
        @Named("scheduling.channel") channel: Channel,
        @Named("scheduling.schedulers") val schedulers: Map<String, SchedulingAlgorithm>
) : DefaultConsumer(channel) {

    companion object {
        val log = LoggerFactory.getLogger(SchedulingRootVerticle::class.java)
    }

    override fun handleDelivery(
            consumerTag: String?,
            envelope: Envelope?,
            properties: AMQP.BasicProperties?,
            body: ByteArray?
    ) {
        log.debug("Processing scheduling request ${body!!.toString(Charset.defaultCharset())}")
        val job = SchedulingJob(body.toString(Charset.defaultCharset()))
        val schedulingSolution = schedulers[job.getAlgorithmType()]!!.schedule(job)
        log.debug("Successfully scheduled $schedulingSolution")
    }

}