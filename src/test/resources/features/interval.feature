Feature: Interval algorithm

  Scenario: Pick only available interval
    Given there is a meeting scheduling problem
    And this meeting has to happen in period '2017-10-02T14:00Z/2017-10-06T16:00Z'
    And there is a participant
    And this participant declares availability '2017-10-02T14:00Z/2017-10-06T16:00Z'
    When the meeting was scheduled
    Then the meeting scheduled is in period '2017-10-02T14:00Z/2017-10-06T16:00Z'


  Scenario: Pick longer available interval
    Given there is a meeting scheduling problem
    And this meeting has to happen in period '2017-10-01T00:00Z/2017-10-30T16:00Z'
    And there is a participant
    And this participant declares availability '2017-10-02T09:00Z/2017-10-02T10:00Z'
    And this participant declares availability '2017-10-03T12:00Z/2017-10-03T15:00Z'
    And this participant declares availability '2017-10-04T18:00Z/2017-10-04T19:00Z'
    When the meeting was scheduled
    Then the meeting scheduled is in period '2017-10-03T12:00Z/2017-10-03T15:00Z'