package org.openmrs.module.basicexample.web.controller;

import org.openmrs.api.APIException;
import org.openmrs.module.basicexample.Doctor;
import org.openmrs.module.basicexample.api.DoctorService;
import org.openmrs.module.basicexample.response.DepartmentResponse;
import org.openmrs.module.basicexample.response.DoctorResponse;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller(value = "doctorRestController")
@RequestMapping(value = "/rest/" + RestConstants.VERSION_1 + "/" + "doctor")
public class DoctorRestController {
	
	@Autowired
	private DoctorService doctorService;
	
	@RequestMapping(value = "/save/doctor", method = RequestMethod.POST)
    public ResponseEntity<DoctorResponse> saveDoctor(@RequestBody Doctor doctor){
        try{
            Doctor savedDoctor = doctorService.saveDoctor(doctor);
            DoctorResponse doctorResponse = new DoctorResponse();
            doctorResponse.setName(savedDoctor.getName());
            doctorResponse.setDepartment(savedDoctor.getDepartment());
            doctorResponse.setContact(savedDoctor.getContact());
            return new ResponseEntity<>(doctorResponse, HttpStatus.OK);
        }catch(APIException exception){
        throw new APIException(exception.getMessage());
        }

    }
	
	@RequestMapping(value = "/getAllHospitalDoctors", method = RequestMethod.GET)
    public ResponseEntity<List<DoctorResponse>> getAllDoctors(){
        try{
            List<Doctor> doctors = doctorService.getAllDoctors();
            List<DoctorResponse> listOfDoctors = new ArrayList<>();
            for(Doctor doctor : doctors){
                DoctorResponse doctorResponse2 = new DoctorResponse();
                doctorResponse2.setName(doctor.getName());
                doctorResponse2.setDepartment(doctor.getDepartment());
                doctorResponse2.setContact(doctor.getContact());
                listOfDoctors.add(doctorResponse2);
            }
            if(!listOfDoctors.isEmpty()){
                return new ResponseEntity<>(listOfDoctors, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }catch(APIException exception){
            throw new APIException(exception.getMessage());
        }
}
	
	@RequestMapping(value = "/find/doctor/{id}", method = RequestMethod.GET)
    public ResponseEntity<DoctorResponse> findDoctorById(@PathVariable Integer id){
        try{
            Doctor foundDoctor = doctorService.getDoctorById(id);
            if(foundDoctor != null){
                DoctorResponse doctorResponse3 = new DoctorResponse();
                doctorResponse3.setName(foundDoctor.getName());
                doctorResponse3.setDepartment(foundDoctor.getDepartment());
                doctorResponse3.setContact(foundDoctor.getContact());
                return new ResponseEntity<>(doctorResponse3, HttpStatus.OK);
            }else{
               return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch(APIException exception){
            throw new APIException(exception.getMessage());
        }
    }
	
	@RequestMapping(value = "/update/doctor/{id}", method = RequestMethod.PUT)
    public ResponseEntity<DoctorResponse> updateDoctor(@PathVariable Integer id, @RequestBody Doctor doctorToUpdate){
        try{
            Doctor doctorToFinallyUpdate = doctorService.getDoctorById(id);
            if(doctorToFinallyUpdate != null){
                doctorToFinallyUpdate.setName(doctorToUpdate.getName());
                doctorToFinallyUpdate.setDepartment(doctorToUpdate.getDepartment());
                doctorToFinallyUpdate.setContact(doctorToUpdate.getContact());
                doctorService.updateDoctor(doctorToFinallyUpdate);
                DoctorResponse doctorResponse4 = new DoctorResponse();
                doctorResponse4.setName(doctorToFinallyUpdate.getName());
                doctorResponse4.setDepartment(doctorToFinallyUpdate.getDepartment());
                doctorResponse4.setContact(doctorToFinallyUpdate.getContact());
                return new ResponseEntity<>(doctorResponse4, HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }catch(APIException exception){
            throw new APIException(exception.getMessage());
        }
    }
	
	@RequestMapping(value = "/delete/doctor/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteDoctor(@PathVariable Integer id){
        try{
            Doctor doctorToDelete = doctorService.getDoctorById(id);
            if(doctorToDelete != null){
                doctorService.deleteDoctor(doctorToDelete);
                return new ResponseEntity<>("You have successfully deleted a doctor", HttpStatus.OK);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch(APIException exception){
            throw new APIException(exception.getMessage());
        }
    }
}
