Feature: Basic Video Conference

  Scenario: Joining a video conference with multiple users

    Given I navigate to main page with "User 1"
    And I navigate to main page with "User 2"
    Then I validate that the "Main Page" is properly presented for "User 1"
    And I validate that the "Main Page" is properly presented for "User 2"
    When I join a call for "User 1"
    And I join a call for "User 2"
    Then I validate that I am in a call
    Then I end the call








