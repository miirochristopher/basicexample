package org.openmrs.module.basicexample.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.openmrs.api.APIException;
import org.openmrs.module.basicexample.Department;
import org.openmrs.module.basicexample.api.BasicexampleService;
import org.openmrs.module.basicexample.response.DepartmentResponse;
import org.openmrs.module.webservices.rest.web.RestConstants;
import org.openmrs.module.webservices.rest.web.v1_0.controller.MainResourceController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller(value = "basicexampleRestController")
@RequestMapping(value = "/rest/" + RestConstants.VERSION_1 + "/" + "basicexample")
public class BasicexampleRestController extends MainResourceController {
	
	@Autowired
	private BasicexampleService basicexampleService;
	
	ObjectMapper mapper = new ObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
	
	@RequestMapping(value = "/department/find/by/id/{id}", method = RequestMethod.GET)
	public ResponseEntity<DepartmentResponse> getDepartmentById(@PathVariable Integer id) {
		Department department = basicexampleService.getDepartmentById(id);
		String safetyMeasures = department.getPatientSafetyMeasures();
		DepartmentResponse departmentResponse = new DepartmentResponse();
		if (department != null) {
			departmentResponse.setDepartmentName(department.getDepartmentName());
			departmentResponse.setLocation(department.getLocation());
			departmentResponse.setDateCreated(department.getDateCreated());
			departmentResponse.setDepartmentSafetyMeasures(safetyMeasures);
			return new ResponseEntity<DepartmentResponse>(departmentResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<DepartmentResponse>(new DepartmentResponse(), HttpStatus.NO_CONTENT);
		}
	}
	
	@RequestMapping(value = "/department/find/by/uuid/{uuid}", method = RequestMethod.GET)
	public ResponseEntity<DepartmentResponse> getDepartmentByUuid(@PathVariable String uuid) {
		Department department = basicexampleService.getDepartmentByUuid(uuid);
		String safetyMeasures = department.getPatientSafetyMeasures();
		DepartmentResponse departmentResponse = new DepartmentResponse();
		if (department != null) {
			departmentResponse.setDepartmentName(department.getDepartmentName());
			departmentResponse.setLocation(department.getLocation());
			departmentResponse.setDateCreated(department.getDateCreated());
			departmentResponse.setDepartmentSafetyMeasures(safetyMeasures);
			return new ResponseEntity<DepartmentResponse>(departmentResponse, HttpStatus.OK);
		} else {
			return new ResponseEntity<DepartmentResponse>(new DepartmentResponse(), HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/department/save", method = RequestMethod.POST)
	public ResponseEntity<DepartmentResponse> saveDepartment(@RequestBody Department department) {
		try {
			Department savedDepartment = basicexampleService.saveDepartment(department);
			String safetyMeasures = savedDepartment.getPatientSafetyMeasures();
			DepartmentResponse departmentResponse = new DepartmentResponse();
			departmentResponse.setDepartmentName(savedDepartment.getDepartmentName());
			departmentResponse.setLocation(savedDepartment.getLocation());
			departmentResponse.setDateCreated(savedDepartment.getDateCreated());
			departmentResponse.setDepartmentSafetyMeasures(safetyMeasures);
			return new ResponseEntity<DepartmentResponse>(departmentResponse, HttpStatus.OK);
		}
		catch (APIException exception) {
			throw new APIException(exception.getMessage());
		}
	}
}
