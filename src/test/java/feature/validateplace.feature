Feature: Validation Add Place APIs

  Scenario Outline: verifying place is added successfully using post api ADD_Place
    Given Add place API PayLoad "<Address>" "<Name>" "<Language>"
    When user calls "addplace" API "POST" https call
    Then Api call got with successful with status code 200
    And "status" in response body is "OK"
    And Verfiy place id created maps to "<Name>" using "GetPlaceEndP"

    Examples:
      | Name | Address | Language |
      | Dave| eDassame | Marathi  |
