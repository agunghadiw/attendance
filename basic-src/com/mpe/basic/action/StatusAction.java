package com.mpe.basic.action;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.mpe.basic.model.Status;
import com.mpe.basic.model.dao.BasicDAOFactory;
import com.mpe.basic.model.dao.BasicDAOFactoryHibernate;
import com.mpe.basic.model.other.StatusType;
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
public class StatusAction extends BaseAction implements Preparable, ModelDriven<Status> {
	
	private static final long serialVersionUID = 1L;
	
	BasicDAOFactory basicDAOFactory = BasicDAOFactory.instance(BasicDAOFactoryHibernate.class);
	
	List<Status> statusList;
	Status status;
	long statusId;
	Vector<String> statusTypeList = new Vector<String>();
	String nameX="", statusTypeX="";
	
	@Override
	public String cancel() {
		setMappedRequest(Constants.PARTIAL_LIST);
		return Constants.REDIRECT;
	}
	
	@Override
	public String detail() {
		setReadOnly(true);
		setMappedRequest(Constants.LIST);
		return SUCCESS;
	}
	
	@Override
	public String add() {
		setPreviousPage(Constants.ADD);
		setMappedRequest(Constants.SAVE);
		return SUCCESS;
	}
	
	/*@Validations(
			requiredStrings={
					@RequiredStringValidator(fieldName="code",type=ValidatorType.SIMPLE,trim=true,key="validation.code.required"),
					@RequiredStringValidator(fieldName="name",type=ValidatorType.SIMPLE,trim=true,key="validation.name.required")
			}
	)*/
	
	@Override
	public String save() {
		try {
			basicDAOFactory.getStatusDAO().save(status);
		} catch (Exception e) {
			addActionError(e.getMessage());
			return INPUT;
		}
		setMappedRequest(Constants.PARTIAL_LIST);
		return Constants.REDIRECT;
	}
	
	@Override
	public String edit() {
		setPreviousPage(Constants.EDIT);
		setMappedRequest(Constants.UPDATE);
		return SUCCESS;
	}
	
	@Override
	public String update() {
		try {
			basicDAOFactory.getStatusDAO().update(status);
		} catch (Exception e) {
			addActionError(e.getMessage());
			return INPUT;
		}
		setMappedRequest(Constants.PARTIAL_LIST);
		return Constants.REDIRECT;
	}
	
	@Override
	public String delete() {
		try {
			basicDAOFactory.getStatusDAO().delete(getStatusId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setMappedRequest(Constants.PARTIAL_LIST);
		return Constants.REDIRECT;
	}
	
	@Override
	public String list() {
		try {
			statusList = basicDAOFactory.getStatusDAO().findAll(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setMappedRequest(Constants.LIST);
		return SUCCESS;
	}
	
	@Override
	public String partialList() {
		try {
			PartialList<Status> partialList = basicDAOFactory.getStatusDAO().findByCriteria(getStart(), getCount(), getAscDesc().equalsIgnoreCase("asc")?Order.asc(getOrderBy().length()>0?getOrderBy():"statusId"):Order.desc(getOrderBy().length()>0?getOrderBy():"statusId"), 
					nameX.length()>0?Restrictions.like("name", nameX,MatchMode.ANYWHERE):Restrictions.ne("statusId", new Long(-1)), 
					statusTypeX.length()>0?Restrictions.eq("type", StatusType.valueOf(statusTypeX)):Restrictions.isNotNull("type"));
			statusList = partialList.getList();
			setPager(Pager.generatePager(getStart(), getCount(), partialList.getTotal()));
			setPagerItem(Pager.generatePagerItem(getStart(), getCount(), partialList.getTotal()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		setMappedRequest(Constants.PARTIAL_LIST);
		return SUCCESS;
	}

	@Override
	public Status getModel() {
		return status;
	}

	@Override
	public void prepare() throws Exception {
		try {
			if (statusTypeList.size()==0) {
				StatusType[] types = StatusType.values();
				for (int i=0; i<types.length; i++) {
					statusTypeList.add(types[i].name());
				}
			}
		} catch (Exception e) {
		}
		if (getStatusId()==0) {
			status = new Status();
		} else {
			status = (Status) basicDAOFactory.getStatusDAO().findById(getStatusId());
		}
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public long getStatusId() {
		return statusId;
	}

	public void setStatusId(long statusId) {
		this.statusId = statusId;
	}

	public List<Status> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<Status> statusList) {
		this.statusList = statusList;
	}

	public Vector<String> getStatusTypeList() {
		return statusTypeList;
	}

	public void setStatusTypeList(Vector<String> statusTypeList) {
		this.statusTypeList = statusTypeList;
	}

	public String getNameX() {
		return nameX;
	}

	public void setNameX(String nameX) {
		this.nameX = nameX;
	}

	public String getStatusTypeX() {
		return statusTypeX;
	}

	public void setStatusTypeX(String statusTypeX) {
		this.statusTypeX = statusTypeX;
	}
	
	

}
