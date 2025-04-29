/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.basicexample.api.impl;

import org.openmrs.api.APIException;
import org.openmrs.api.UserService;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.basicexample.Department;
import org.openmrs.module.basicexample.Item;
import org.openmrs.module.basicexample.api.BasicexampleService;
import org.openmrs.module.basicexample.api.dao.BasicexampleDao;
import org.openmrs.util.PrivilegeConstants;
import org.springframework.transaction.annotation.Transactional;

public class BasicexampleServiceImpl extends BaseOpenmrsService implements BasicexampleService {
	
	BasicexampleDao dao;
	
	UserService userService;
	
	/**
	 * Injected in moduleApplicationContext.xml
	 */
	public void setDao(BasicexampleDao dao) {
		this.dao = dao;
	}
	
	/**
	 * Injected in moduleApplicationContext.xml
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public Item getItemByUuid(String uuid) throws APIException {
		return dao.getItemByUuid(uuid);
	}
	
	@Override
	public Item saveItem(Item item) throws APIException {
		if (item.getOwner() == null) {
			item.setOwner(userService.getUser(1));
		}
		
		return dao.saveItem(item);
	}
	
	@Override
	public Department getDepartmentByUuid(String uuid) throws APIException {
		return dao.getDepartmentByUuid(uuid);
	}
	
	@Override
	public Department getDepartmentById(Integer id) throws APIException {
		return dao.getDepartmentById(id);
	}
	
	@Override
	public Department getLengthOfStayById(Integer id) throws APIException {
		return dao.getLengthOfStayById(id);
	}
	
	@Override
	public Department saveDepartment(Department department) throws APIException {
		return dao.saveDepartment(department);
	}
	
	@Override
	public String getPatientSafetyMeasuresByDepartmentId(Integer id) {
		return dao.getPatientSafetyMeasuresByDepartmentId(id);
	}
	
	@Override
	public String getPatientSafetyByDepartmentId(Integer id) throws APIException {
		Department department = dao.getDepartmentById(id);
		if (department != null) {
			return department.getPatientSafety();
		} else {
			return "";
		}
	}
	
	@Override
	public String updatePatientSafetyMeasuresByDepartmentId(Integer id, String patientSafetyMeasures) throws APIException {
		return dao.updatePatientSafetyMeasuresByDepartmentId(id, patientSafetyMeasures);
	}
}
