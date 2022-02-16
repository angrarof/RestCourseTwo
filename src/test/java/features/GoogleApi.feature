Feature: Testing google distance matrix, place, place detail and photo

  Scenario: End to end test flow
    #Get coordinates of place in maps
    Given I navigate to google maps landing page
    When I search for "industrial merida"
    Then I save the coordinates of the place

#    Call nearby places api to check place you want
    When I want to search "restaurants" near to me
    And I call "getNearbyPlace" API with "GET" http method
    Then The API call is success with status code 200
    And "status" in response body is "OK"
    And I validate "Korean Grill & ice cream" is displayed in search results

#    #Call place details api to review details of place you want
    When I add place id to request
    And I call "getPlaceDetails" API with "GET" http method
    Then The API call is success with status code 200
    And "result.name" in response body is "Korean Grill & ice cream"
    And I deserialize response body into PlaceDetails class

    #Get photo
    When I add reference of photo at index 1
    And I call "getPlacePhoto" API with "GET" http method
    Then The API call is success with status code 200
    And I download the photo of the place

