package org.openmrs.module.basicexample;

import org.openmrs.BaseOpenmrsData;

import javax.persistence.*;

@Entity(name = "basicexample.Doctor")
@Table(name = "doctors")
public class Doctor extends BaseOpenmrsData {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "department")
	private String department;
	
	@Column(name = "contact")
	private String contact;
	
	@Override
	public Integer getId() {
		return 0;
	}
	
	@Override
	public void setId(Integer integer) {
		
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getContact() {
		return contact;
	}
	
	public void setContact(String contact) {
		this.contact = contact;
	}
	
	@Override
	public String toString() {
		return "Doctor{" + "id=" + id + ", name='" + name + '\'' + ", department='" + department + '\'' + ", contact='"
		        + contact + '\'' + '}';
	}
}
