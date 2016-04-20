package com.mpe.basic.action;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.mpe.basic.model.Lookup;
import com.mpe.basic.model.Role;
import com.mpe.basic.model.Permission;
import com.mpe.basic.model.dao.BasicDAOFactory;
import com.mpe.basic.model.dao.BasicDAOFactoryHibernate;
import com.mpe.basic.model.other.LookupCategory;
import com.mpe.common.action.BaseAction;
import com.mpe.common.util.Constants;
import com.mpe.common.util.Pager;
import com.mpe.common.util.PartialList;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;
import com.opensymphony.xwork2.validator.annotations.ValidatorType;
/**
 * @author Agung Hadiwaluyo
 *
 */
public class RoleAction extends BaseAction implements Preparable, ModelDriven<Role> {

	private static final long serialVersionUID = 1L;
	
	BasicDAOFactory basicDAOFactory = BasicDAOFactory.instance(BasicDAOFactoryHibernate.class);
	
	Role role;
	List<Role> roleList;
	List<Permission> availablePermissionList;
	List<Permission> choosenPermissionList;
	long[] availablePermissionIds;
	long[] choosenPermissionIds;
	long roleId, userGroupId;
	String roleNameX = "";
	List<Lookup> userGroupList;
	Long userGroupIdX;
	
	List<Role> roleEnvers;
	
	@Override
	public String cancel() {
		setMappedRequest(Constants.PARTIAL_LIST);
		return Constants.REDIRECT;
	}
	
