package com.mpe.basic.model.dao;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;

import com.mpe.basic.model.Lookup;
import com.mpe.basic.model.other.LookupCategory;
import com.mpe.common.dao.GenericHibernateDAO;
import com.mpe.common.model.ComboBoxModel;
/**
 * @author Agung Hadiwaluyo
 *
 */
public class LookupDAOHibernate extends GenericHibernateDAO<Lookup, Long> implements LookupDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Lookup> findByCategory(LookupCategory category) throws Exception {
		Session session = getSessionAnnotated();
		Transaction transaction = null;
		List<Lookup> list = null;
		try {
			transaction = session.beginTransaction();
			Criteria criteria = session.createCriteria(Lookup.class)
				.add(Restrictions.eq("category", category))
				.addOrder(Order.asc("name"))
				.setCacheable(true);			
			list = criteria.list();
			transaction.commit();
		} catch(Exception exception){
			if (transaction!=null)transaction.rollback();
			throw exception;
		} finally {
			if (session!=null) session.close();
		}
		return list;
	}

	@Override
	public List<ComboBoxModel> comboBoxList(String orderBy, String ascDesc, LookupCategory category)
			throws Exception {
		Session session = getSessionAnnotated();
		List<ComboBoxModel> models = new LinkedList<ComboBoxModel>();
		try {
			if (orderBy==null || (orderBy!=null && orderBy.length()==0)) orderBy = " name ";
			String sql = "" +
					"select " +
					"lookup_id as id, " +
					"name as name " +
					"from lookup " +
					"where category = '" + category.name()+ "' order by " + orderBy + " " + ascDesc + " " + 
					"";
			
			models = (List<ComboBoxModel>)session.createSQLQuery(sql)
					.addScalar("id", LongType.INSTANCE)
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
	public List<Lookup> findByCategory(LookupCategory category, Session session)
			throws Exception {
		List<Lookup> list = null;
		try {
			Criteria criteria = session.createCriteria(Lookup.class)
				.add(Restrictions.eq("category", category))
				.addOrder(Order.asc("name"))
				.setCacheable(true);			
			list = criteria.list();
		} catch(Exception exception){
			throw exception;
		}
		return list;
	}
	
	

}
