package com.mpe.basic.model.dao;


import java.util.List;

import com.mpe.basic.model.Branch;
import com.mpe.basic.model.other.BranchList;
import com.mpe.common.dao.GenericDAO;
import com.mpe.common.model.ComboBoxModel;
import com.mpe.common.model.TreeNode;
import com.mpe.common.util.PartialList;

/**
 * @author Agung Hadiwaluyo
 *
 */
public interface BranchDAO extends GenericDAO<Branch, Long> {
	
	public PartialList<BranchList> findAll(int start, int count, String orderBy, String ascDesc, 
			Long branchClassId, Long branchTypeId, String code, String name, String city) throws Exception;
	
	public List<ComboBoxModel> comboBoxList(String orderBy, String ascDesc, 
			Long branchClassId, Long branchTypeId, String code, String name, String city) throws Exception;
	
	public List<TreeNode> showTree(long organizationId) throws Exception;
	
	public void deleteAllBranch() throws Exception;

}
