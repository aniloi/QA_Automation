Feature: Verify Authentication API

  @auth
  Scenario: Authenticate with valid credentials using a data table
    Given I log in with the following credentials
      | dwClientAppKey | 942176f1-9f6f-427c-b9a4-bd99f74d2914 |
      | username       | bo.niloi.api                         |
      | password       | passw0rd                             |
      | appTypeID      | 4                                    |
    Then the response contains an auth token
    And Verify the response status code is 200
    And Verify the response schema matches "Auth Token Response Schema"