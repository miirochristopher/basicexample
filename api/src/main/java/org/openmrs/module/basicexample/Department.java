package org.openmrs.module.basicexample;

import org.openmrs.BaseOpenmrsData;
import org.openmrs.User;

import javax.persistence.*;

@Entity(name = "basicexample.Department")
@Table(name = "department")
public class Department extends BaseOpenmrsData {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
	private String departmentName;
	
	@Column(name = "location")
	private String location;
	
	@ManyToOne
	@JoinColumn(name = "owner")
	private User owner;
	
	@Column(name = "patient_safety_measures")
	private String patientSafetyMeasures;
	
	@Override
	public Integer getId() {
		return id;
	}
	
	@Override
	public void setId(Integer integer) {
	}
	
	@Override
	public String getUuid() {
		return super.getUuid();
	}
	
	@Override
	public void setUuid(String uuid) {
		super.setUuid(uuid);
	}
	
	public User getOwner() {
		return owner;
	}
	
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	public String getDepartmentName() {
		return departmentName;
	}
	
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getPatientSafetyMeasures() {
		return patientSafetyMeasures;
	}
	
	public void setPatientSafetyMeasures(String patientSafetyMeasures) {
		this.patientSafetyMeasures = patientSafetyMeasures;
	}
	
	@Override
	public String toString() {
		return "Department{" + "id=" + id + ", departmentName='" + departmentName + '\'' + ", location='" + location + '\''
		        + ", owner=" + owner + ", patientSafetyMeasures='" + patientSafetyMeasures + '\'' + '}';
	}
}
