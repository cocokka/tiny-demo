Feature: i have landed on login screen

  Background:
    Given open the login page
    """json
    {
      "firstname": "ha",
      "lastname": "er"
    }
    """

  Scenario: user can login successfully with valid username and password
    When i enter valid "zhangsan" and "password123" in the input field
    Then i can see login button is active
    When i click on login button
    Then I will enter home page

  Scenario: user can login failed with invalid username and password
    When i enter valid "lisi" and "password23" in the input field
    Then i can see login button is active
    When i click on login button
    Then I can't enter home page

  Scenario Outline: google
    Given open the "<url>"
    When input "<keyword>"
    Then show "<result>"
    Examples:
      | url            | keyword | result |
      | www.google.com | hello   | world  |
      | www.baidu.com  | what    | niuma  |