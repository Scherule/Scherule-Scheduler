package com.scherule.scheduling

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

internal class SchedulingJobTest {

    val jobContent = "{\"id\":{\"id\":\"933\"},\"parameters\":{\"between\":\"1507040100000-1507046400000\",\"minDuration\":18000000,\"minParticipants\":3},\"participants\":[{\"id\":{\"id\":\"321\"},\"name\":\"Greg\",\"importance\":1,\"availability\":[\"1507040100000-1507046400000\"]}]}"

    @Test
    fun canReadMinParticipants() {
        assertEquals(
                3,
                SchedulingJob(jobContent).getMinParticipants()
        )
    }

}