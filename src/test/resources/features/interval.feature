Feature: Interval algorithm

  Scenario:
    Given there is a meeting scheduling problem
    And this meeting has minimum participants count of '2'
    And this meeting has minimum duration of '3' hours
    And this meeting has to happen in period '2017-10-02T14:00Z/2017-10-06T16:00Z'
    And there is a participant
    And this participant declares availability '2017-10-02T14:00Z/2017-10-06T16:00Z' with weight '1'
    And this participant declares availability '2017-10-02T14:00Z/2017-10-06T16:00Z' with weight '1'
    When the meeting was scheduled
    Then the meeting scheduled is in period '2017-10-02T14:00Z/2017-10-06T16:00Z'