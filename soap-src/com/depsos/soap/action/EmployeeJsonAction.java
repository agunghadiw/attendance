/**
 * 
 */
package com.depsos.soap.action;

import com.depsos.hr.model.dao.HrDAOFactory;
import com.depsos.hr.model.dao.HrDAOFactoryHibernate;
import com.mpe.common.action.BaseAction;

/**
 * @author Mas AH
 * @create on May 19, 2015 11:55:13 AM
 */
public class EmployeeJsonAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	HrDAOFactory hrDAOFactory = HrDAOFactory.instance(HrDAOFactoryHibernate.class);
	
	
	String fullName;
	String userName;
	
	
	public String findByFullName() {
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "findByFullNameSuccess";
	}

	/* (non-Javadoc)
	 * @see com.mpe.common.action.BaseAction#list()
	 */
	@Override
	public String list() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mpe.common.action.BaseAction#partialList()
	 */
	@Override
	public String partialList() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mpe.common.action.BaseAction#add()
	 */
	@Override
	public String add() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mpe.common.action.BaseAction#save()
	 */
	@Override
	public String save() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mpe.common.action.BaseAction#edit()
	 */
	@Override
	public String edit() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mpe.common.action.BaseAction#update()
	 */
	@Override
	public String update() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mpe.common.action.BaseAction#detail()
	 */
	@Override
	public String detail() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mpe.common.action.BaseAction#delete()
	 */
	@Override
	public String delete() {
		return null;
	}

	/* (non-Javadoc)
	 * @see com.mpe.common.action.BaseAction#cancel()
	 */
	@Override
	public String cancel() {
		return null;
	}

}
