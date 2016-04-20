package com.mpe.basic.action;

import java.util.List;
import java.util.Vector;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.mpe.basic.model.Lookup;
import com.mpe.basic.model.dao.BasicDAOFactory;
import com.mpe.basic.model.dao.BasicDAOFactoryHibernate;
import com.mpe.basic.model.other.LookupCategory;
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
public class LookupAction extends BaseAction implements Preparable, ModelDriven<Lookup> {
	
	private static final long serialVersionUID = 1L;
	
	BasicDAOFactory basicDAOFactory = BasicDAOFactory.instance(BasicDAOFactoryHibernate.class);
	
	List<Lookup> lookupList;
	Lookup lookup;
	long lookupId;
	Vector<String> lookupCategoryList = new Vector<String>();
	String nameX="", lookupCategoryX=""; 
	
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
					@RequiredStringValidator(fieldName="name",type=ValidatorType.SIMPLE,trim=true,key="validation.name.required"),
			},
			requiredFields={
					@RequiredFieldValidator(fieldName="category",type=ValidatorType.FIELD,key="validation.category.required")
			}
	)*/
	
	@Override
	public String save() {
		try {
			basicDAOFactory.getLookupDAO().save(lookup);
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
			basicDAOFactory.getLookupDAO().update(lookup);
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
			basicDAOFactory.getLookupDAO().delete(getLookupId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setMappedRequest(Constants.PARTIAL_LIST);
		return Constants.REDIRECT;
	}
	
	@Override
	public String list() {
		try {
			lookupList = basicDAOFactory.getLookupDAO().findAll(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setMappedRequest(Constants.LIST);
		return SUCCESS;
	}
	
	@Override
	public String partialList() {
		try {
			PartialList<Lookup> partialList = basicDAOFactory.getLookupDAO().findByCriteria(getStart(), getCount(), getAscDesc().equalsIgnoreCase("asc")?Order.asc(getOrderBy().length()>0?getOrderBy():"lookupId"):Order.desc(getOrderBy().length()>0?getOrderBy():"lookupId"), 
					nameX.length()>0?Restrictions.like("name", nameX,MatchMode.ANYWHERE):Restrictions.ne("lookupId", new Long(-1)), 
					lookupCategoryX.length()>0?Restrictions.eq("category", LookupCategory.valueOf(lookupCategoryX)):Restrictions.isNotNull("category"));
			lookupList = partialList.getList();
			setPager(Pager.generatePager(getStart(), getCount(), partialList.getTotal()));
			setPagerItem(Pager.generatePagerItem(getStart(), getCount(), partialList.getTotal()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		setMappedRequest(Constants.PARTIAL_LIST);
		return SUCCESS;
	}

	@Override
	public Lookup getModel() {
		return lookup;
	}

	@Override
	public void prepare() throws Exception {
		try {
			if (lookupCategoryList.size()==0) {
				LookupCategory[] categories = LookupCategory.values();
				for (int i=0; i<categories.length; i++) {
					lookupCategoryList.add(categories[i].name());
				}
			}
		} catch (Exception e) {
		}
		if (getLookupId()==0) {
			lookup = new Lookup();
		} else {
			lookup = (Lookup) basicDAOFactory.getLookupDAO().findById(getLookupId());
		}
	}

	public Lookup getLookup() {
		return lookup;
	}

	public void setLookup(Lookup lookup) {
		this.lookup = lookup;
	}

	public long getLookupId() {
		return lookupId;
	}

	public void setLookupId(long lookupId) {
		this.lookupId = lookupId;
	}

	public List<Lookup> getLookupList() {
		return lookupList;
	}

	public void setLookupList(List<Lookup> lookupList) {
		this.lookupList = lookupList;
	}

	public Vector<String> getLookupCategoryList() {
		return lookupCategoryList;
	}

	public void setLookupCategoryList(Vector<String> lookupCategoryList) {
		this.lookupCategoryList = lookupCategoryList;
	}

	public String getNameX() {
		return nameX;
	}

	public void setNameX(String nameX) {
		this.nameX = nameX;
	}

	public String getLookupCategoryX() {
		return lookupCategoryX;
	}

	public void setLookupCategoryX(String lookupCategoryX) {
		this.lookupCategoryX = lookupCategoryX;
	}
	
	

}
