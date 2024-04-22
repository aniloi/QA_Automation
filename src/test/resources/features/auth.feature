Feature: Verify Authentication API

  Scenario: Authenticate with a valid credentials using a data table
    Given I log in with the following credentials
      | dwClientAppKey | 2d9f930e-7a85-524d-cd02-5d2f5b0bb7fc |
      | username       | bo-api.qa.ruby                       |
      | password       | 1234qwer                             |
      | appTypeID      | 4                                    |
    Then the scenario passes