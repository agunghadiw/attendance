/**
 * 
 */
package com.mpe.basic.action;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.mpe.basic.model.WorkOffDay;
import com.mpe.basic.model.dao.BasicDAOFactory;
import com.mpe.basic.model.dao.BasicDAOFactoryHibernate;
import com.mpe.basic.model.other.WorkOffDayType;
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
public class WorkOffDayAction extends BaseAction implements Preparable, ModelDriven<WorkOffDay> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	BasicDAOFactory basicDAOFactory = BasicDAOFactory.instance(BasicDAOFactoryHibernate.class);
	
	WorkOffDay workOffDay;
	List<WorkOffDay> workOffDayList;
	long workOffDayId;
	Vector<String> types = new Vector<String>();
	
	String eventNameX="";
	Date eventDateX;

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.ModelDriven#getModel()
	 */
	@Override
	public WorkOffDay getModel() {
		return workOffDay;
	}
	
	@Override
	public String cancel() {
		setMappedRequest(Constants.PARTIAL_LIST);
		return Constants.REDIRECT;
	}

	/* (non-Javadoc)
	 * @see com.opensymphony.xwork2.Preparable#prepare()
	 */
	@Override
	public void prepare() throws Exception {
		try {
			WorkOffDayType[] workOffDayTypes = WorkOffDayType.values();
			for (int i=0; i<workOffDayTypes.length; i++) {
				types.add(workOffDayTypes[i].name());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (workOffDayId==0) workOffDay = new WorkOffDay();
		else workOffDay = basicDAOFactory.getWorkOffDayDAO().findById(workOffDayId);
		
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
		try {
			PartialList<WorkOffDay> partialList = basicDAOFactory.getWorkOffDayDAO().findByCriteria(getStart(), getCount(), 
					getAscDesc().equalsIgnoreCase("asc")?Order.asc(getOrderBy().length()>0?getOrderBy():"workOffDayId"):Order.desc(getOrderBy().length()>0?getOrderBy():"workOffDayId"), 
					eventNameX.length()>0?Restrictions.eq("eventName", eventNameX):Restrictions.ne("workOffDayId", new Long(-1)),
					eventDateX!=null?Restrictions.eq("eventDate", eventDateX):Restrictions.ne("workOffDayId", new Long(-1))
			);
			workOffDayList = partialList.getList();
			setPager(Pager.generatePager(getStart(), getCount(), partialList.getTotal()));
			setPagerItem(Pager.generatePagerItem(getStart(), getCount(), partialList.getTotal()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		setMappedRequest(Constants.PARTIAL_LIST);
		return SUCCESS;
	}

	/* (non-Javadoc)
	 * @see com.mpe.common.action.BaseAction#add()
	 */
	@Override
	public String add() {
		setPreviousPage(Constants.ADD);
		setMappedRequest(Constants.SAVE);
		return SUCCESS;
	}

	/* (non-Javadoc)
	 * @see com.mpe.common.action.BaseAction#save()
	 */
	@Override
	public String save() {
		try {
			basicDAOFactory.getWorkOffDayDAO().save(workOffDay);
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
			return INPUT;
		}
		setMappedRequest(Constants.PARTIAL_LIST);
		return Constants.REDIRECT;
	}

	/* (non-Javadoc)
	 * @see com.mpe.common.action.BaseAction#edit()
	 */
	@Override
	public String edit() {
		setPreviousPage(Constants.EDIT);
		setMappedRequest(Constants.UPDATE);
		return SUCCESS;
	}

	/* (non-Javadoc)
	 * @see com.mpe.common.action.BaseAction#update()
	 */
	@Override
	public String update() {
		try {
			basicDAOFactory.getWorkOffDayDAO().update(workOffDay);
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
			return INPUT;
		}
		setMappedRequest(Constants.PARTIAL_LIST);
		return Constants.REDIRECT;
	}

	/* (non-Javadoc)
	 * @see com.mpe.common.action.BaseAction#detail()
	 */
	@Override
	public String detail() {
		setReadOnly(true);
		return SUCCESS;
	}

	/* (non-Javadoc)
	 * @see com.mpe.common.action.BaseAction#delete()
	 */
	@Override
	public String delete() {
		try {
			basicDAOFactory.getWorkOffDayDAO().delete(workOffDayId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setMappedRequest(Constants.PARTIAL_LIST);
		return Constants.REDIRECT;
	}

	public WorkOffDay getWorkOffDay() {
		return workOffDay;
	}

	public void setWorkOffDay(WorkOffDay workOffDay) {
		this.workOffDay = workOffDay;
	}

	public List<WorkOffDay> getWorkOffDayList() {
		return workOffDayList;
	}

	public void setWorkOffDayList(List<WorkOffDay> workOffDayList) {
		this.workOffDayList = workOffDayList;
	}

	public long getWorkOffDayId() {
		return workOffDayId;
	}

	public void setWorkOffDayId(long workOffDayId) {
		this.workOffDayId = workOffDayId;
	}

	public String getEventNameX() {
		return eventNameX;
	}

	public void setEventNameX(String eventNameX) {
		this.eventNameX = eventNameX;
	}

	public Date getEventDateX() {
		return eventDateX;
	}

	public void setEventDateX(Date eventDateX) {
		this.eventDateX = eventDateX;
	}

	public Vector<String> getTypes() {
		return types;
	}

	public void setTypes(Vector<String> types) {
		this.types = types;
	}
	
	

}
