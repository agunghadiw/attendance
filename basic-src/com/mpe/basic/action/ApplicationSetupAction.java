package com.mpe.basic.action;

import java.util.List;
import java.util.Vector;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.mpe.basic.model.ApplicationSetup;
import com.mpe.basic.model.Role;
import com.mpe.basic.model.dao.BasicDAOFactory;
import com.mpe.basic.model.dao.BasicDAOFactoryHibernate;
import com.mpe.common.action.BaseAction;
import com.mpe.common.util.CommonUtil;
import com.mpe.common.util.Constants;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class ApplicationSetupAction extends BaseAction implements Preparable, ModelDriven<ApplicationSetup> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	BasicDAOFactory basicDAOFactory = BasicDAOFactory.instance(BasicDAOFactoryHibernate.class);
	
	ApplicationSetup applicationSetup;
	long applicationSetupId;
	List<Role> userRoles;
	
	Vector<String> months;

	@Override
	public ApplicationSetup getModel() {
		return applicationSetup;
	}

	@Override
	public void prepare() throws Exception {
		if (months==null) months = CommonUtil.monthList();
		if (applicationSetupId==0) {
			applicationSetup = basicDAOFactory.getApplicationSetupDAO().findByCriteria(Restrictions.ne("applicationSetupId", new Long(-1)));
		} else {
			applicationSetup = basicDAOFactory.getApplicationSetupDAO().findById(applicationSetupId);
		}
		if (userRoles==null) {
			userRoles = basicDAOFactory.getRoleDAO().findAll(Order.asc("roleName"));
		}
		if (applicationSetup!=null) applicationSetupId = applicationSetup.getApplicationSetupId();
		else applicationSetup = new ApplicationSetup();
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
		setMappedRequest(Constants.SAVE);
		setPreviousPage(Constants.ADD);
		return SUCCESS;
	}

	@Override
	public String save() {
		try {
			if (applicationSetupId==0) basicDAOFactory.getApplicationSetupDAO().save(applicationSetup);
			else basicDAOFactory.getApplicationSetupDAO().update(applicationSetup);
		} catch (Exception e) {
			addActionError(e.getMessage());
			return INPUT;
		}
		return SUCCESS;
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
		setMappedRequest(Constants.PARTIAL_LIST);
		return Constants.REDIRECT;
	}

	public ApplicationSetup getApplicationSetup() {
		return applicationSetup;
	}

	public void setApplicationSetup(ApplicationSetup applicationSetup) {
		this.applicationSetup = applicationSetup;
	}

	public long getApplicationSetupId() {
		return applicationSetupId;
	}

	public void setApplicationSetupId(long applicationSetupId) {
		this.applicationSetupId = applicationSetupId;
	}

	public List<Role> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<Role> userRoles) {
		this.userRoles = userRoles;
	}

	public Vector<String> getMonths() {
		return months;
	}

	public void setMonths(Vector<String> months) {
		this.months = months;
	}

	
	
	
	
	

}
