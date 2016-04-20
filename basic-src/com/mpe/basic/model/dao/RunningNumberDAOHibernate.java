package com.mpe.basic.model.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.mpe.basic.model.Lookup;
import com.mpe.basic.model.RunningNumber;
import com.mpe.basic.model.UniqueNumber;
import com.mpe.basic.model.other.LookupCategory;
import com.mpe.common.dao.GenericHibernateDAO;
import com.mpe.common.util.CommonUtil;

/**
 * @author Agung Hadiwaluyo
 *
 */
public class RunningNumberDAOHibernate extends GenericHibernateDAO<RunningNumber, Long> implements RunningNumberDAO {
	
	public String getUniqueNumber(Date date, boolean yearly) throws Exception {
		Session session = getSessionAnnotated();
		Transaction transaction = null;
		String num = "";
		try {
			transaction = session.beginTransaction();
			UniqueNumber uniqueNumber = null;
			try {
				uniqueNumber = (UniqueNumber)session.createCriteria(UniqueNumber.class).add(Restrictions.ne("uniqueNumberId", new Long(-1))).uniqueResult();
			} catch (Exception e) {}
			
			if (uniqueNumber==null) {
				uniqueNumber = new UniqueNumber();
				uniqueNumber.setNumberDate(new Date());
				uniqueNumber.setStartNumber("0000000001");
				session.save(uniqueNumber);
			} else {
				String n = uniqueNumber.getStartNumber();
				String code = Integer.toString(Integer.parseInt(n)+1);
				if (code.length()>n.length()) code = "1";
				n = returnNol(n.length()-code.length())+code;
				uniqueNumber.setStartNumber(n);
				session.update(uniqueNumber);
			}
			//
			//num = (CommonUtil.getStringFromDate(date, "yyMMddHHmm")) + uniqueNumber.getStartNumber();
			num = uniqueNumber.getStartNumber();
			//
			transaction.commit();
		} catch (Exception e) {
			transaction.rollback();
			throw e;
		} finally {
			if (session!=null) session.close();
		}
		return num;
	}
	
