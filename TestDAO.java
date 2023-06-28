package spring.orm.contract;

import java.util.List;

import spring.orm.model.TestModel;
import spring.orm.model.input.TestInputModel;
import spring.orm.model.output.patientsoutputmodel;
import spring.orm.model.output.testsCategoriesModel;
import spring.orm.model.output.testsPatientsModel;

public interface TestDAO {

	public void saveTest(TestInputModel s);

	public List<TestModel> getTests();

	public TestModel getTestById(int id);

	public void deleteTest(int test_id);

	public List<TestModel> getTestByCategory(String cat);

	public Object getTestPrice(int tests);

	public void updateTest(TestModel t);

	public List<testsPatientsModel> getAllTestPatients();

	public List<testsPatientsModel> getTestWisePatients(int test);

	List<testsPatientsModel> getAllTestPatientDetails(int test, String date1, String date2);

	List<patientsoutputmodel> getPatients();

	public List<testsCategoriesModel> getTestsCategories();

}
