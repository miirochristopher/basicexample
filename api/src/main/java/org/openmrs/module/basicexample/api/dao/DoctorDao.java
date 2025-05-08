package org.openmrs.module.basicexample.api.dao;

import org.hibernate.criterion.Restrictions;
import org.openmrs.api.db.hibernate.DbSession;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.basicexample.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("doctorDao")
public class DoctorDao {
	
	@Autowired
	DbSessionFactory sessionFactory;
	
	private DbSession getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	//save a doctor in the database
	public Doctor saveDoctor(Doctor doctor) {
		getSession().saveOrUpdate(doctor);
		return doctor;
	}
	
	//getting a list of all doctors from the database
	@SuppressWarnings("unchecked")
	public List<Doctor> getAllDoctors() {
		List<Doctor> doctors = getSession().createCriteria(Doctor.class).list();
		return doctors;
	}
	
	//get a doctor by id
	public Doctor getDoctorById(Integer id) {
		return (Doctor) getSession().createCriteria(Doctor.class).add(Restrictions.eq("id", id)).uniqueResult();
	}
	
	//update a doctor
	public void updateDoctor(Doctor doctorToUpdate) {
		getSession().update(doctorToUpdate);
	}
	
	//delete a doctor
	public void deleteDoctor(Doctor doctorToDelete) {
		getSession().delete(doctorToDelete);
	}
	
}
