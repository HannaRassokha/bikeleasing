@redirection-to-registration
Feature: Redirection To Portal For Registration

  Scenario: Open Bikeleasing And Select Portal And Proceed To Registration
    Given Mario opens Bikeleasing url
    Then he should be on Bikeleasing page
    And he selects Portal option
    Then he should be on Portal page
    And he selects Registration option
    Then he should be on Registration page
