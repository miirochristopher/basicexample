package org.openmrs.module.basicexample.api.impl;

import org.openmrs.api.APIException;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.basicexample.Doctor;
import org.openmrs.module.basicexample.api.DoctorService;
import org.openmrs.module.basicexample.api.dao.DoctorDao;

import java.util.List;

public class DoctorServiceImpl extends BaseOpenmrsService implements DoctorService {
	
	private DoctorDao doctorDao;
	
	/**
	 * Injected in moduleApplicationContext.xml
	 */
	public void setDoctorDao(DoctorDao doctorDao) {
		this.doctorDao = doctorDao;
	}
	
	@Override
	public Doctor saveDoctor(Doctor doctor) throws APIException {
		return doctorDao.saveDoctor(doctor);
	}
	
	@Override
	public List<Doctor> getAllDoctors() throws APIException {
		return doctorDao.getAllDoctors();
	}
	
	@Override
	public Doctor getDoctorById(Integer id) throws APIException {
		return doctorDao.getDoctorById(id);
	}
	
	@Override
	public void updateDoctor(Doctor doctorToUpdate) throws APIException {
		doctorDao.updateDoctor(doctorToUpdate);
	}
	
	@Override
	public void deleteDoctor(Doctor doctorToDelete) throws APIException {
		doctorDao.deleteDoctor(doctorToDelete);
	}
}
