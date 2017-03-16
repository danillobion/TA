
Feature: Add Student
  As a professor
  I want to register students in the system
  So I can evaluate the students with respect to various criteria
  
#Cenário Controler
  Scenario: Register a student with a login that already exists
    Given the student "Robertinho Alves" with login "ra" is registered in the system
    When I register "Edilberto Braz" with login "ra"
    Then the system does not register "Edilberto Braz" with login "ra"

#Cenário GUI
  Scenario: Error message when registering a login twice
    Given the student "Edilberto Braz" with login "ra" is registered in the system
    When I register "Edilberto Braz" with login "ra"
    Then I do not can see the name of "Edilberto Braz" with login "ra" in the list of students
