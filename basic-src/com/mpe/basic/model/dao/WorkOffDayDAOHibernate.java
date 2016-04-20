package com.mpe.basic.model.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.BooleanType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;

import com.depsos.hr.model.other.AttendanceMachineList;
import com.mpe.basic.model.WorkOffDay;
import com.mpe.basic.model.other.WorkOffDayCalendar;
import com.mpe.common.dao.GenericHibernateDAO;

/**
 * @author Agung Hadiwaluyo
 *
 */
public class WorkOffDayDAOHibernate extends GenericHibernateDAO<WorkOffDay, Long> implements WorkOffDayDAO {


	@Override
	public boolean isWorkOffHoliday(Date date) throws Exception {
		Session session = getSessionAnnotated();
		try {
			String sql = "select count(work_off_day_id) as A from work_off_day where event_date =:date and work_off_day_type = 'OFF' ";
			int i = (Integer)session.createSQLQuery(sql).addScalar("A", IntegerType.INSTANCE)
					.setDate("date", date)
					.uniqueResult();
			if (i>0) { 
				return true;
			} /*else {
				// saturday & sunday!
				Calendar c = Calendar.getInstance();
				c.setTime(date);
				if (c.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY || c.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY) return true;
			}*/
		} catch (Exception e) {
			throw e;
		} finally {
			if (session!=null) session.close();
		}
		return false;
	}

	/*@Override
	public boolean isHolidayOff(Date date, Session session) throws Exception {
		try {
			String sql = "select count(work_off_day_id) as A from work_off_day where event_date =:date and work_off_day_type = 'OFF' ";
			int i = (Integer)session.createSQLQuery(sql).addScalar("A", IntegerType.INSTANCE)
					.setDate("date", date)
					.uniqueResult();
			if (i>0) return true;
		} catch (Exception e) {
			throw e;
		} finally {
		}
		return false;
	}*/
	
	@Override
	public boolean isWorkOnHoliday(Date date) throws Exception {
		Session session = getSessionAnnotated();
		try {
			String sql = "select count(work_off_day_id) as A from work_off_day where event_date =:date and work_off_day_type = 'ON' ";
			int i = (Integer)session.createSQLQuery(sql).addScalar("A", IntegerType.INSTANCE)
					.setDate("date", date)
					.uniqueResult();
			if (i>0) return true;
		} catch (Exception e) {
			throw e;
		} finally {
			if (session!=null) session.close();
		}
		return false;
	}

	@Override
	public List<WorkOffDayCalendar> eventByYear(int year) throws Exception {
		Session session = getSessionAnnotated();
		List<WorkOffDayCalendar> workOffDayCalendars = new LinkedList<>();
		try {
			String sql = ""
					+ "select "
					+ "event_name as event, "
					+ "date_part('month', event_date) as month, "
					+ "date_part('day', event_date) as day, "
					+ "date_part('year', event_date) as year "
					+ "from work_off_day "
					+ "where date_part('year', event_date) = :curYear "
					+ ""
					+ "";
			SQLQuery query = session.createSQLQuery(sql)
					.addScalar("event", StringType.INSTANCE)//0
					.addScalar("day", IntegerType.INSTANCE)//0
					.addScalar("month", IntegerType.INSTANCE)//0
					.addScalar("year", IntegerType.INSTANCE);
			
			query.setInteger("curYear", year);
			
			workOffDayCalendars = query
					.setResultTransformer(Transformers.aliasToBean(WorkOffDayCalendar.class))
					.list();
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (session!=null) session.close();
		}
		return workOffDayCalendars;
	}

	/*@Override
	public boolean isHolidayOn(Date date, Session session) throws Exception {
		try {
			String sql = "select count(work_off_day_id) as A from work_off_day where event_date =:date and work_off_day_type = 'ON' ";
			int i = (Integer)session.createSQLQuery(sql).addScalar("A", IntegerType.INSTANCE)
					.setDate("date", date)
					.uniqueResult();
			if (i>0) return true;
		} catch (Exception e) {
			throw e;
		} finally {
		}
		return false;
	}*/
	
	/*@Override
	public Date workingDay(Date date, boolean next) throws Exception {
		Session session = getSessionAnnotated();
		Calendar c = null;
		String sql = "select count(work_off_day_id) as A from work_off_day where event_date =:date ";
		try {
			c = Calendar.getInstance();
			c.setTime(date);
			//
			boolean b = false;
			while (!b) {
				int i = (Integer)session.createSQLQuery(sql).addScalar("A", IntegerType.INSTANCE)
						.setDate("date", c.getTime())
						.uniqueResult();
				if (i>0) {
					if (next) c.add(Calendar.DATE, 1);
					else c.add(Calendar.DATE, -1);
				} else {
					if (c.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY || c.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY) {
						if (next) c.add(Calendar.DATE, 1);
						else c.add(Calendar.DATE, -1);
					} else {
						b = true;
					}
				}
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (session!=null) session.close();
		}
		return (c!=null?c.getTime():null);
	}*/


	
}
