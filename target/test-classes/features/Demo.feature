Feature: Basic feature for demo
  In order to show automation basic test and output report

  Background:
    Given I am navigated to main page

  Scenario: Searching for a string pattern
    When I am searching for a string pattern Automation
    Then I should see at least 1 search result

  Scenario: Searching for a string pattern (negative)
    When I am searching for a string pattern Automation
    Then I should not see any search result


