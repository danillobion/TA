﻿Feature: Add Student
  As a professor
  I want to register students in the system
  So I can evaluate the students with respect to various criteria

#Cenário Controler
  Scenario: Register a student with a login that already exists
    Given the student "Robertinho Alves" with login "ra" and password "bibi" is registered in the system
    When I register "Edilberto Braz" with login "ra" and password "bibi"
    Then the system does not register "Edilberto Braz" with login "ra" and password "bibi"

#Cenário GUI
  Scenario: Error message when registering a login twice
    Given the student "Edilberto Braz" with login "ra" and password "bibi" is registered in the system
    When I register "Edilberto Braz" with login "ra" and password "bibi"
    Then I do not can see the name of "Edilberto Braz" with login "ra" and password "bibi" in the list of students
  
#Controller Scenario
	Scenario: I add a student with name " "
	Given the student " " with login "Fa" and password "bibi" is not registered in the system
	When I add the " " with login "Fa" and password "bibi"
	Then the system does not register the student " " with login "Fa" and password "bibi"

#GUI Scenario
   Scenario: Error when I add a student with name " "
	Given the student " " with login "Fa" and password "bibi" is not registered in the system
	And I am in the "add student" page
    When I add the " " with login "Fa" and password "bibi"
    Then I should see a message related to the student registration failure