	public String popUpEnvers() {
		try {
			roleEnvers = basicDAOFactory.getRoleDAO().enversById(roleId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}

	@Override
	public String add() {
		try {
			setPreviousPage(Constants.ADD);
			setMappedRequest(Constants.SAVE);
		} catch (Exception e) {
		}
		return SUCCESS;
	}

	@Override
	public String delete() {
		try {
			basicDAOFactory.getRoleDAO().delete(roleId);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
	public String edit() {
		try {
			setPreviousPage(Constants.EDIT);
			setMappedRequest(Constants.UPDATE);
		} catch (Exception e) {
		}
		return SUCCESS;
	}

	@Override
	public Role getModel() {
		return role;
	}

	@Override
	public String list() {
		try {
			roleList = basicDAOFactory.getRoleDAO().findAll(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		setMappedRequest(Constants.LIST);
		return Constants.LIST;
	}

	@Override
	public String partialList() {
		setPreviousPage(Constants.PARTIAL_LIST);
		try {
			PartialList<Role> partialList = basicDAOFactory.getRoleDAO().findByCriteria(getStart(), getCount(), 
					getAscDesc().equalsIgnoreCase("asc")?Order.asc(getOrderBy().length()>0?getOrderBy():"roleId"):Order.desc(getOrderBy().length()>0?getOrderBy():"roleId"), 
					roleNameX.length()>0?Restrictions.like("roleName", roleNameX,MatchMode.ANYWHERE):Restrictions.ne("roleId", new Long(-1)),
					userGroupIdX!=null?Restrictions.eq("userGroup.lookupId", userGroupIdX):Restrictions.ne("roleId", new Long(-1))
			);
			roleList = partialList.getList();
			setPager(Pager.generatePager(getStart(), getCount(), partialList.getTotal()));
			setPagerItem(Pager.generatePagerItem(getStart(), getCount(), partialList.getTotal()));
		} catch (Exception e) {
			addActionError(e.getMessage());
			return INPUT;
		}
		setMappedRequest(Constants.PARTIAL_LIST);
		return SUCCESS;
	}
	
	@Validations(
		requiredStrings={
				@RequiredStringValidator(type=ValidatorType.SIMPLE,fieldName="roleName",trim=true,key="validation.roleName.required")
		}
	)

	@Override
	public String save() {
		try {
			if (choosenPermissionIds!=null) {
				List<Long> longList = new LinkedList<Long>();
				for (int i=0; i<choosenPermissionIds.length; i++) {
					//Permission permission = basicDAOFactory.getPermissionDAO().findById(choosenPermissionIds[i]);
					longList.add(new Long(choosenPermissionIds[i]));
				}
				List<Permission> permissionList = basicDAOFactory.getPermissionDAO().findAll(Order.asc("permissionId"));
				for (Permission permission : permissionList) {
					for (Long l : longList) {
						if(permission.getPermissionId()==l.longValue()){
							role.getPermissions().add(permission);
							break;
						}
					}
				}
			}
			if (userGroupId > 0) {
				Lookup userGroup = basicDAOFactory.getLookupDAO().findById(userGroupId);
				role.setUserGroup(userGroup);
			} else role.setUserGroup(null);
			basicDAOFactory.getRoleDAO().save(role);
		} catch (Exception e) {
			addActionError(e.getMessage());
			return INPUT;
		}
		setMappedRequest(Constants.PARTIAL_LIST);
		return Constants.REDIRECT;
	}

	@Override
	public String update() {
		try {
			// remove previous
			role.getPermissions().clear();
			if (choosenPermissionIds!=null) {
				List<Long> longList = new LinkedList<Long>();
				for (int i=0; i<choosenPermissionIds.length; i++) {
					//Permission permission = basicDAOFactory.getPermissionDAO().findById(choosenPermissionIds[i]);
					longList.add(new Long(choosenPermissionIds[i]));
				}
				List<Permission> permissions = new LinkedList<Permission>();
				List<Permission> permissionList = basicDAOFactory.getPermissionDAO().findAll(Order.asc("permissionId"));
				for (Permission permission : permissionList) {
					for (Long l : longList) {
						if(permission.getPermissionId()==l.longValue()){
							//role.getPermissions().add(permission);
							permissions.add(permission);
							break;
						}
					}
				}
				role.setPermissions(permissions);
			}
			if (userGroupId > 0) {
				Lookup userGroup = basicDAOFactory.getLookupDAO().findById(userGroupId);
				role.setUserGroup(userGroup);
			} else role.setUserGroup(null);
			basicDAOFactory.getRoleDAO().update(role);
		} catch (Exception e) {
			addActionError(e.getMessage());
			return INPUT;
		}
		setMappedRequest(Constants.PARTIAL_LIST);
		return Constants.REDIRECT;
	}

	@Override
	public void prepare() throws Exception {
		if (roleId==0) {
			role = new Role();
			choosenPermissionList = role.getPermissions();
		} else {
			try {
				Session session = basicDAOFactory.getRoleDAO().getSession(Constants.HIBERNATE_CFG_KEY_1);
				role = (Role) session.createCriteria(Role.class)
					.add(Restrictions.eq("roleId", new Long(roleId)))
					.setFetchMode("permissions", FetchMode.SELECT)
					.uniqueResult();
				userGroupId = role.getUserGroup()!=null?role.getUserGroup().getLookupId():0;
				choosenPermissionList = role.getPermissions();
				choosenPermissionIds = new long[choosenPermissionList.size()];
				int i=0;
				Iterator<Permission> iterator = role.getPermissions().iterator();
				while (iterator.hasNext()) {
					Permission permission = (Permission) iterator.next();
					choosenPermissionIds[i] = permission.getPermissionId();
					i++;
				}
				session.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			//logger.info(" choosenPermissionList >> "+choosenPermissionList.size());
		}
		try {
			if (availablePermissionList==null) {
				availablePermissionList = new LinkedList<Permission>();
				List<Permission> list = basicDAOFactory.getPermissionDAO().findAll(Order.asc("permissionId"));
				for (Permission permission : list) {
					boolean same = false;
					for (Permission permission2 : choosenPermissionList) {
						if (permission.getPermissionId()==permission2.getPermissionId()) {
							same = true;
							break;
						}
					}
					if (!same) availablePermissionList.add(permission);
				}
			}
			if (userGroupList==null) userGroupList = basicDAOFactory.getLookupDAO().findByCategory(LookupCategory.USER_GROUP);
		} catch(Exception exception){}
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public List<Permission> getAvailablePermissionList() {
		return availablePermissionList;
	}

	public void setAvailablePermissionList(List<Permission> availablePermissionList) {
		this.availablePermissionList = availablePermissionList;
	}

	public List<Permission> getChoosenPermissionList() {
		return choosenPermissionList;
	}

	public void setChoosenPermissionList(List<Permission> choosenPermissionList) {
		this.choosenPermissionList = choosenPermissionList;
	}

	public long[] getAvailablePermissionIds() {
		return availablePermissionIds;
	}

	public void setAvailablePermissionIds(long[] availablePermissionIds) {
		this.availablePermissionIds = availablePermissionIds;
	}

	public long[] getChoosenPermissionIds() {
		return choosenPermissionIds;
	}

	public void setChoosenPermissionIds(long[] choosenPermissionIds) {
		this.choosenPermissionIds = choosenPermissionIds;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public String getRoleNameX() {
		return roleNameX;
	}

	public void setRoleNameX(String roleNameX) {
		this.roleNameX = roleNameX;
	}
	
	

	public long getUserGroupId() {
		return userGroupId;
	}

	public void setUserGroupId(long userGroupId) {
		this.userGroupId = userGroupId;
	}

	public Long getUserGroupIdX() {
		return userGroupIdX;
	}

	public void setUserGroupIdX(Long userGroupIdX) {
		this.userGroupIdX = userGroupIdX;
	}

	public List<Lookup> getUserGroupList() {
		return userGroupList;
	}

	public void setUserGroupList(List<Lookup> userGroupList) {
		this.userGroupList = userGroupList;
	}

	public List<Role> getRoleEnvers() {
		return roleEnvers;
	}

	public void setRoleEnvers(List<Role> roleEnvers) {
		this.roleEnvers = roleEnvers;
	}
	
	
	

}
