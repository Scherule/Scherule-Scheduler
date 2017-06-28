package com.scherule.scheduling

import com.jayway.jsonpath.DocumentContext
import com.jayway.jsonpath.JsonPath
import org.joda.time.Duration

class SchedulingJob(jsonValue: String) {

    val context: DocumentContext = JsonPath.parse(jsonValue);

    fun getMinParticipants() : Int {
        return context.read( "$.parameters.minParticipants")
    }

    fun getMinDuration() : Duration {
        return Duration.standardDays(3)
    }

}