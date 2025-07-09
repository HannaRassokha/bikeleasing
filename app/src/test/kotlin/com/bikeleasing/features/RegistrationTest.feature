Feature: Registration at portal.bikeleasing.de

  Scenario Outline: All mandatory fields in registration form are filled with valid data
    Given user is on registration page
    When user enters valid employeer_code "<employer_code>" and salutation "<salutation>"
    And user enters valid surname "<surname>" and firstname "<firstname>"
    And user enters valid email "<email>" and phone "<phone>"
    And user enters valid personalid "<personalid>" and password "<password>"
    And user enters valid cost_center "<cost_center>"
    And user clicks on register button
    Then user is redirected to bikeleasing portal

    Examples:
      | employer_code | salutation | surname | firstname | email                 | phone         | personalid | password | cost_center |
      | 123456        | Herr       | Müller  | Lukas     | lukas.mueller@test.de | +491761234567 | 9010101234 | Test123! | 0           |

  Scenario Outline: Not all mandatory fields in registration form are filled with valid data
    Given user is on registration page
    When user enters valid employeer_code "<employer_code>" and salutation "<salutation>"
    And user enters valid surname "<surname>" and firstname "<firstname>"
    And user enters valid email "<email>" and phone "<phone>"
    And user enters valid personalid "<personalid>" and password "<password>"
    And user enters valid cost_center "<cost_center>"
    And user clicks on register button
    Then user sees error modal with invalidity_reason "<invalidity_reason>"

    Examples:
      | employer_code | salutation | surname | firstname | email              | phone         | personalid | password   | cost_center | invalidity_reason    |
      | 123456        | Herr       |         | Lukas     | lukas@test.de      | +491761234567 | 9010101234 | Test123!   | 0           | Empty name           |
      |               | Frau       | Schmidt | Anna      | anna@test.de       | +491512223344 | 8505056789 | Abc!2024   | 0           | no employercode      |
      | 345678        | Divers     | Meier   | Sophie    | invalid-email      | +491601112233 | 9212312345 | Qwertz7@   | 0           | invalid E-Mail       |
      | 654321        | Herr       | Schulze | Max       | max@test.de        | 1234          | 8001010003 | MaxPower1! | 1           | invalid phone number |
      | 111111        | Frau       | Müller  | Lukas     | lukas@test.de      | +491761234567 |            | Test123!   | 2           | empty personalid     |
      | 222222        | Divers     | Becker  | Lea       | lea.becker@test.de | +491761111111 | 1234567890 | short      | 3           | invalid password     |
      | 333333        | Herr       | König   | Tim       |                    | +491701234000 | 8001010002 | Abcd123!   | 4           | empty email          |
      | 444444        | Frau       | Wolf    | Jana      | jana.wolf@test.de  | +491761234567 | abc        | Valid123!  | 5           | invalid personalid   |
      | 555555        |            | Muster  | Max       | max.muster@test.de | +491761234543 | 8001010000 | Valid123!  | 6           | empty salutation     |
      | 666666        | Frau       | Fox     | Maria     | maria.fox@test.de  | +491744534543 | 8001010001 | Valid123!  |             | empty cost_center    |

  Scenario Outline: All fields in registration form are filled with valid data
    Given user is on registration page
    When user enters valid employeer_code "<employer_code>" and salutation "<salutation>"
    And user enters valid surname "<surname>" and firstname "<firstname>"
    And user enters valid email "<email>" and phone "<phone>"
    And user enters valid personalid "<personalid>" and password "<password>"
    And user enters valid cost_center "<cost_center>
    And user enters valid address "<address>" and postal_code "<postal_code>"
    And user enters valid city "<city>" and mobile_phone "<mobile_phone>"
    And user clicks on register button
    Then user is redirected to bikeleasing portal

    Examples:
      | employer_code | salutation | surname | firstname | email                | phone         | personalid | password  | address        | postal_code | city   | mobile_phone   |
      | 123456        | Herr       | Becker  | Jonas     | jonas.becker@test.de | +491761234567 | 8505051234 | Abcd123!@ | Hauptstraße 12 | 10115       | Berlin | +4915112345678 |


  Scenario Outline: Invalid or missing data in optional fields do not prevents registration
    Given user is on registration page
    When user enters valid employeer_code "<employer_code>" and salutation "<salutation>"
    And user enters valid surname "<surname>" and firstname "<firstname>"
    And user enters valid email "<email>" and phone "<phone>"
    And user enters valid personalid "<personalid>" and password "<password>"
    And user enters valid cost_center "<cost_center>"
    And user enters address "<address>" and  invalid postal_code "<postal_code>"
    And user enters valid city "<city>" and mobile_phone "<mobile_phone>"
    And user clicks on register button
    Then user is redirected to bikeleasing portal

    Examples:
      | employer_code | salutation | surname | firstname | email          | phone         | personalid | password  | cost_center | address         | postal_code | city    | mobile_phone   |
      | 123456        | Mr         | Becker  | Jonas     | jonas@test.de  | +491761234567 | 9001011234 | Abc123!@  | DPT-001     |                 | ABCDE       | Berlin  | +4915112345678 |
      | 654321        | Mrs        | Wagner  | Laura     | laura@test.de  | +491701234567 | 8505056789 | PwdTest1! | HR-42       | Invalid#Address | 12          | Hamburg | not-a-number   |
      | 789012        | Ms         | Meier   | Sophie    | sophie@test.de | +491601112233 | 9212312345 | Abcd123@  | IT-777      | @@@             |             | Leipzig |                |
