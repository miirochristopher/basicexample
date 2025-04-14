package org.openmrs.module.basicexample;

import org.openmrs.BaseOpenmrsData;

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
	
	@Column(name = "length_of_stay")
	private String lengthOfStay;
	
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
	
	public String getLengthOfStay() {
		return lengthOfStay;
	}
	
	public void setLengthOfStay(String lengthOfStay) {
		this.lengthOfStay = lengthOfStay;
	}
	
	@Override
	public String toString() {
		return "Department{" + "id=" + id + ", departmentName='" + departmentName + '\'' + ", location='" + location + '\''
		        + ", lengthOfStay='" + lengthOfStay + '\'' + '}';
	}
}
