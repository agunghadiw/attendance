package com.mpe.basic.model.dao;


import java.util.List;

import com.mpe.basic.model.BranchType;
import com.mpe.basic.model.other.BranchTypeList;
import com.mpe.common.dao.GenericDAO;
import com.mpe.common.model.ComboBoxModel;
import com.mpe.common.util.PartialList;
/**
 * @author Agung Hadiwaluyo
 *
 */
public interface BranchTypeDAO extends GenericDAO<BranchType, Long> {
	
	public PartialList<BranchTypeList> findAll(int start, int count) throws Exception;
	public List<ComboBoxModel> comboBoxList(String orderBy, String ascDesc, Long parentId, String name) throws Exception;
	public Long previousBranchType(long branchTypeId) throws Exception;

}