	public String getUniqueRunningNumber(String runningNumberType, long organizationId, Date date, boolean yearly) throws Exception {
		Session session = getSessionAnnotated();
		String num = "";
		try {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			int m = calendar.get(Calendar.MONTH) + 1;
			String mm = String.valueOf(m).length()==1?""+String.valueOf(m) : String.valueOf(m);
			String yyyy = String.valueOf(calendar.get(Calendar.YEAR));
			String yy = yyyy.substring(2, yyyy.length());
			if (runningNumberType==null || runningNumberType.length()==0) throw new Exception("Running Number Null or Empty!");
			/*Lookup numberType = (Lookup)getSession().createCriteria(Lookup.class)
				.add(Restrictions.eq("category", "RUNNING_NUMBER_TYPE"))
				.add(Restrictions.eq("name", new String(runningNumberType)))
				.setMaxResults(1).uniqueResult();*/
			// ** PROBLEM WITH SINGLE SESSION **
			Lookup numberType = (Lookup)session.createCriteria(Lookup.class)
				.add(Restrictions.eq("category", LookupCategory.RUNNING_NUMBER_TYPE))
				.add(Restrictions.eq("name", new String(runningNumberType)))
				.setMaxResults(1).uniqueResult();
			if (numberType==null) throw new Exception("Running Number Lookup Not Found!");
			//RunningNumber runningNumber = findByCriteria(Restrictions.eq("runningNumberType.lookupId", new Long(numberType.getLookupId())),Restrictions.eq("organizationId", new Long(organizationId)));
			// ** PROBLEM WITH SINGLE SESSION **
			RunningNumber runningNumber = findByCriteria(session, Restrictions.eq("runningNumberType.lookupId", new Long(numberType.getLookupId())),Restrictions.eq("organization.organizationId", new Long(organizationId)));
			if (runningNumber==null) throw new Exception("Running Number Not Found!");
			if (runningNumber!=null) {
				// cek and replace MM YY at suffix or prefix with calendar !
				String suffix = runningNumber.getSuffix()!=null?runningNumber.getSuffix():"";
				String prefix = runningNumber.getPrefix()!=null?runningNumber.getPrefix():"";
				if (suffix!=null && suffix.length()>0 && suffix.indexOf("MM")>=0) suffix = suffix.replace("MM", mm);
				if (suffix!=null && suffix.length()>0 && suffix.indexOf("YY")>=0) suffix = suffix.replace("YY", yy);
				if (suffix!=null && suffix.length()>0 && suffix.indexOf("YYYY")>=0) suffix = suffix.replace("YYYY", yyyy);
				if (prefix!=null && prefix.length()>0 && prefix.indexOf("MM")>=0) prefix = prefix.replace("MM", mm);
				if (prefix!=null && prefix.length()>0 && prefix.indexOf("YY")>=0) prefix = prefix.replace("YY", yy);
				if (prefix!=null && prefix.length()>0 && prefix.indexOf("YYYY")>=0) prefix = prefix.replace("YYYY", yyyy);
				// get number from running-number
				String n = "";
				if (runningNumber.getCurrentNumber()!=null && runningNumber.getCurrentNumber().length()>0) n = (runningNumber.getCurrentNumber()).substring(prefix.length(), (runningNumber.getCurrentNumber().length() - suffix.length()));
				else {
					// cek yearly => if false get previous last data
					// TODO
					n = returnReset(runningNumber.getStartNumber().length());
				}
				
				if (n!=null && n.equalsIgnoreCase(returnMax(n.length()))) {
					throw new Exception("Running Number Over Limit!");
				} else {
					// increase number
					String code = Integer.toString(Integer.parseInt(n)+1);
					n = returnNol(n.length()-code.length())+code;
				}
				
				// try insert into unique number
				boolean fail = true;
				while (fail) {
					Transaction transaction = null;
					try {
						transaction = session.beginTransaction();
						num = prefix + n + suffix;
						runningNumber.setCurrentNumber(num);
						session.update(runningNumber);
						transaction.commit();
						fail = false;
					} catch (Exception e) {
						if (transaction!=null) transaction.rollback();
						// increase number
						String code = Integer.toString(Integer.parseInt(n)+1);
						n = returnNol(n.length()-code.length())+code;
					}
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (session!=null) session.close();
		}
		return num;
	}
	
	public String returnNol(int i) {
		String x = "";
		for (int a=0; a<i; a++) {
			x = x + "0";
		}
		return x;
	}

	public String returnMax(int i) {
		String x = "";
		for (int a=0; a<i; a++) {
			x = x + "9";
		}
		return x;
	}
	
	public String returnReset(int i) {
		String x = "";
		for (int a=0; a<i; a++) {
			x = x + "0";
		}
		x = x.substring(0, (x.length()-1)) + "0";
		return x;
	}

	@Override
	public String getUniqueRunningNumber(long runningNumberId, Date date,
			boolean yearly) throws Exception {
		Session session = getSessionAnnotated();
		String num = "";
		try {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			int m = calendar.get(Calendar.MONTH) + 1;
			String mm = String.valueOf(m).length()==1?""+String.valueOf(m) : String.valueOf(m);
			String yyyy = String.valueOf(calendar.get(Calendar.YEAR));
			String yy = yyyy.substring(2, yyyy.length());
			//RunningNumber runningNumber = findByCriteria(Restrictions.eq("runningNumberType.lookupId", new Long(numberType.getLookupId())),Restrictions.eq("organizationId", new Long(organizationId)));
			// ** PROBLEM WITH SINGLE SESSION **
			RunningNumber runningNumber = findByCriteria(session, Restrictions.eq("runningNumberId", new Long(runningNumberId)));
			if (runningNumber==null) throw new Exception("Running Number Not Found!");
			if (runningNumber!=null) {
				// cek and replace MM YY at suffix or prefix with calendar !
				String suffix = runningNumber.getSuffix()!=null?runningNumber.getSuffix():"";
				String prefix = runningNumber.getPrefix()!=null?runningNumber.getPrefix():"";
				if (suffix!=null && suffix.length()>0 && suffix.indexOf("MM")>=0) suffix = suffix.replace("MM", mm);
				if (suffix!=null && suffix.length()>0 && suffix.indexOf("YY")>=0) suffix = suffix.replace("YY", yy);
				if (suffix!=null && suffix.length()>0 && suffix.indexOf("YYYY")>=0) suffix = suffix.replace("YYYY", yyyy);
				if (prefix!=null && prefix.length()>0 && prefix.indexOf("MM")>=0) prefix = prefix.replace("MM", mm);
				if (prefix!=null && prefix.length()>0 && prefix.indexOf("YY")>=0) prefix = prefix.replace("YY", yy);
				if (prefix!=null && prefix.length()>0 && prefix.indexOf("YYYY")>=0) prefix = prefix.replace("YYYY", yyyy);
				// get number from running-number
				String n = "";
				if (runningNumber.getCurrentNumber()!=null && runningNumber.getCurrentNumber().length()>0) n = (runningNumber.getCurrentNumber()).substring(prefix.length(), (runningNumber.getCurrentNumber().length() - suffix.length()));
				else {
					// cek yearly => if false get previous last data
					// TODO
					n = returnReset(runningNumber.getStartNumber().length());
				}
				
				if (n!=null && n.equalsIgnoreCase(returnMax(n.length()))) {
					throw new Exception("Running Number Over Limit!");
				} else {
					// increase number
					String code = Integer.toString(Integer.parseInt(n)+1);
					n = returnNol(n.length()-code.length())+code;
				}
				
				// try insert into unique number
				boolean fail = true;
				while (fail) {
					Transaction transaction = null;
					try {
						transaction = session.beginTransaction();
						num = prefix + n + suffix;
						runningNumber.setStartNumber(num);
						session.update(runningNumber);
						transaction.commit();
						fail = false;
					} catch (Exception e) {
						if (transaction!=null) transaction.rollback();
						// increase number
						String code = Integer.toString(Integer.parseInt(n)+1);
						n = returnNol(n.length()-code.length())+code;
					}
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (session!=null) session.close();
		}
		return num;
	}

	@Override
	public String getUniqueRunningNumber(long runningNumberTypeId,
			long organizationId, Date date, boolean yearly) throws Exception {
		Session session = getSessionAnnotated();
		String num = "";
		try {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			int m = calendar.get(Calendar.MONTH) + 1;
			String mm = String.valueOf(m).length()==1?""+String.valueOf(m) : String.valueOf(m);
			String yyyy = String.valueOf(calendar.get(Calendar.YEAR));
			String yy = yyyy.substring(2, yyyy.length());
			//RunningNumber runningNumber = findByCriteria(Restrictions.eq("runningNumberType.lookupId", new Long(numberType.getLookupId())),Restrictions.eq("organizationId", new Long(organizationId)));
			// ** PROBLEM WITH SINGLE SESSION **
			RunningNumber runningNumber = findByCriteria(session, Restrictions.eq("runningNumberType.lookupId", new Long(runningNumberTypeId)),Restrictions.eq("organization.organizationId", new Long(organizationId)));
			if (runningNumber==null) throw new Exception("Running Number Not Found!");
			if (runningNumber!=null) {
				// cek and replace MM YY at suffix or prefix with calendar !
				String suffix = runningNumber.getSuffix()!=null?runningNumber.getSuffix():"";
				String prefix = runningNumber.getPrefix()!=null?runningNumber.getPrefix():"";
				if (suffix!=null && suffix.length()>0 && suffix.indexOf("MM")>=0) suffix = suffix.replace("MM", mm);
				if (suffix!=null && suffix.length()>0 && suffix.indexOf("YY")>=0) suffix = suffix.replace("YY", yy);
				if (suffix!=null && suffix.length()>0 && suffix.indexOf("YYYY")>=0) suffix = suffix.replace("YYYY", yyyy);
				if (prefix!=null && prefix.length()>0 && prefix.indexOf("MM")>=0) prefix = prefix.replace("MM", mm);
				if (prefix!=null && prefix.length()>0 && prefix.indexOf("YY")>=0) prefix = prefix.replace("YY", yy);
				if (prefix!=null && prefix.length()>0 && prefix.indexOf("YYYY")>=0) prefix = prefix.replace("YYYY", yyyy);
				// get number from running-number
				String n = "";
				if (runningNumber.getCurrentNumber()!=null && runningNumber.getCurrentNumber().length()>0) n = (runningNumber.getCurrentNumber()).substring(prefix.length(), (runningNumber.getCurrentNumber().length() - suffix.length()));
				else {
					// cek yearly => if false get previous last data
					// TODO
					n = returnReset(runningNumber.getStartNumber().length());
				}
				
				if (n!=null && n.equalsIgnoreCase(returnMax(n.length()))) {
					throw new Exception("Running Number Over Limit!");
				} else {
					// increase number
					String code = Integer.toString(Integer.parseInt(n)+1);
					n = returnNol(n.length()-code.length())+code;
				}
				
				// try insert into unique number
				boolean fail = true;
				while (fail) {
					Transaction transaction = null;
					try {
						transaction = session.beginTransaction();
						num = prefix + n + suffix;
						runningNumber.setCurrentNumber(num);
						session.update(runningNumber);
						transaction.commit();
						fail = false;
					} catch (Exception e) {
						if (transaction!=null) transaction.rollback();
						// increase number
						String code = Integer.toString(Integer.parseInt(n)+1);
						n = returnNol(n.length()-code.length())+code;
					}
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (session!=null) session.close();
		}
		return num;
	}
	
}
