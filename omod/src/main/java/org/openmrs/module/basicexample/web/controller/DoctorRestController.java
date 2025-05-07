package org.openmrs.module.basicexample.web.controller;

import org.openmrs.api.APIException;
import org.openmrs.module.basicexample.Doctor;
import org.openmrs.module.basicexample.api.DoctorService;
import org.openmrs.module.basicexample.response.DoctorResponse;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
}
}
