Feature: Verify Instruments API

  Scenario: List Instruments
    Given I am logged in with a valid user account
    When I GET a list of all the available Instruments offered on the DriveWealth platform
    Then Verify API response

  Scenario: Retrieve Instrument
    Given I am logged in with a valid user account
    When I GET the details of a specific Instrument
    Then Verify API response

  Scenario: Retrieve Options Chain
    Given I am logged in with a valid user account
    When I GET a list of Options chain based on query parameters
    Then Verify API response

  Scenario: Retrieve Options Expiration
    Given I am logged in with a valid user account
    When I GET option expiration dates for an underlying security
    Then Verify API response

  Scenario: Search Instrument by Filter
    Given I am logged in with a valid user account
    When I POST a filter criteria to return a list of matching instruments
    Then Verify API response