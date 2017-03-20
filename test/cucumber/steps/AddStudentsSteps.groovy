package steps

import cucumber.api.PendingException
import pages.AddGroupStudentPage
import ta.Student
import steps.AddStudentsTestDataAndOperations
import pages.AddStudentsPage
import pages.StudentPages.StudentPage

/**
 * Created by rodrigocalegario on 5/28/16.
 */

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

int countStudent

Given(~'^the student "([^"]*)" with login "([^"]*)" and password "([^"]*)" is not registered in the system$') { String name, String login, String password ->
    assert Student.findByLogin(login) == null
}

When(~'^I register "([^"]*)" with login "([^"]*)" and password "([^"]*)"$' ) { String name, String login, String password ->
    AddStudentsTestDataAndOperations.createStudent(name, login, password)
}

When(~'^I add the "([^"]*)" with login "([^"]*)" and password "([^"]*)"$') { String name, String login, String password ->
	to AddStudentsPage
	at AddStudentsPage
	countStudent = AddStudentsTestDataAndOperations.countStudent()
	page.fillStudentDetails(name, login, password)
	page.selectAddStudent()
}

Then(~'^the student "([^"]*)" with login "([^"]*)" is saved in the system$') { String name, String login ->
    Student student = Student.findByLogin(login)
    assert AddStudentsTestDataAndOperations.compatibleTo(student, name, login)
}


Then(~'^I can see the name of "([^"]*)" and the login "([^"]*)" in the list of students$') { String name, String login ->
    to StudentPage
    assert page.confirmStudent(name, login)
}

Then(~'^I do not can see the name of "(.*?)" with login "(.*?)" and password "(.*?)" in the list of students$') { String name, String login, String password ->
	to StudentPage
	assert AddStudentsTestDataAndOperations.alunoQtd(login) == 1
	//assert page.qtdStudentTable(countStudent)
}

Given(~'^the student "([^"]*)" with login "([^"]*)" and password "([^"]*)" is registered in the system$') { String name, String login, String password ->
    AddStudentsTestDataAndOperations.createStudent(name, login, password)
    countStudent = AddStudentsTestDataAndOperations.countStudent()
    assert Student.findByLogin(login) != null
}

Then(~'^the system does not register "([^"]*)" with login "([^"]*)" and password "([^"]*)"$') { String name, String login, String password ->
    assert AddStudentsTestDataAndOperations.alunoQtd(login) == 1
}

Then(~/^the system does not register the student "(.*?)" with login "(.*?)" and password "(.*?)"$/) { String name, String login, String password ->
    assert AddStudentsTestDataAndOperations.alunoQtd(login) == 0
}

Then(~'^I can see the name of "([^"]*)" and the login "([^"]*)" in the list of students only once$') { String name, String login ->
    to StudentPage
    assert page.qtdStudentTable(countStudent)
}

When(~'^I send a text with "([^"]*)"$') { String group ->
    AddStudentsTestDataAndOperations.createGroup(group)
}

And(~'^I go to the "([^"]*)" page$') { String page ->
    if(page.equals("create group")){
        to AddGroupStudentPage
    }else if(page.equals("add student")){
        to AddStudentsPage
    }
}

And(~'^I am in the "([^"]*)" page$') { String page ->
    if(page.equals("create group")){
        to AddGroupStudentPage
    }else if(page.equals("add student")){
        to AddStudentsPage
    }else if(page.equals("sudent list")){
        to StudentPage
    }
}

When(~'^I add the text "([^"]*)"$') { String text ->
    countStudent = AddStudentsTestDataAndOperations.countStudent()
    page.fillGroupStudentDetails(text)
    page.selectAddGroup()
    countStudent ++;
}

And(~'^the name of "([^"]*)" and the login "([^"]*)" is already in the list of student$') { String name, String login->
    to AddStudentsPage
    page.fillStudentDetails(name, login)
    page.selectAddStudent()
    to StudentPage
    assert page.confirmStudent(name, login)
}

Then(~'^I should see a message related to the student registration failure$'){->
	at AddStudentsPage
	assert page.checkForErrors()
}