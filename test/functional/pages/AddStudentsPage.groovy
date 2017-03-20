package pages

import geb.Page

/**
 * Created by rodrigocalegario on 5/28/16.
 */
class AddStudentsPage extends Page{

    static url = "/TA/student/create"

    static at =  {
        title ==~ /Create Student/
    }

    def fillStudentDetails(String name, String login, String password) {
        $("form").name = name
        $("form").login = login
		$("form").password = password
    }

    def selectAddStudent() {
        $("input", name: "create").click()
    }
	
	def boolean checkForErrors() {
		return $("ul", class: "errors").isDisplayed()
	}
}
