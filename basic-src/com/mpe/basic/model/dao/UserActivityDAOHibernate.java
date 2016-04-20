package com.mpe.basic.model.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

import com.mpe.basic.model.UserActivity;
import com.mpe.basic.model.other.UserActivityCalendar;
import com.mpe.basic.model.other.UserActivityCalendarDetail;
import com.mpe.common.dao.GenericHibernateDAO;
import com.mpe.common.util.PartialList;
/**
 * @author Agung Hadiwaluyo
 *
 */
public class UserActivityDAOHibernate extends GenericHibernateDAO<UserActivity, Long> implements UserActivityDAO {
	
	Log logger = LogFactory.getFactory().getInstance(this.getClass());

	@Override
	public void insertActivity(long userId, String userName, String viewName, String ip)
			throws Exception {
		Session session = getSessionAnnotated();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			/*String sql = "" +
					"insert into user_activity(user_activity_id, activity_date, user_name, activity_name, ip, user_id) " +
					"values (user_activity_seq.NEXTVAL, :a, :b, :c, :d, :e) " +
					"" +
					"";
			session.createSQLQuery(sql)
				.setTimestamp("a", new Timestamp((new Date()).getTime()))
				.setString("b", userName)
				.setString("c", viewName)
				.setString("d", ip)
				.setLong("e", userId)
				.executeUpdate();*/
			
			UserActivity userActivity = new UserActivity();
			userActivity.setActivityDate(new Date());
			userActivity.setActivityName(viewName);
			userActivity.setIp(ip);
			userActivity.setUserId(userId);
			userActivity.setUserName(userName);
			session.save(userActivity);
			
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction!=null) transaction.rollback();
		} finally {
			if (session!=null) session.close();
		}
		
	}

	@SuppressWarnings({"unchecked" })
	@Override
	public PartialList<UserActivityCalendar> userCalendar(int start, int count,
			int month, int year, String userName, long organizationId, boolean customer, String customerCompany) throws Exception {
		Session session = getSessionAnnotated();
		PartialList<UserActivityCalendar> partialList = null;
		try {
			String string = "";
			if (userName!=null && userName.length()>0) string = string + " and u.user_name like '%"+userName+"%' ";
			if (organizationId>0) string = string + " and u.organization_id = "+organizationId+ " ";
			//if (customer) string = string + " and u.organization_id is null ";
			//if (customerCompany!=null && customerCompany.length()>0) string = string + " and c.company like '%"+customerCompany+"%' ";
			String sqlTotal = "" +
					"select count(*) as A " +
					"from users u " +
					"left join employee e on u.user_id = e.user_id " +
					"left join organization o on u.organization_id= o.organization_id " +
					"where u.is_active = 'T' " + string + " " +
					"";
			
			SQLQuery queryTotal = session.createSQLQuery(sqlTotal).addScalar("A", LongType.INSTANCE);
			long total = ((Long)queryTotal.setCacheable(true).uniqueResult()).longValue();
			
			String sql = "" +
					"select " +
					"u.user_id as A, " +
					"u.user_name as B, " +
					"e.full_name as C, " +
					"case when u.organization_id is not null then o.name else '-' end as D " +
					"from users u " +
					"left join employee e on u.user_id = e.user_id " +
					"left join organization o on u.organization_id= o.organization_id " +
					"where u.is_active = 'T' " + string + " " +
					"";			
			
			SQLQuery query = session.createSQLQuery(sql)
				.addScalar("A", LongType.INSTANCE)
				.addScalar("B", StringType.INSTANCE)
				.addScalar("C", StringType.INSTANCE)
				.addScalar("D", StringType.INSTANCE)
				;
			
			List<Object[]> list = query.setCacheable(true)
				.setFirstResult(start)
				.setMaxResults(count).list();
			
			List<UserActivityCalendar> models = new LinkedList<UserActivityCalendar>();
			
			Calendar cal = new GregorianCalendar();
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			
			int wos[] = getWorkOffDayInMonth(cal.getTime(), session);
			
			for (Object[] objects : list) {
				UserActivityCalendar userActivityCalendar = new UserActivityCalendar();
				userActivityCalendar.setUserId((Long)objects[0]);
				userActivityCalendar.setUserName((String)objects[1]);
				userActivityCalendar.setFullName((String)objects[2]);
				userActivityCalendar.setOrganizationOrCustomer((String)objects[3]);
				
				Calendar calendar = new GregorianCalendar();
				calendar.set(Calendar.MONTH, month);
				calendar.set(Calendar.YEAR, year);
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				List<UserActivityCalendarDetail> details = new LinkedList<UserActivityCalendarDetail>();
				int maxDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
				
				int uas[] = getUserActivityInMonth(cal.getTime(), userActivityCalendar.getUserId(), session);
				
				for (int i=1; i<=maxDayOfMonth; i++) {
					UserActivityCalendarDetail detail = new UserActivityCalendarDetail();
					detail.setDate(i);
					if (calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY) {
						detail.setHoliday(true);
					}
					detail.setActivityCount(uas[i-1]);
					// get public holiday
					if (wos[i-1]>0) {
						detail.setHoliday(true);
					}
					details.add(detail);
					// add date
					calendar.add(Calendar.DAY_OF_MONTH, 1);
				}
				userActivityCalendar.setUserActivityCalendarDetails(details);
				models.add(userActivityCalendar);
			}
			partialList = new PartialList<UserActivityCalendar>(models, total);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session!=null) session.close();
		}
		return partialList;
	}
	
	@Override
	public int[] getUserActivityInMonth(Date date, long userId, Session session) throws Exception {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int maxDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int[] is = new int[maxDayOfMonth];
		Vector<Date> dates = new Vector<Date>();
		for (int i=1; i<=maxDayOfMonth; i++) {
			Calendar cal = new GregorianCalendar();
			cal.setTime(date);			
			cal.add(Calendar.DAY_OF_MONTH, i-1);
			dates.add(cal.getTime());
		}
		try {
			
			String sqlUserActivityMonth = "" +
				"select count(distinct user_activity_id) as A, to_char(activity_date, 'DD') as B from user_activity " +
				"where " +
				"user_id = :userId and " +
				//"date(activity_date) IN (:dates) " +
				"to_date(to_char(activity_date, 'dd/MM/yyyy'),'dd/MM/yyyy') IN (:dates) " +
				"group by activity_date " +
				"order by to_char(activity_date, 'DD') ASC " +
				"";
			List<Object[]> list = session.createSQLQuery(sqlUserActivityMonth)
				.addScalar("A", IntegerType.INSTANCE)
				.addScalar("B", IntegerType.INSTANCE)
				.setLong("userId", userId)
				.setParameterList("dates", dates, DateType.INSTANCE)
				.list();
			
			for (int i=0; i<is.length; i++) {
				for (Object[] objects : list) {
					if (objects!=null) {
						if (((Integer)objects[1]).intValue()==(i+1)) {
							is[i] = ((Integer)objects[0]).intValue();
							break;
						}
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return is;
	}
	
	@Override
	public int[] getWorkOffDayInMonth(Date date, Session session) throws Exception {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int maxDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		int[] is = new int[maxDayOfMonth];
		Vector<Date> dates = new Vector<Date>();
		for (int i=1; i<=maxDayOfMonth; i++) {
			Calendar cal = new GregorianCalendar();
			cal.setTime(date);			
			cal.add(Calendar.DAY_OF_MONTH, i-1);
			dates.add(cal.getTime());
		}
		try {
			
			/*String sqlWorkOffDayMonth = "" +
				"select count(distinct work_off_day_id) as A, day(event_date) as B from work_off_day " +
				"where event_date IN (:dates) " +
				"group by event_date " +
				"order by day(event_date) ASC " +
				"";*/
			
			String sqlWorkOffDayMonth = "" +
					"select count(distinct work_off_day_id) as A, to_char(event_date,'DD') as B from work_off_day " +
					"where event_date IN (:dates) " +
					"group by event_date " +
					"order by to_char(event_date,'DD') ASC " +
					"";
			
			List<Object[]> list = session.createSQLQuery(sqlWorkOffDayMonth)
				.addScalar("A", IntegerType.INSTANCE)
				.addScalar("B", IntegerType.INSTANCE)
				.setParameterList("dates", dates, DateType.INSTANCE)
				.list();
			
			for (int i=0; i<is.length; i++) {
				for (Object[] objects : list) {
					if (objects!=null) {
						if (((Integer)objects[1]).intValue()==(i+1)) {
							is[i] = ((Integer)objects[0]).intValue();
							break;
						}
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return is;
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public List<UserActivityCalendar> userCalendar(int month, int year,
			String userName, long organizationId, boolean customer, String customerCompany) throws Exception {
		Session session = getSessionAnnotated();
		List<UserActivityCalendar> models = new LinkedList<UserActivityCalendar>();
		try {
			String string = "";
			if (userName!=null && userName.length()>0) string = string + " and u.user_name like '%"+userName+"%' ";
			if (organizationId>0) string = string + " and u.organization_id = "+organizationId+ " ";
			//if (customer) string = string + " and u.organization_id is null ";
			//if (customerCompany!=null && customerCompany.length()>0) string = string + " and c.company like '%"+customerCompany+"%' ";
			String sql = "" +
					"select " +
					"u.user_id as A, " +
					"u.user_name as B, " +
					"e.full_name as C, " +
					"o.name as D " +
					"from users u " +
					"left join employee e on u.user_id = e.user_id " +
					"left join organization o on u.organization_id= o.organization_id " +
					"where u.is_active = 'T' " + string + " " +
					"";
			
			SQLQuery query = session.createSQLQuery(sql)
				.addScalar("A", LongType.INSTANCE)
				.addScalar("B", StringType.INSTANCE)
				.addScalar("C", StringType.INSTANCE)
				.addScalar("D", StringType.INSTANCE)
				;
			
			List<Object[]> list = query.setCacheable(true).list();
			
			Calendar cal = new GregorianCalendar();
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			
			int wos[] = getWorkOffDayInMonth(cal.getTime(), session);
			
			for (Object[] objects : list) {
				UserActivityCalendar userActivityCalendar = new UserActivityCalendar();
				userActivityCalendar.setUserId((Long)objects[0]);
				userActivityCalendar.setUserName((String)objects[1]);
				userActivityCalendar.setFullName((String)objects[2]);
				userActivityCalendar.setOrganizationOrCustomer((String)objects[3]);
				
				Calendar calendar = new GregorianCalendar();
				calendar.set(Calendar.MONTH, month);
				calendar.set(Calendar.YEAR, year);
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				List<UserActivityCalendarDetail> details = new LinkedList<UserActivityCalendarDetail>();
				int maxDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
				
				int uas[] = getUserActivityInMonth(cal.getTime(), userActivityCalendar.getUserId(), session);
				
				for (int i=1; i<=maxDayOfMonth; i++) {
					UserActivityCalendarDetail detail = new UserActivityCalendarDetail();
					detail.setDate(i);
					if (calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SATURDAY || calendar.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY) {
						detail.setHoliday(true);
					}
					//logger.info("x : "+x);
					detail.setActivityCount(uas[i-1]);
					// get public holiday
					if (wos[i-1]>0) {
						detail.setHoliday(true);
					}
					details.add(detail);
					// add date
					calendar.add(Calendar.DAY_OF_MONTH, 1);
				}
				userActivityCalendar.setUserActivityCalendarDetails(details);
				models.add(userActivityCalendar);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session!=null) session.close();
		}
		return models;
	}
	
	
}
