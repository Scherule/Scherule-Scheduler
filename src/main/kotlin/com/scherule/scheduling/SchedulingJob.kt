package com.scherule.scheduling

import com.jayway.jsonpath.DocumentContext
import com.jayway.jsonpath.JsonPath
import com.scherule.scheduling.algorithms.Participant
import com.scherule.scheduling.algorithms.SchedulingProblem
import org.joda.time.Duration
import org.joda.time.Interval

class SchedulingJob(jsonValue: String) : SchedulingProblem {

    override fun getParticipants(): Set<Participant> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val context: DocumentContext = JsonPath.parse(jsonValue);

    override fun getMinParticipants(): Int {
        return context.read("$.parameters.minParticipants")
    }

    override fun getMinDuration(): Duration {
        return Duration.millis(context.read("$.parameters.minDuration"))
    }

    override fun getBetween(): Interval {
        val between: String = context.read("$.parameters.between")
        val betweenParts = between.split("-").map { it.toLong() }
        return Interval(betweenParts[0], betweenParts[1])
    }

}