package org.openmrs.module.basicexample.api;

import org.openmrs.annotation.Authorized;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.basicexample.Doctor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface DoctorService extends OpenmrsService {
	
	@Authorized()
	@Transactional
	Doctor saveDoctor(Doctor doctor) throws APIException;
	
	@Authorized
	@Transactional
	List<Doctor> getAllDoctors() throws APIException;
	
	@Authorized
	@Transactional
	Doctor getDoctorById(Integer id) throws APIException;
	
	@Authorized
	@Transactional
	void updateDoctor(Doctor doctorToUpdate) throws APIException;
	
	@Authorized
	@Transactional
	void deleteDoctor(Doctor doctorToDelete) throws APIException;
}
