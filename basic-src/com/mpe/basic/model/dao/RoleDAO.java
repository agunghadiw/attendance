package com.mpe.basic.model.dao;

import java.util.List;

import com.mpe.basic.model.Role;
import com.mpe.common.dao.GenericDAO;
import com.mpe.common.model.ComboBoxModel;
/**
 * @author Agung Hadiwaluyo
 *
 */
public interface RoleDAO extends GenericDAO<Role, Long> {
	
	Role findByRoleName(String name) throws Exception;
	public long findByName(String name) throws Exception;
	List<ComboBoxModel> comboBoxList(String orderBy, String ascDesc, Long userGroupId) throws Exception;

}
