Feature: Basic Search Flow


  Background: The url is launched and the application is up and running.

  Scenario: Verify the availability of seats and its display message.
    Given the departure and return fields are available on the screen
    When the user enters departure and arrival into the fields and click on search button
      | Departing |  | Arrival                       |
      | July      |  | December (two years from now) |
    Then it should display "Seats available! Call now on 0800 MARSAIR to book!"
#defect -"Seats available! Call now on 0800 MARSAIR to book!"


  Scenario: Verify the unavailability of seats and its display message
    Given the departure and return fields are available on the screen
    When the user enters departure and arrival into the fields and click on search button
      | Departing |  | Arrival                   |
      | July      |  | July (two years from now) |
    And it should display "Sorry, there are no more seats available."

  Scenario: Verify the message when the return dates are invalid
    Given the departure and return fields are available on the screen
    When the user enters departure and arrival into the fields and click on search button
      | Departing |  | Arrival  |
      | July      |  | December |
    And it should display "Unfortunately, this schedule is not possible. Please try again."

  Scenario: Verify the user is able to go back to the flight search from anywhere on the site
    Given the text "Book a ticket to the red planet now!" is available on the screen
    When the user clicks the text, it should land to the homepage
      #-defect-It is not clickable
    And also on clicking  the MarsAir logo on the top left  should  take the user to the home page.

  Scenario Outline: Verify the promotional code and the message once it is applied
    Given the departure and return fields are available on the screen
    When the user enters "<departing>" and "<arrival>" into the fields and "<promotionalcode>" and click on search button
    Then verify "<finalmessage>"  for the "<promotionalcode>" is as expected
    Examples:
      | departing | arrival                       | promotionalcode | finalmessage                                         |
      | July      | December (two years from now) | XA6-XAS-949     | Promotional code [XA6-XAS-949] used: [60]% discount! |
      | July      | December (two years from now) | XA6R-XAS-949    | Sorry, code [XA6R-XAS-949] is not valid              |