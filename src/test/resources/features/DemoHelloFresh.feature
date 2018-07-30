Feature: Basic feature for HelloFreshDemo
  In order to show automation basic test and output report

  Background:
    Given I go to the main page "www.hellofresh.com"

  Scenario: Log in
    When I click "Log in"
    And I Enter email = "email@mail.com"
    And I Enter password = "132456"
    And I click Login to submit
    Then I should see message "Incorrect username or password."

  Scenario: Click plans
    When I click "Our plans"
    Then I should see Plans page

