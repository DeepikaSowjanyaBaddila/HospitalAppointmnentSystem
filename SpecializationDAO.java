package spring.orm.contract;

import java.util.List;

import spring.orm.model.Specialization;

public interface SpecializationDAO {

	public Specialization getSpecialization(String Id);

	public List<Specialization> getAllSpecializations();

	public void deleteSpecialization(String id);

	void addSpecialization(Specialization s);

	void updateSpecialization(Specialization s);


}
