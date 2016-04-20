package com.mpe.basic.action;

import org.hibernate.criterion.Restrictions;

import com.mpe.basic.model.User;
import com.mpe.basic.model.UserSecugen;
import com.mpe.basic.model.dao.BasicDAOFactory;
import com.mpe.basic.model.dao.BasicDAOFactoryHibernate;
import com.mpe.common.action.BaseAction;
import com.mpe.common.util.Constants;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class UserSecugenAction extends BaseAction implements Preparable, ModelDriven<UserSecugen> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	BasicDAOFactory basicDAOFactory = BasicDAOFactory.instance(BasicDAOFactoryHibernate.class);
	
	UserSecugen userSecugen;
	long userId;
	String userName;
	boolean active = false;

	@Override
	public UserSecugen getModel() {
		return userSecugen;
	}

	@Override
	public void prepare() throws Exception {
		try {
			if (userId > 0) {
				User user = basicDAOFactory.getUserDAO().findById(userId);
				if (user!=null) {
					userName = user.getUserName();
					active = user.isActive();
				}
				userSecugen = basicDAOFactory.getUserSecugenDAO().findByCriteria(Restrictions.eq("userId", userId));
				if (userSecugen==null) userSecugen = new UserSecugen();  
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
		setMappedRequest(Constants.SAVE);
		setPreviousPage(Constants.ADD);
		return SUCCESS;
	}

	@Override
	public String save() {
		try {
			if (userSecugen.getUserId()==0) {
				basicDAOFactory.getUserSecugenDAO().save(userSecugen);
			} else {
				basicDAOFactory.getUserSecugenDAO().update(userSecugen);
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
			return INPUT;
		}
		setMappedRequestFullPath("UserAction_partialList.action");
		return Constants.REDIRECT;
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
		setMappedRequestFullPath("UserAction_partialList.action");
		return Constants.REDIRECT;
	}

	public UserSecugen getUserSecugen() {
		return userSecugen;
	}

	public void setUserSecugen(UserSecugen userSecugen) {
		this.userSecugen = userSecugen;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	

}
