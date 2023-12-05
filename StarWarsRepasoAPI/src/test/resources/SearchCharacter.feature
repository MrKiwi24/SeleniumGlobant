Feature: Search a given character on Wikipedia
  In order to search a Star Wars character on Wikipedia
  With the info from swapi
  I want to get the character's name with an id and and use that name to search it

  Scenario: Search a Star Wars Character on Wikipedia
    Given the user gets the <characterName> with the <id> from the API
    When goes to Wikipedia and searches the <characterName>
    Then Wikipedia should display the character info