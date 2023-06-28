package spring.orm.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import spring.orm.contract.SpecializationDAO;
import spring.orm.model.Specialization;

@Component
public class SpecializationDAOImpl implements SpecializationDAO {

	@PersistenceContext
	private EntityManager em;

	@Transactional
	@Override
	public Specialization getSpecialization(String Id) {
		// Retrieve a specialization by its ID
		return em.find(Specialization.class, Id);
	}

	@Override
	public List<Specialization> getAllSpecializations() {
		// Retrieve all specializations that are not marked as deleted
		return em.createQuery("select s from Specialization s where isDeleted = false", Specialization.class)
				.getResultList();
	}

	@Transactional
	@Override
	public void deleteSpecialization(String id) {
		// Delete a specialization by setting its 'isDeleted' flag to true
		Specialization s = em.find(Specialization.class, id);
		s.setDeleted(true);
		em.merge(s);
	}

	@Transactional
	@Override
	public void addSpecialization(Specialization s) {
		// Add a specialization to the database
		if (em.find(Specialization.class, s.getId()) == null) {
			//If the specialization is new then add
			em.persist(s);
		} else {
			//If the specialization is soft deleted then change the status
			Specialization alreadyexisted = em.find(Specialization.class, s.getId());
			alreadyexisted.setDeleted(false);
			em.merge(alreadyexisted);
		}
	}

	@Transactional
	@Override
	public void updateSpecialization(Specialization s) {
		// Update a specialization in the database
		em.merge(s);

	}

}