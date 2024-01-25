Feature: open chrome browser to search for a product

  Scenario Outline: Search for a product
    Given open chrome
    When Input "<keyword>" in the search box and click on the search button
    Then Verify that the result page is displayed with the searched "<content>"
    Examples:
      | keyword  | content |
      | Cucumber | 黄瓜      |
      | java     | 哔哩哔哩     |
