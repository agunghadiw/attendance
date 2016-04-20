package com.mpe.basic.model.dao;

import java.util.List;

import com.mpe.basic.model.Status;
import com.mpe.basic.model.other.StatusType;
import com.mpe.common.dao.GenericDAO;
import com.mpe.common.model.ComboBoxModel;
/**
 * @author Agung Hadiwaluyo
 *
 */
public interface StatusDAO extends GenericDAO<Status, Long> {
	
	List<Status> findByType(StatusType type)throws Exception;
	List<ComboBoxModel> comboBoxList(String orderBy, String ascDesc, StatusType type)throws Exception;
	List<ComboBoxModel> comboBoxListID(String orderBy, String ascDesc, StatusType type, Long statusId)throws Exception;
	List<ComboBoxModel> comboBoxListID(String orderBy, String ascDesc, StatusType type, Long[] statusId)throws Exception;

}
