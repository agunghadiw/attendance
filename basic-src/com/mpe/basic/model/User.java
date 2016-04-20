package com.mpe.basic.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import com.mpe.common.util.*;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
//@NamedQuery(name="user.findByUserName", query="select u from User u where u.userName like ?")
@Table(name="users",
	indexes=@Index(columnList="user_name,organization_id",name="IDX_USERS",unique=false),
	uniqueConstraints={@UniqueConstraint(columnNames={"user_name"})}
)
@BatchSize(size=100)
/*@org.hibernate.annotations.Table(
	appliesTo="users", 
	indexes=@org.hibernate.annotations.Index(name="IDX_USERS",columnNames={"user_name","organization_id","email"})
)*/
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)

public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(allocationSize=1,initialValue=1,name="users_seq",sequenceName="users_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="users_seq")
	@Column(name="user_id",nullable=false)
	long userId;
	
	@NotNull(message="Username can't blank")
	@Pattern(regexp="((?=.*[0-9a-zA-Z])(?!.*[()+}{;=`~:\\|'?/><,]).{4,})",message="Username min 4 characters or numeric combination")
	//@Pattern(regexp="((?=.*\\d)(?=.*[0-9a-zA-Z])(?!.*[()+}{;=`~:\\|'?/><,]).{5,})",message="Username min 5 characters and numeric combination")
	@Column(length=128,name="user_name",nullable=false)
	String userName;
	
	@NotNull(message="Password can't blank")
	@Column(length=128,name="user_pass",nullable=false)
	String userPass;
	
	@Column(length=1,name="is_active",nullable=false)
	@Type(type="true_false")
	boolean active = true;
	
	/*@NotNull(message="User type can't blank")
	@Enumerated(EnumType.STRING)
	@Column(name="user_type")
	UserType userType;*/
    
    @NotEmpty(message="Role can't empty")
    @Size(min=1,message="Select min 1 role")
    @ManyToMany(fetch=FetchType.EAGER)
    @Fetch(FetchMode.SELECT)
    @JoinTable(
		name = "user_role",
		joinColumns = {@JoinColumn(name = "user_id")},
		inverseJoinColumns = {@JoinColumn(name = "role_id")}
	)
	@BatchSize(size=100)
	@org.hibernate.annotations.OrderBy(clause="role_id asc")
	Set<Role> roles = new HashSet<Role>();
    
    @Transient
    String newUserPass;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="organization_id")
    Organization organization;
    
    @Column(name="branch_id")
    Long branchId;
    
    /*@Type(type="true_false")
    @Column(name="is_automatic_chat", length=1)
    boolean automaticChat = false;*/
    
    /*@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="customer_id")
    Customer customer;*/
    
    /*@Column(name="customer_id")
    Long customerId;
    
    @Column(name="vendor_id")
    Long vendorId;*/
    
    /*@Formula(value="(select v.company from vendor v join employee e on v.vendor_id=e.vendor_id where e.user_id=user_id)")
    String vendorCompany;*/
    
    //@Column(name="full_name",length=128)
    //@Formula(value="(select e.full_name from employee e where e.user_id=user_id and ROWNUM = 1)")
    //String fullName;
    
    //@Email(message="No valid email address")
    @Column(name="email",length=128)
    String email;
    
    //@Pattern(regexp="^((\\+62)\\d{6,})$", message="Mobile number format is +62xxxxxxxx")
    @Column(name="mobile",length=20)
    String mobile;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="last_login_date")
    Date lastLoginDate;
    
    //@Column(name="business_unit_id")
    //Long businessUnitId;
    
    //@Column(name="duration_expired_user_pass",length=2)
    //int durationExpiredUserPass;
    
	@Column(length=128,name="create_by",insertable=true,updatable=false)
	String createBy;
	
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="create_on",insertable=true,updatable=false)
    Date createOn;
    
    @Column(length=128,name="change_by",insertable=false,updatable=true)
    String changeBy;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="change_on",insertable=false,updatable=true)
    Date changeOn;

	public String getNewUserPass() {
		return newUserPass;
	}


	public void setNewUserPass(String newUserPass) {
		this.newUserPass = newUserPass;
		try {
			if (this.newUserPass!=null && this.newUserPass.length()>0) this.userPass = CommonUtil.digest(this.newUserPass);
		} catch (Exception e) {
		}
	}


	public User() {
		super();
	}


	public long getUserId() {
		return userId;
	}


	public void setUserId(long userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}
	
	/*public String getUserNameFullName() {
		return userName + ((fullName!=null && fullName.length()>0)?" - ":"") + fullName;
	}*/


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserPass() {
		return userPass;
	}


	public void setUserPass(String userPass) {
		try {
			if (this.newUserPass!=null && this.newUserPass.length()>0) this.userPass = CommonUtil.digest(this.newUserPass);
			else this.userPass = userPass;
		} catch (Exception e) {
		}
	}


	public boolean isActive() {
		return active;
	}


	public void setActive(boolean active) {
		this.active = active;
	}

	public String getCreateBy() {
		return createBy;
	}


	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}


	public Date getCreateOn() {
		return createOn;
	}

	public String getChangeBy() {
		return changeBy;
	}


	public void setChangeBy(String changeBy) {
		this.changeBy = changeBy;
	}


	public Date getChangeOn() {
		return changeOn;
	}


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
	}


	public void setChangeOn(Date changeOn) {
		this.changeOn = changeOn;
	}


	public Organization getOrganization() {
		return organization;
	}


	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	
	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
	

	public Date getLastLoginDate() {
		return lastLoginDate;
	}


	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}
	
	public Set<Permission> getPermissionAccessByParentChild() {
		Set<Permission> set = new LinkedHashSet<Permission>();
		Set<Permission> level1Set = new LinkedHashSet<Permission>();
		Set<Permission> level2Set = new LinkedHashSet<Permission>();
		//Set<Permission> level3Set = new LinkedHashSet<Permission>();
		for (Role role : getRoles()) {
			for (Permission permission : role.getPermissions()) {
				// level #1
				if (permission.isShow() && permission.getParent()==null) level1Set.add(permission);
				// level #2
				else if (permission.isShow() && permission.getParent()!=null) level2Set.add(permission);
				else if (!permission.isShow()) set.add(permission);
				// level #3
				//else if (permission.isShow()) level3Set.add(permission);		
			}
		}
		for (Permission permission1 : level1Set) {
			// level 1
			for (Permission permission2 : level2Set) {
				// level #2
				if (permission2.getParent()!=null && permission2.getParent().getPermissionId()==permission1.getPermissionId()) {
					for (Permission permission3 : level2Set) {
						// level #3
						if (permission3.getParent()!=null && permission3.getParent().getPermissionId()==permission2.getPermissionId()) {
							permission2.getPermissionChilds().add(permission3);
						}
					}
					permission1.getPermissionChilds().add(permission2);
				}
				
			}
			set.add(permission1);
		}
		return set;
	}


