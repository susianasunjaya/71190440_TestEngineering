Feature: feature to test register functionality
  Scenario Outline: Check register with valid credentials
    Given browser opened
    And user in register page
    When user insert <username> and <password>
    And register button clicked
    Then user redirect to login screen
    Examples:
    |username              |password   |
    |susiana@gmail.com     |TI190440   |
    |susiana@gmail.com     |           |
    |                      |TI190440   |
    |                      |           |
    |susiana@gmail.com     |TI0440     |
    |susiana@gmail.com     |TI71190444444444000|
    |susiana@gmail.com     |TI190440_?/|
