package com.mpe.basic.action;

import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.mpe.basic.model.BranchType;
import com.mpe.basic.model.dao.BasicDAOFactory;
import com.mpe.basic.model.dao.BasicDAOFactoryHibernate;
import com.mpe.basic.model.other.BranchTypeList;
import com.mpe.common.action.BaseAction;
import com.mpe.common.util.Constants;
import com.mpe.common.util.Pager;
import com.mpe.common.util.PartialList;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class BranchTypeAction extends BaseAction implements Preparable, ModelDriven<BranchType> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	BasicDAOFactory basicDAOFactory = BasicDAOFactory.instance(BasicDAOFactoryHibernate.class);
	
	BranchType branchType;
	List<BranchTypeList> branchTypes;
	List<BranchType> parentList;
	long branchTypeId;

	@Override
	public BranchType getModel() {
		return branchType;
	}

	@Override
	public void prepare() throws Exception {
		try {
			parentList = basicDAOFactory.getBranchTypeDAO().findByCriteria(Order.asc("name"), Restrictions.ne("branchTypeId", branchTypeId));
		} catch (Exception e) {}
		if (branchTypeId==0) branchType =  new BranchType();
		else branchType =  basicDAOFactory.getBranchTypeDAO().findById(branchTypeId);
		
	}
	
	@Override
	public String cancel() {
		setMappedRequest(Constants.PARTIAL_LIST);
		return Constants.REDIRECT;
	}

	@Override
	public String list() {
		return null;
	}

	@Override
	public String partialList() {
		try {
			PartialList<BranchTypeList> partialList = basicDAOFactory.getBranchTypeDAO().findAll(getStart(), getCount());
			branchTypes = partialList.getList();
			setPager(Pager.generatePager(getStart(), getCount(), partialList.getTotal()));
			setPagerItem(Pager.generatePagerItem(getStart(), getCount(), partialList.getTotal()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		setMappedRequest(Constants.PARTIAL_LIST);
		return SUCCESS;
	}

	@Override
	public String add() {
		setMappedRequest(Constants.SAVE);
		setPreviousPage(Constants.ADD);
		return SUCCESS;
	}

	@Override
	public String save() {
		try {
			basicDAOFactory.getBranchTypeDAO().save(branchType);
		} catch (Exception e) {
			addActionError(e.getMessage());
			return INPUT;
		}
		setMappedRequest(Constants.PARTIAL_LIST);
		return Constants.REDIRECT;
	}

	@Override
	public String edit() {
		setMappedRequest(Constants.UPDATE);
		setPreviousPage(Constants.EDIT);
		return SUCCESS;
	}

	@Override
	public String update() {
		try {
			basicDAOFactory.getBranchTypeDAO().update(branchType);
		} catch (Exception e) {
			addActionError(e.getMessage());
			return INPUT;
		}
		setMappedRequest(Constants.PARTIAL_LIST);
		return Constants.REDIRECT;
	}

	@Override
	public String detail() {
		setReadOnly(true);
		return SUCCESS;
	}

	@Override
	public String delete() {
		try {
			basicDAOFactory.getBranchTypeDAO().delete(branchTypeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setMappedRequest(Constants.PARTIAL_LIST);
		return Constants.REDIRECT;
	}

	public BranchType getBranchType() {
		return branchType;
	}

	public void setBranchType(BranchType branchType) {
		this.branchType = branchType;
	}

	public long getBranchTypeId() {
		return branchTypeId;
	}

	public void setBranchTypeId(long branchTypeId) {
		this.branchTypeId = branchTypeId;
	}

	public List<BranchTypeList> getBranchTypes() {
		return branchTypes;
	}

	public void setBranchTypes(List<BranchTypeList> branchTypes) {
		this.branchTypes = branchTypes;
	}

	public List<BranchType> getParentList() {
		return parentList;
	}

	public void setParentList(List<BranchType> parentList) {
		this.parentList = parentList;
	}
	
	

}
