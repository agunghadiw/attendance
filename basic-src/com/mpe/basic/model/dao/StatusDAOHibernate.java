package com.mpe.basic.model.dao;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

import com.mpe.basic.model.Status;
import com.mpe.basic.model.other.StatusType;
import com.mpe.common.dao.GenericHibernateDAO;
import com.mpe.common.model.ComboBoxModel;
/**
 * @author Agung Hadiwaluyo
 *
 */
public class StatusDAOHibernate extends GenericHibernateDAO<Status, Long> implements StatusDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Status> findByType(StatusType type) throws Exception {
		Session session = getSessionAnnotated();
		List<Status> list = null;
		try {
			Criteria criteria = session.createCriteria(Status.class)
				.setCacheable(true)
				.add(Restrictions.eq("type", type));
			list = criteria.list();
		} catch(Exception exception){
			throw exception;
		} finally {
			if (session!=null) session.close();
		}
		return list;
	}

	@Override
	public List<ComboBoxModel> comboBoxList(String orderBy, String ascDesc, StatusType type) throws Exception {
		Session session = getSessionAnnotated();
		List<ComboBoxModel> models = new LinkedList<ComboBoxModel>();
		try {
			if (orderBy==null || (orderBy!=null && orderBy.length()==0)) orderBy = " name ";
			String sql = "" +
					"select " +
					"status_id as key, " +
					"name as name " +
					"from status " +
					"where type =  '" + type + "'  order by " + orderBy + " " + ascDesc + " " + 
					"";
			
			models = session.createSQLQuery(sql)
					.addScalar("key", LongType.INSTANCE)
					.addScalar("name", StringType.INSTANCE)
					.setResultTransformer(Transformers.aliasToBean(ComboBoxModel.class))
					.list();
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (session!=null) session.close();
		}
		return models;
	}

	@Override
	public List<ComboBoxModel> comboBoxListID(String orderBy, String ascDesc,
			StatusType type, Long statusId) throws Exception {
		Session session = getSessionAnnotated();
		List<ComboBoxModel> models = new LinkedList<ComboBoxModel>();
		try {
			if (orderBy==null || (orderBy!=null && orderBy.length()==0)) orderBy = " name ";
			String sql = "" +
					"select " +
					"status_id as key, " +
					"name as name " +
					"from status " +
					"where " +
					"type =  '" + type + "'  " + (statusId!=null?" and status_id = "+statusId.longValue()+" ":"") +
					"order by " + orderBy + " " + ascDesc + " " + 
					"";
			
			models = session.createSQLQuery(sql)
					.addScalar("key", LongType.INSTANCE)
					.addScalar("name", StringType.INSTANCE)
					.setResultTransformer(Transformers.aliasToBean(ComboBoxModel.class))
					.list();
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (session!=null) session.close();
		}
		return models;
	}
	
	@Override
	public List<ComboBoxModel> comboBoxListID(String orderBy, String ascDesc,
			StatusType type, Long[] statusId) throws Exception {
		Session session = getSessionAnnotated();
		List<ComboBoxModel> models = new LinkedList<ComboBoxModel>();
		try {
			if (orderBy==null || (orderBy!=null && orderBy.length()==0)) orderBy = " name ";
			String string = "";
			if (statusId!=null && statusId.length>0) string = string + " and status_id IN (:statusIds) ";
			String sql = "" +
					"select " +
					"status_id as key, " +
					"name as name " +
					"from status " +
					"where " +
					"type =  '" + type + "'  " + string + " " +
					"order by " + orderBy + " " + ascDesc + " " + 
					"";
			
			SQLQuery query = session.createSQLQuery(sql)
					.addScalar("key", LongType.INSTANCE)
					.addScalar("name", StringType.INSTANCE);
			
			if (statusId!=null && statusId.length>0) query.setParameterList("statusIds", statusId);
			
			models = query
					.setResultTransformer(Transformers.aliasToBean(ComboBoxModel.class))
					.list();
			
		} catch (Exception e) {
			throw e;
		} finally {
			if (session!=null) session.close();
		}
		return models;
	}

}
