package spring.orm.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.orm.contract.TestDAO;
import spring.orm.model.TestModel;

@Service
public class TestServices {
	@Autowired
	private TestDAO tdao;
	
	//Used to get and display the test
	public List<TestModel> getTests() {
		return tdao.getTests();
	}


	//get the test by testid
	public TestModel getTestById(int id) {
		return tdao.getTestById(id);
	}
	
	
	//Used to update the test
	public void updateTest(TestModel t) {
		tdao.updateTest(t);

	}
	
	
	//Used to delete the test
	public void deleteTest(int test_id) {
		
		tdao.deleteTest(test_id);

	}

	//Gives the test by categorywise in testbillgen
      public List<TestModel> getTestByCategory(String cat) { 
		
		return tdao.getTestByCategory(cat);
	}
      
		//Gives the test price used in testbillgen
      public Object getTestPrice(int test) {
		return tdao.getTestPrice(test);
	}

}