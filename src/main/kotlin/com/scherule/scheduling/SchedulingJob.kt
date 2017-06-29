package com.scherule.scheduling

import com.jayway.jsonpath.DocumentContext
import com.jayway.jsonpath.JsonPath
import com.scherule.scheduling.algorithms.SchedulingProblem
import org.joda.time.Duration
import org.joda.time.Interval

class SchedulingJob(jsonValue: String) : SchedulingProblem {

    val context: DocumentContext = JsonPath.parse(jsonValue);

    override fun getMinParticipants() : Int {
        return context.read( "$.parameters.minParticipants")
    }

    override fun getMinDuration() : Duration {
        return Duration.millis(context.read("$.parameters.minDuration"))
    }

    override fun  getBetween(): Interval {
        val between: String = context.read("$.parameters.between")
        val betweenParts = between.split("-").map { it.toLong() }
        return Interval(betweenParts[0], betweenParts[1])
    }

    fun getParticipants() : Set<String> {
        return HashSet()
    }

    fun getImportanceByParticipants() : Map<String, Int> {
        return getParticipants().associateBy({it}, { getImportanceOfParticipant(it) })
    }

    private fun getImportanceOfParticipant(it: String): Int {
        return context.read("$.participants[?(@.id=$it)]")
    }

}