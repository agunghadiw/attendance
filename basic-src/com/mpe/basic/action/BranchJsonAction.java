package com.mpe.basic.action;

import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;

import com.mpe.basic.model.dao.BasicDAOFactory;
import com.mpe.basic.model.dao.BasicDAOFactoryHibernate;
import com.mpe.common.action.BaseAction;
import com.mpe.common.model.ComboBoxModel;
import com.opensymphony.xwork2.Preparable;

public class BranchJsonAction extends BaseAction implements Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	BasicDAOFactory basicDAOFactory = BasicDAOFactory.instance(BasicDAOFactoryHibernate.class);
	
	Long branchTypeId;
	List<ComboBoxModel> branchParents;
	List<ComboBoxModel> branchTypes;

	@SkipValidation
	public String jsonBranchType() {
		return "jsonBranchTypeSuccess";
	}
	
	@Override
	public void prepare() throws Exception {
		try {
			// get previous branch-type of current-branch_type
			if (branchTypes==null) branchTypes = basicDAOFactory.getBranchTypeDAO().comboBoxList("parent_id", "asc", null, null);
			//BranchType branchType = addressDAOFactory.getBranchTypeDAO().findById(branchTypeId);
			if (branchTypeId!=null && branchTypeId.longValue() > 0) {
				Long previousBranchTypeId = basicDAOFactory.getBranchTypeDAO().previousBranchType(branchTypeId);
				if (previousBranchTypeId!=null && previousBranchTypeId.longValue()>0) {
					branchParents = basicDAOFactory.getBranchDAO().comboBoxList("b.name", "asc", null, previousBranchTypeId, "", "", "");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public String list() {
		return null;
	}

	@Override
	public String partialList() {
		return null;
	}

	@Override
	public String add() {
		return null;
	}

	@Override
	public String save() {
		return null;
	}

	@Override
	public String edit() {
		return null;
	}

	@Override
	public String update() {
		return null;
	}

	@Override
	public String detail() {
		return null;
	}

	@Override
	public String delete() {
		return null;
	}

	@Override
	public String cancel() {
		return null;
	}

	public Long getBranchTypeId() {
		return branchTypeId;
	}

	public void setBranchTypeId(Long branchTypeId) {
		this.branchTypeId = branchTypeId;
	}

	public List<ComboBoxModel> getBranchParents() {
		return branchParents;
	}

	public void setBranchParents(List<ComboBoxModel> branchParents) {
		this.branchParents = branchParents;
	}

	public List<ComboBoxModel> getBranchTypes() {
		return branchTypes;
	}

	public void setBranchTypes(List<ComboBoxModel> branchTypes) {
		this.branchTypes = branchTypes;
	}
	
	

}
