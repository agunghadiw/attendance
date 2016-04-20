package com.mpe.basic.model.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.mpe.basic.model.UserActivity;
import com.mpe.basic.model.other.UserActivityCalendar;
import com.mpe.common.dao.GenericDAO;
import com.mpe.common.util.PartialList;
/**
 * @author Agung Hadiwaluyo
 *
 */
public interface UserActivityDAO extends GenericDAO<UserActivity, Long> {
	
	public void insertActivity(long userId, String userName, String viewName, String ip) throws Exception;
	public PartialList<UserActivityCalendar> userCalendar(int start,int count, int month, int year, String userName, long organizationId, boolean customer, String customerCompany) throws Exception;
	public List<UserActivityCalendar> userCalendar(int month, int year, String userName, long organizationId, boolean customer, String customerCompany) throws Exception;

	public int[] getWorkOffDayInMonth(Date date, Session session) throws Exception;
	public int[] getUserActivityInMonth(Date date, long userId, Session session) throws Exception;
	
}
