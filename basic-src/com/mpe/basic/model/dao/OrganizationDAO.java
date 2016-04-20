package com.mpe.basic.model.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.mpe.basic.model.Organization;
import com.mpe.common.dao.GenericDAO;
import com.mpe.common.model.ComboBoxModel;

/**
 * @author Agung Hadiwaluyo
 *
 */
public interface OrganizationDAO extends GenericDAO<Organization, Long> {
	
	public List<ComboBoxModel> list(long parentId) throws Exception;
	//public long getDefaultOrganizationId() throws Exception;
	
	public void updateSodDate(long organizationId, Date sodDate) throws Exception;
	public void updateSodDate(long organizationId, Date sodDate, Session session) throws Exception;
	public Date getSodDate(Long organizationId) throws Exception;

}
