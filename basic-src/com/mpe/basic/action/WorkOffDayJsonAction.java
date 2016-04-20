package com.mpe.basic.action;

import java.util.Calendar;
import java.util.Date;

import com.mpe.basic.model.dao.BasicDAOFactory;
import com.mpe.basic.model.dao.BasicDAOFactoryHibernate;
import com.mpe.common.action.BaseAction;
import com.opensymphony.xwork2.Preparable;

/**
 * @author agunghadiw
 * @create on Feb 11, 2015 4:45:16 PM
 */

public class WorkOffDayJsonAction extends BaseAction implements Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	BasicDAOFactory basicDAOFactory = BasicDAOFactory.instance(BasicDAOFactoryHibernate.class);
	
	// input
	long valDate = 0;
	int placementPeriod;
	
	// output
	long matDate = 0;

	@Override
	public void prepare() throws Exception {
	}
	
	
	/*public String calcMatDate() {
		try {
			Calendar a1 = Calendar.getInstance();
			a1.setTime(new Date(valDate));
			a1.add(Calendar.DATE, placementPeriod);
			
			Date date = basicDAOFactory.getWorkOffDayDAO().workingDay(a1.getTime(), true);
			matDate = date.getTime();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "calcMatDateJsonSuccess";
	}*/

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


	public long getValDate() {
		return valDate;
	}


	public void setValDate(long valDate) {
		this.valDate = valDate;
	}


	public int getPlacementPeriod() {
		return placementPeriod;
	}


	public void setPlacementPeriod(int placementPeriod) {
		this.placementPeriod = placementPeriod;
	}


	public long getMatDate() {
		return matDate;
	}


	public void setMatDate(long matDate) {
		this.matDate = matDate;
	}
	
	

}
