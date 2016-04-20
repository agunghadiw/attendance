package com.mpe.basic.action;

import java.util.List;

import org.hibernate.criterion.Order;

import com.mpe.basic.model.BranchClass;
import com.mpe.basic.model.dao.BasicDAOFactory;
import com.mpe.basic.model.dao.BasicDAOFactoryHibernate;
import com.mpe.common.action.BaseAction;
import com.mpe.common.util.Constants;
import com.mpe.common.util.Pager;
import com.mpe.common.util.PartialList;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class BranchClassAction extends BaseAction implements Preparable, ModelDriven<BranchClass> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	BasicDAOFactory basicDAOFactory = BasicDAOFactory.instance(BasicDAOFactoryHibernate.class);
	
	long branchClassId;
	BranchClass branchClass;
	List<BranchClass> branchClasses;
	

	@Override
	public BranchClass getModel() {
		return branchClass;
	}

	@Override
	public void prepare() throws Exception {
		if (branchClassId==0) branchClass = new BranchClass();
		else branchClass = basicDAOFactory.getBranchClassDAO().findById(branchClassId);
		
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
			PartialList<BranchClass> partialList = basicDAOFactory.getBranchClassDAO().findByCriteria(getStart(), getCount(), 
					Order.asc("code"));
			branchClasses = partialList.getList();
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
			basicDAOFactory.getBranchClassDAO().save(branchClass);
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
			basicDAOFactory.getBranchClassDAO().update(branchClass);
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
			basicDAOFactory.getBranchClassDAO().delete(branchClass);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setMappedRequest(Constants.PARTIAL_LIST);
		return Constants.REDIRECT;
	}

	public long getBranchClassId() {
		return branchClassId;
	}

	public void setBranchClassId(long branchClassId) {
		this.branchClassId = branchClassId;
	}

	public BranchClass getBranchClass() {
		return branchClass;
	}

	public void setBranchClass(BranchClass branchClass) {
		this.branchClass = branchClass;
	}

	public List<BranchClass> getBranchClasses() {
		return branchClasses;
	}

	public void setBranchClasses(List<BranchClass> branchClasses) {
		this.branchClasses = branchClasses;
	}
	
	

}
