package com.scherule.scheduling.behavioral.steps

import com.scherule.scheduling.algorithms.types.interval.projection.Availability
import com.scherule.scheduling.algorithms.types.interval.projection.IntervalProjectionAlgorithm
import com.scherule.scheduling.builders.ParticipationBuilder
import com.scherule.scheduling.builders.ParticipationBuilder.Companion.aParticipation
import com.scherule.scheduling.converters.DurationConverter
import com.scherule.scheduling.converters.IntervalConverter
import com.scherule.scheduling.helpers.SchedulingProblemPojo
import com.scherule.scheduling.helpers.SchedulingProblemPojo.Companion.aSchedulingProblem
import cucumber.api.Transform
import cucumber.api.java.en.Given
import cucumber.api.java.en.When
import cucumber.runtime.java.guice.ScenarioScoped
import org.joda.time.Duration
import org.joda.time.Interval
import java.util.*
import javax.inject.Inject

@ScenarioScoped
internal class SchedulingSteps @Inject constructor(
        private val meetingScheduler: IntervalProjectionAlgorithm
) {

    var problemBuilder: SchedulingProblemPojo.Builder = aSchedulingProblem()
    var participationBuilderStack: Stack<ParticipationBuilder> = Stack()

    @Given("there is a meeting scheduling problem")
    fun givenThereIsMeetingSchedulingProblem() {
        problemBuilder = aSchedulingProblem()
    }

    @Given("this meeting has minimum participants count of '(\\d+)'")
    fun givenThisMeetingHasMinimumParticipantsCountOf(minParticipants: Int) =
            problemBuilder.withMinParticipants(minParticipants)

    @Given("this meeting has minimum duration of '(.*)' hours")
    fun givenThisMeetingHasMinimumDurationOfHours(
            @Transform(DurationConverter::class) minLength: Duration
    ) = problemBuilder.longerThan(minLength)

    @Given("this meeting has to happen in period '(.*)'")
    fun givenThisMeetingHasToHappenInPeriod(
            @Transform(IntervalConverter::class) interval: Interval
    ) = problemBuilder.inBetween(interval)


    @Given("there is a participant")
    fun givenThereIsParticipant() {
        participationBuilderStack.push(aParticipation())
    }

    @Given("this participant declares availability '(.*)' with weight '(\\d+)'")
    fun givenThisParticipantDeclaresAvailabilityWithWeight(
            @Transform(IntervalConverter::class) interval: Interval,
            weight: Int
    ) = participationBuilderStack.peek().withAvailability(Availability(weight, interval))


    @When("the meeting was scheduled")
    fun whenTheMeetingWasScheduled() = meetingScheduler.schedule(problemBuilder
            .withParticipation(*participationBuilderStack.map { it.build() }.toTypedArray())
            .build()
    )


    @When("the meeting scheduled is in period '(.*)'")
    fun thenTheMeetingScheduledIsInPeriod() {
        System.out.println("x")
    }

}