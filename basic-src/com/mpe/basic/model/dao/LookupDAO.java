package com.mpe.basic.model.dao;

import java.util.List;

import org.hibernate.Session;

import com.mpe.basic.model.Lookup;
import com.mpe.basic.model.other.LookupCategory;
import com.mpe.common.dao.GenericDAO;
import com.mpe.common.model.ComboBoxModel;

/**
 * @author Agung Hadiwaluyo
 *
 */
public interface LookupDAO extends GenericDAO<Lookup, Long> {
	
	List<Lookup> findByCategory(LookupCategory category) throws Exception;
	List<Lookup> findByCategory(LookupCategory category, Session session) throws Exception;
	List<ComboBoxModel> comboBoxList(String orderBy, String ascDesc, LookupCategory category) throws Exception;

}
