Feature: Login to portal.bikeleasing.de

  Scenario Outline: Login with valid credentials
    Given user is on login page
    When user enters userid "<userid>" and password "<password>"
    And clicks login button
    Then user should be redirected to the dashboard

    Examples:
      | userid     | password  |
      | A1B2-C3D4  | P@sword1  |
      | A1B2-C3D45 | P1@abcdef |

  Scenario Outline: Login with invalid credentials
    Given user is on login page
    When user enters userid "<userid>" and password "<password>"
    And clicks login button
    Then user sees notification error message

    Examples:
      | userid    | password  | # Explanation
      | A1B2-C3D5 | Hello123! | # userid or password is incorrect
      | A5B6-C7D9 | Test!123  | # user does not exist in the system

  Scenario Outline: Login with invalid password format
    Given user is on login page
    When user enters userid "<userid>" and password "<password>"
    And clicks login button
    Then user sees notification error message

    Examples:
      | userid    | password   | # Explanation
      | 123456789 | 1234567    | # password too short (less then 8)
      | 123456789 | password1! | # no capital letter
      | 123456789 | Password!  | # no specific number
      | 123456789 | Password1  | # no specific character

  Scenario Outline: Login button disabled for invalid userid and password
    Given user is on login page
    When user enters userid "<userid>" and password "<password>"
    Then login button is disabled and user cannot login

    Examples:
      | userid    | password   |
      | A1B1-C2D3 | EMPTY      |
      | 12345678  | 1          |
      | EMPTY     | Password1! |
      | EMPTY     | EMPTY      |