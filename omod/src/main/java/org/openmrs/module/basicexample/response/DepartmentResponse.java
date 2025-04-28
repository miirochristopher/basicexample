package org.openmrs.module.basicexample.response;

import lombok.Data;

import java.util.Date;

@Data
public class DepartmentResponse {
	
	String departmentName;
	
	String location;
	
	Date dateCreated;
	
	String patientSafetyMeasures;
	
	String lengthOfStay;
	
	String patientSafety;
	
}
