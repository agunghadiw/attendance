package com.mpe.basic.action;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.mpe.basic.model.Address;
import com.mpe.basic.model.Branch;
import com.mpe.basic.model.BranchClass;
import com.mpe.basic.model.Location;
import com.mpe.basic.model.User;
import com.mpe.basic.model.dao.BasicDAOFactory;
import com.mpe.basic.model.dao.BasicDAOFactoryHibernate;
import com.mpe.basic.model.other.BranchList;
import com.mpe.basic.model.other.LocationType;
import com.mpe.common.action.BaseAction;
import com.mpe.common.model.ComboBoxModel;
import com.mpe.common.model.TreeNode;
import com.mpe.common.util.Constants;
import com.mpe.common.util.Pager;
import com.mpe.common.util.PartialList;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

public class BranchAction extends BaseAction implements Preparable, ModelDriven<Branch> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	BasicDAOFactory basicDAOFactory = BasicDAOFactory.instance(BasicDAOFactoryHibernate.class);
	
	long branchId;
	Branch branch;
	List<BranchList> branchLists;
	List<Location> cities;
	List<BranchClass> branchClasses;
	List<ComboBoxModel> branchParents;
	List<ComboBoxModel> branchTypes;
	
	
	Address address;
	// for jquery validation only!!!
	Long cityId;
	
	String codeX="",nameX="",cityX="";
	Long branchClassIdX, branchTypeIdX;
	
	List<TreeNode> treeNodes;

	@Override
	public Branch getModel() {
		return branch;
	}

	@Override
	public void prepare() throws Exception {
		try {
			if (cities==null) cities = basicDAOFactory.getLocationDAO().findByCriteria(Order.asc("name"), Restrictions.eq("locationType", LocationType.CITY));
			if (branchClasses==null) {
				branchClasses = basicDAOFactory.getBranchClassDAO().findAll(Order.asc("code"));
			}
			if (branchTypes==null) branchTypes = basicDAOFactory.getBranchTypeDAO().comboBoxList("parent_id", "asc", null, null);
			//
			if (branchId==0) {
				branch = new Branch();
			} else {
				Session session = basicDAOFactory.getBranchDAO().getSession(Constants.HIBERNATE_CFG_KEY_1);
				branch = (Branch)session.createCriteria(Branch.class)
					.setFetchMode("address", FetchMode.JOIN)
					.add(Restrictions.eq("branchId", new Long(branchId))).uniqueResult();
				session.close();
				
			}
			if (branch!=null) {
				address = branch.getAddress()!=null?branch.getAddress():new Address();	
				//
				if (branch.getBranchTypeId()!=null && branch.getBranchTypeId() > 0) {
					Long previousBranchTypeId = basicDAOFactory.getBranchTypeDAO().previousBranchType(branch.getBranchTypeId());
					if (previousBranchTypeId!=null && previousBranchTypeId.longValue()>0) {
						branchParents = basicDAOFactory.getBranchDAO().comboBoxList("b.name", "asc", null, previousBranchTypeId, "", "", "");
					}
				}
			}
			if (branchParents==null) branchParents = new LinkedList<ComboBoxModel>(); 
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public String tree() {
		try {
			User user = (User)getSession().get(Constants.USER);
			treeNodes = basicDAOFactory.getBranchDAO().showTree(user.getOrganization().getOrganizationId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	@Override
	public String cancel() {
		setMappedRequest(Constants.PARTIAL_LIST);
		return Constants.REDIRECT;
	}

	@Override
	public String list() {
		return null;
	}

	@Override
	public String partialList() {
		try {
			PartialList<BranchList> partialList = basicDAOFactory.getBranchDAO().findAll(getStart(), getCount(), 
					getOrderBy(), getAscDesc(), branchClassIdX, branchTypeIdX, codeX, nameX, cityX);
			branchLists = partialList.getList();
			setPager(Pager.generatePager(getStart(), getCount(), partialList.getTotal()));
			setPagerItem(Pager.generatePagerItem(getStart(), getCount(), partialList.getTotal()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		setMappedRequest(Constants.PARTIAL_LIST);
		return SUCCESS;
	}

	@Override
	public String add() {
		setPreviousPage(Constants.ADD);
		setMappedRequest(Constants.SAVE);
		return SUCCESS;
	}

	@Override
	public String save() {
		try {
			User user = (User)getSession().get(Constants.USER);
			if (user!=null && user.getOrganization()!=null) branch.setOrganizationId(user.getOrganization().getOrganizationId());
			basicDAOFactory.getBranchDAO().save(branch);
		} catch (Exception e) {
			e.printStackTrace();
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
			User user = (User)getSession().get(Constants.USER);
			if (user!=null && user.getOrganization()!=null) branch.setOrganizationId(user.getOrganization().getOrganizationId());
			basicDAOFactory.getBranchDAO().update(branch);
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
			return INPUT;
		}
		setMappedRequest(Constants.PARTIAL_LIST);
		return Constants.REDIRECT;
	}

	@Override
	public String detail() {
		setReadOnly(true);
		return SUCCESS;
	}

	@Override
	public String delete() {
		try {
			basicDAOFactory.getBranchDAO().delete(branchId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setMappedRequest(Constants.PARTIAL_LIST);
		return Constants.REDIRECT;
	}

	public long getBranchId() {
		return branchId;
	}

	public void setBranchId(long branchId) {
		this.branchId = branchId;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getNameX() {
		return nameX;
	}

	public void setNameX(String nameX) {
		this.nameX = nameX;
	}

	public List<BranchClass> getBranchClasses() {
		return branchClasses;
	}

	public void setBranchClasses(List<BranchClass> branchClasses) {
		this.branchClasses = branchClasses;
	}

	public List<BranchList> getBranchLists() {
		return branchLists;
	}

	public void setBranchLists(List<BranchList> branchLists) {
		this.branchLists = branchLists;
	}

	public String getCodeX() {
		return codeX;
	}

	public void setCodeX(String codeX) {
		this.codeX = codeX;
	}

	public String getCityX() {
		return cityX;
	}

	public void setCityX(String cityX) {
		this.cityX = cityX;
	}

	public Long getBranchClassIdX() {
		return branchClassIdX;
	}

	public void setBranchClassIdX(Long branchClassIdX) {
		this.branchClassIdX = branchClassIdX;
	}

	public Long getBranchTypeIdX() {
		return branchTypeIdX;
	}

	public void setBranchTypeIdX(Long branchTypeIdX) {
		this.branchTypeIdX = branchTypeIdX;
	}

	public List<ComboBoxModel> getBranchParents() {
		return branchParents;
	}

	public void setBranchParents(List<ComboBoxModel> branchParents) {
		this.branchParents = branchParents;
	}

	public List<ComboBoxModel> getBranchTypes() {
		return branchTypes;
	}

	public void setBranchTypes(List<ComboBoxModel> branchTypes) {
		this.branchTypes = branchTypes;
	}

	public List<TreeNode> getTreeNodes() {
		return treeNodes;
	}

	public void setTreeNodes(List<TreeNode> treeNodes) {
		this.treeNodes = treeNodes;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
	
	

}
