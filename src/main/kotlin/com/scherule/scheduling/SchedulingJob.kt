package com.scherule.scheduling

import com.jayway.jsonpath.DocumentContext
import com.jayway.jsonpath.JsonPath
import org.joda.time.Duration
import org.joda.time.Interval

class SchedulingJob(jsonValue: String) {

    val context: DocumentContext = JsonPath.parse(jsonValue);

    fun getMinParticipants() : Int {
        return context.read( "$.parameters.minParticipants")
    }

    fun getMinDuration() : Duration {
        return Duration.millis(context.read("$.parameters.minDuration"))
    }

    fun  getBetween(): Interval {
        val between: String = context.read("$.parameters.between")
        val betweenParts = between.split("-").map { it.toLong() }
        return Interval(betweenParts[0], betweenParts[1])
    }

}