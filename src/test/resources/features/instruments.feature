Feature: Verify Instruments API

  @ListInstruments
  Scenario: List Instruments
    Given I am logged in with a valid user account
    When I GET a list of all the available Instruments offered on the DriveWealth platform
      | status           | ACTIVE |
      | isOptionsEnabled | true   |
    Then Verify the response status code is 200
#    And Verify the response schema matches "List Of Instruments Schema"

  @RetrieveInstruments
  Scenario: Retrieve Instrument
    Given I am logged in with a valid user account
    When I GET the details of "AMZN"
    Then Verify the response status code is 200
    And Verify the response contains the correct symbol for "AMZN"
#    And Verify the response schema matches "Retrieved Instruments Schema"

  @RetrieveOptions
  Scenario: Retrieve Options Chain
    Given I am logged in with a valid user account
    When I GET a list of Options chain based on query parameters for "AAPL"
      | expirationDate | 2022-05-15                           |
      | page           | 1                                    |
      | pageSize       | 20                                   |
      | sortOrder      | asc                                  |
      | noOfStrikes    | 5                                    |
      | minStrikePrice | 20                                   |
      | maxStrikePrice | 100                                  |
      | optionType     | call                                 |
      | id             | 3fb1e8a9-f7d5-4d90-95e2-43e7326b5636 |
      | filterBy       | expirationDate                       |
    Then Verify the response status code is 200

  @RetrieveOptionsExp
  Scenario: Retrieve Options Expiration
    Given I am logged in with a valid user account
    When I GET option expiration dates for "AAPL"
    Then Verify the response status code is 200

  @SearchInstrumentsFilter
  Scenario: Search Instrument by Filter
    Given I am logged in with a valid user account
    When I POST a filter criteria to return a list of matching instruments
    Then Verify the response status code is 200