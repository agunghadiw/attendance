package com.mpe.basic.model.dao;

import java.util.Date;
import java.util.List;

import com.mpe.basic.model.WorkOffDay;
import com.mpe.basic.model.other.WorkOffDayCalendar;
import com.mpe.common.dao.GenericDAO;
/**
 * @author Agung Hadiwaluyo
 *
 */
public interface WorkOffDayDAO extends GenericDAO<WorkOffDay, Long> {

	public boolean isWorkOffHoliday(Date date) throws Exception;
	//public boolean isHolidayOff(Date date, Session session) throws Exception;
	public boolean isWorkOnHoliday(Date date) throws Exception;
	//public boolean isHolidayOn(Date date, Session session) throws Exception;
	
	
	public List<WorkOffDayCalendar> eventByYear(int year) throws Exception;
	

}
