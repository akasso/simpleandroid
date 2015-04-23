Feature: Menu
  As a gamer when I start the app I want a menu screen to appear
  
  Scenario: Menu
    Given I see the "Simple Android Application"
    Then I should see "Ping Pong"
    And I should see a "Start" button
    And I should see a "Sound On" button
    
  Scenario: Start a new game
    Given I see the "Simple Android Application"
    When I touch the "Start" button
    Then I should see "GameActivity"

  Scenario: Audio button
    Given I should see a "Sound On" button
    When I touch the "Sound On" button
    Then I should see a "Sound Off" button
