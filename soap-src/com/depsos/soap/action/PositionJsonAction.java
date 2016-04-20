
package com.depsos.soap.action;

import java.util.LinkedList;
import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.json.annotations.JSON;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.depsos.hr.model.Degree;
import com.depsos.hr.model.Position;
import com.depsos.hr.model.dao.HrDAOFactory;
import com.depsos.hr.model.dao.HrDAOFactoryHibernate;
import com.mpe.common.action.BaseAction;
import com.mpe.common.util.Constants;

/**
 * @author Agung Hadiwaluyo
 *
 */

public class PositionJsonAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	HrDAOFactory hrDAOFactory = HrDAOFactory.instance(HrDAOFactoryHibernate.class);
	
	List<Degree> degrees = new LinkedList<Degree>();
	
	Long positionId;
	
	@JSON(name="degreeByPosition")
	@SkipValidation
	public String degreeByPosition() {
		Session session = null;
		try {
			//
			session = hrDAOFactory.getPositionDAO().getSession(Constants.HIBERNATE_CFG_KEY_1);
			if (positionId!=null) {
				//Position position = hrDAOFactory.getPositionDAO().
				Position position = (Position)session.createCriteria(Position.class)
						.add(Restrictions.eq("positionId", positionId))
						.setFetchMode("degrees", FetchMode.JOIN)
						.uniqueResult();
				//
				if (position!=null) {
					if (position.getDegrees()!=null) degrees.addAll(position.getDegrees());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session!=null) session.close();
		}
		return "degreeByPositionSuccess";
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

	public Long getPositionId() {
		return positionId;
	}

	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}

	public List<Degree> getDegrees() {
		return degrees;
	}

	public void setDegrees(List<Degree> degrees) {
		this.degrees = degrees;
	}
	
	
	
	

}
