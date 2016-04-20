package com.mpe.basic.action;

import java.util.List;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.mpe.basic.model.UserActivity;
import com.mpe.basic.model.dao.BasicDAOFactory;
import com.mpe.basic.model.dao.BasicDAOFactoryHibernate;
import com.mpe.common.action.BaseAction;
import com.mpe.common.util.Constants;
import com.mpe.common.util.Pager;
import com.mpe.common.util.PartialList;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
/**
 * @author Agung Hadiwaluyo
 *
 */
public class UserActivityAction extends BaseAction implements Preparable, ModelDriven<UserActivity> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	BasicDAOFactory basicDAOFactory = BasicDAOFactory.instance(BasicDAOFactoryHibernate.class);
	
	UserActivity userActivity;
	List<UserActivity> userActivityList;
	String userNameX="",viewNameX="";
	
	@Override
	public String cancel() {
		setMappedRequest(Constants.PARTIAL_LIST);
		return Constants.REDIRECT;
	}

	@Override
	public String add() {
		return null;
	}

	@Override
	public String delete() {
		return null;
	}

	@Override
	public String detail() {
		return null;
	}

	@Override
	public String edit() {
		return null;
	}

	@Override
	public String list() {
		return null;
	}

	@Override
	public String partialList() {
		try {
			PartialList<UserActivity> partialList = basicDAOFactory.getUserActivityDAO().findByCriteria(getStart(), getCount(), 
					getAscDesc().equalsIgnoreCase("asc")?Order.asc(getOrderBy().length()>0?getOrderBy():"activityDate"):Order.desc(getOrderBy().length()>0?getOrderBy():"activityDate"), 
					userNameX.length()>0?Restrictions.ilike("userName", userNameX, MatchMode.ANYWHERE):Restrictions.ne("userActivityId", new Long(-1)), 
					viewNameX.length()>0?Restrictions.ilike("activityName", viewNameX, MatchMode.ANYWHERE):Restrictions.ne("userActivityId", new Long(-1))
					);
			
			userActivityList = partialList.getList();
			setPager(Pager.generatePager(getStart(), getCount(), partialList.getTotal()));
			setPagerItem(Pager.generatePagerItem(getStart(), getCount(), partialList.getTotal()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		setMappedRequest(Constants.PARTIAL_LIST);
		return SUCCESS;
	}

	@Override
	public String save() {
		return null;
	}

	@Override
	public String update() {
		return null;
	}

	@Override
	public void prepare() throws Exception {
		
	}

	@Override
	public UserActivity getModel() {
		return userActivity;
	}

	public UserActivity getUserActivity() {
		return userActivity;
	}

	public void setUserActivity(UserActivity userActivity) {
		this.userActivity = userActivity;
	}

	public List<UserActivity> getUserActivityList() {
		return userActivityList;
	}

	public void setUserActivityList(List<UserActivity> userActivityList) {
		this.userActivityList = userActivityList;
	}

	public String getUserNameX() {
		return userNameX;
	}

	public void setUserNameX(String userNameX) {
		this.userNameX = userNameX;
	}

	public String getViewNameX() {
		return viewNameX;
	}

	public void setViewNameX(String viewNameX) {
		this.viewNameX = viewNameX;
	}
	
	

}
