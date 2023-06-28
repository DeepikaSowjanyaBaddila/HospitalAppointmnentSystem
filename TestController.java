package spring.orm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import spring.orm.contract.TestDAO;
import spring.orm.model.TestModel;
import spring.orm.model.input.TestInputModel;
import spring.orm.model.output.testsPatientsModel;
import spring.orm.services.TestServices;

@Controller
public class TestController {


	private TestDAO testdao;
    private TestServices testservices;
	
	@Autowired
	TestController(TestDAO testdao,TestServices testservices){
		this.testdao=testdao;
		this.testservices=testservices;
		
	}
	
	
	// Page mapping for "dcadmin/dcpatients"
	@RequestMapping(value = "dcadmin/dcpatients")
	public String dcAdminPatientPage() {
		return "dcadmin/DCPatients";
	}
	
	
	 // Request mapping for getting test details
	@RequestMapping(value = "dcadmin/gettestdetails", method = RequestMethod.GET)
	public String testDetails(Model model) {
		// Call the testdao to retrieve the list of test models
		List<TestModel> tm = testservices.getTests();
		// Add the list of test models to the model for rendering in the view
		model.addAttribute("tests", tm);
		return "dcadmin/testspage";

	}
	
	
	  // Request mapping for deleting a test
	@RequestMapping(value = "dcadmin/deltest", method = RequestMethod.POST)
	public @ResponseBody String deleteTest(@RequestParam int test_id, Model model) {
		// Call the testdao to delete the test with the given test_id
		testservices.deleteTest(test_id);
		// Retrieve the updated list of test models
		List<TestModel> tm = testservices.getTests();
		// Add the updated list of test models to the model for rendering in the view
       model.addAttribute("tests", tm);
        return "dcadmin/testspage";
	}


    // Request mapping for saving a test
	@RequestMapping(value = "dcadmin/savetest", method = RequestMethod.POST)
	public String saveTest(@ModelAttribute TestInputModel t, Model model) {
		// Call the testdao to save the test using the TestInputModel
		testdao.saveTest(t);
		// Retrieve the updated list of test models
		List<TestModel> tm = testservices.getTests();
		// Add the updated list of test models to the model for rendering in the view
        model.addAttribute("tests", tm);
		return "redirect:./gettestdetails";
	}
	
	

	 // Request mapping for updating a test
	@RequestMapping(value = "dcadmin/updatetest", method = RequestMethod.POST)
	public String updateTest(@ModelAttribute TestModel t, Model model) {
		// Call the testdao to update the test using the updated testModel object
		testservices.updateTest(t);
		// Retrieve the updated list of test models
       List<TestModel> tm = testservices.getTests();
		// Add the updated list of test models to the model for rendering in the view
        model.addAttribute("tests", tm);
       return "redirect:gettestdetails";
	}

	
	
	 // Request mapping for getting specific test details
	@RequestMapping(value = "dcadmin/gettest", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public ResponseEntity<String> getSpecialization(@RequestParam int id) {
		// Call the testdao to retrieve the test model with the given test_id
		TestModel s = testservices.getTestById(id);
		// Returns a response entity with a JSON representation of the object 's'
		return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(s));
	}
	
	
	
	
	// Request mapping for getting all tests
	@RequestMapping(value = "dcadmin/getalltests", method = RequestMethod.GET)
	public ResponseEntity<String> getAllTests(Model model) {
		// Calls the method in TestDaoImpl to retrieve all tests.
		List<TestModel> testmodel = testservices.getTests();
		// Returns a response entity with a JSON representation of the object 'tm' containing all tests.
		return ResponseEntity.status(HttpStatus.OK).body(new Gson().toJson(testmodel));
	}
	
	
	
	 // Request mapping for getting all test-patients
	@RequestMapping(value = "dcadmin/getalltestspatients", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	public @ResponseBody List<testsPatientsModel> getAllTestPatients(Model model) {
		// Calls the method in TestDaoImpl to retrieve all patients who booked a test.
		List<testsPatientsModel> tpm = testdao.getAllTestPatients();
		return tpm;
	}
	
	
	
	  // Request mapping for getting test-wise patients
	@RequestMapping(value = "dcadmin/gettestwisepatients", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public @ResponseBody List<testsPatientsModel> getTestWisePatients(@RequestParam int test, Model model) {
		// Calls the method in TestDaoImpl to retrieve patients who booked the specified test.
		List<testsPatientsModel> tpm = testdao.getTestWisePatients(test);
        return tpm;
	}
	
	@RequestMapping(value = "dcadmin/getalltestpatientdetails", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public @ResponseBody List<testsPatientsModel> getAllTestPatientDetails(@RequestParam int test,
			@RequestParam String date1, @RequestParam String date2, Model model) {
		// Calling the "getAllTestPatientDetails" method of the "testdao" object, passing the
	    // "test", "date1", and "date2" parameters. This method is expected to return a List of
	    // testsPatientsModel objects, which is assigned to the "tpm" variable.

		List<testsPatientsModel> tpm = testdao.getAllTestPatientDetails(test, date1, date2);
		// System.out.println(tpm.toString());
		return tpm;
	}

}