/*	public Set<Permission> getPermissionAccess() {
		Iterator<Role> iterator = getRoles().iterator();
		Set<Permission> set = new LinkedHashSet<Permission>();
		Set<Permission> superSet = new LinkedHashSet<Permission>();
		Set<Permission> masterSet = new LinkedHashSet<Permission>();
		Set<Permission> standartSet = new LinkedHashSet<Permission>();
		while (iterator.hasNext()) {
			Role role = (Role)iterator.next();
			List<Permission> permissionLst = role.getPermissions();
			Iterator<Permission> iterator2 = permissionLst.iterator();
			while (iterator2.hasNext()) {
				Permission permission = (Permission)iterator2.next();
				// level #1
				if (permission.isShow() && permission.getParent()==null) superSet.add(permission);
				// level #2
				else if (permission.isShow() && permission.getParent()!=null) masterSet.add(permission);
				else if (!permission.isShow()) set.add(permission);
				// level #3
				else if (permission.isShow()) standartSet.add(permission);				
			}
		}
		Iterator<Permission> iterator2 = superSet.iterator();
		while (iterator2.hasNext()) {
			// level 1
			Permission permission = (Permission)iterator2.next();
			permission.setChildSum(getChilds(permission, masterSet));
			set.add(permission);
			Iterator<Permission> iterator3 = masterSet.iterator();
			while (iterator3.hasNext()) {
				// level 2
				Permission permission2 = (Permission)iterator3.next();
				if (permission2.getParent()!=null && permission2.getParent().getPermissionId()==permission.getPermissionId()) {
					permission2.setChildSum(getChilds(permission2, masterSet));
					set.add(permission2);
					Iterator<Permission> iterator4 = masterSet.iterator();
					while (iterator4.hasNext()) {
						// level 3
						Permission permission3 = (Permission)iterator4.next();
						if (permission3.getParent()!=null && permission3.getParent().getPermissionId()==permission2.getPermissionId()) {
							permission3.setChildSum(0);
							set.add(permission3);
						}						
					}
				}
			}
		}
		
		//set panjang tampilan
		Iterator<Permission> iterator3 = set.iterator();
		while (iterator3.hasNext()) {
			Permission permission = (Permission) iterator3.next();			
			//cari yang tampil
			if(permission.isShow()){
				int length = permission.getPermissionName().length();				
				if(permission.getParent()!=null){
					iterator2 = set.iterator();
					while (iterator2.hasNext()) {
						Permission permission1 = (Permission) iterator2.next();						
						if(permission1.isShow() && permission1.getParent()!=null && permission1.getParent().getPermissionId()==permission.getParent().getPermissionId() && permission1.getPermissionName().length() > length) length = permission1.getPermissionName().length();
					}
				}
				length = 5 + (6 * length) + 20;				
				permission.setLength(length);
			}
		}
		return set;
	}
	
	public int getChilds(Permission permission, Set<Permission> set) {
		int i = 0;
		if (permission.getChilds()!=null && permission.getChilds().size()>0) {
			Iterator<Permission> iterator = set.iterator();
			while (iterator.hasNext()) {
				Permission child = (Permission)iterator.next();
				if (child.isShow() && child.getParent()!=null && child.getParent().getPermissionId()==permission.getPermissionId()) i++;
			}
		}
		return i;
	}*/
	
	public boolean hasUserGroup() {
		if (getRoles()!=null && getRoles().size()>0) {
			for (Role role : getRoles()) {
				if (role.getUserGroup()!=null) return true;
			}
		}
		return false;
	}
	
	public boolean hasUserGroup(Lookup userGroup) {
		if (getRoles()!=null && getRoles().size()>0) {
			for (Role role : getRoles()) {
				if (role.getUserGroup()!=null && role.getUserGroup().getLookupId()==userGroup.getLookupId()) return true;
			}
		}
		return false;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public Long getBranchId() {
		return branchId;
	}


	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}
	
   
}
