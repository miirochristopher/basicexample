/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.basicexample.api.dao;

import org.hibernate.criterion.Restrictions;
import org.openmrs.api.db.hibernate.DbSession;
import org.openmrs.api.db.hibernate.DbSessionFactory;
import org.openmrs.module.basicexample.Department;
import org.openmrs.module.basicexample.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("basicexample.BasicexampleDao")
public class BasicexampleDao {
	
	@Autowired
	DbSessionFactory sessionFactory;
	
	private DbSession getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public Item getItemByUuid(String uuid) {
		return (Item) getSession().createCriteria(Item.class).add(Restrictions.eq("uuid", uuid)).uniqueResult();
	}
	
	public Item saveItem(Item item) {
		getSession().saveOrUpdate(item);
		return item;
	}
	
	public Department getDepartmentById(Integer id) {
		return (Department) getSession().createCriteria(Department.class).add(Restrictions.eq("id", id)).uniqueResult();
	}
	
	public Department getLengthOfStayById(Integer id) {
		return (Department) getSession().createCriteria(Department.class).add(Restrictions.eq("id", id)).uniqueResult();
	}
	
	public Department getDepartmentByUuid(String uuid) {
		return (Department) getSession().createCriteria(Department.class).add(Restrictions.eq("uuid", uuid)).uniqueResult();
	}
	
	public Department saveDepartment(Department department) {
		getSession().saveOrUpdate(department);
		return department;
	}
	
	public String getPatientSafetyMeasuresByDepartmentId(Integer id) {
		Department department = getDepartmentById(id);
		if (department != null) {
			return department.getPatientSafetyMeasures();
		}
		return null;
	}
	
	public String getPatientSafetyByDepartmentId(Integer id) {
		Department department = getDepartmentById(id);
		if (department != null) {
			return department.getPatientSafety();
		}
		return "";
	}
	
	public String updatePatientSafetyMeasuresByDepartmentId(Integer id, String patientSafetyMeasures) {
		Department department = getDepartmentById(id);
		if (department != null) {
			department.setPatientSafetyMeasures(patientSafetyMeasures);
			getSession().saveOrUpdate(department);
			return department.getPatientSafetyMeasures();
		}
		return null;
	}
}
