package com.mpe.basic.model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Size;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.ListIndexBase;
import org.hibernate.annotations.Type;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Entity implementation class for Entity: Role
 *
 */
@Entity
@Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
@Table(name="role",
	uniqueConstraints={@UniqueConstraint(columnNames={"role_name"})}
)
@BatchSize(size=100)
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)

public class Role implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(allocationSize=1,initialValue=1,name="role_seq",sequenceName="role_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="role_seq")
	@Column(name="role_id",nullable=false)
	long roleId;
	
	@NotBlank(message="Role name can't blank")
	@Column(length=128,nullable=false,name="role_name")
	String roleName;
	
	// ada disini => karena 1 user dapat handle lebih dari 1 task/flow
	@ManyToOne
	@JoinColumn(name="user_group_id")
	Lookup userGroup;
	
	@NotAudited
	@NotEmpty(message="Permission can't empty")
	@Size(min=2,message="Select min home and logoff")
	@ManyToMany(fetch=FetchType.LAZY)
	//@Cache(usage=CacheConcurrencyStrategy.READ_ONLY)
	@JoinTable(
		name = "role_permission",
		joinColumns = {@JoinColumn(name = "role_id")},
		inverseJoinColumns = {@JoinColumn(name = "permission_id")},
		uniqueConstraints={@UniqueConstraint(columnNames={"role_id","permission_id"})}
	)
	@ListIndexBase(value=0)
	@OrderColumn(name="permission_seq")
	//@Fetch(FetchMode.SELECT)
	@BatchSize(size=100)
	List<Permission> permissions = new LinkedList<Permission>();
	
	@Type(type="true_false")
	@Column(name="is_show_dashboard",length=1)
	boolean showDashboard = false;
	
	@Type(type="true_false")
	@Column(name="is_show_hidden_value",length=1)
	boolean showHiddenValue = false;
	
	@Column(name="default_user_pass_duration")
	Integer defaultUserPassDuration;
	
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

	public Role() {
		super();
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Lookup getUserGroup() {
		return userGroup;
	}

	public void setUserGroup(Lookup userGroup) {
		this.userGroup = userGroup;
	}

	public boolean isShowDashboard() {
		return showDashboard;
	}

	public void setShowDashboard(boolean showDashboard) {
		this.showDashboard = showDashboard;
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

	public void setCreateOn(Date createOn) {
		this.createOn = createOn;
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

	public void setChangeOn(Date changeOn) {
		this.changeOn = changeOn;
	}

	public boolean isShowHiddenValue() {
		return showHiddenValue;
	}

	public void setShowHiddenValue(boolean showHiddenValue) {
		this.showHiddenValue = showHiddenValue;
	}

	public Integer getDefaultUserPassDuration() {
		return defaultUserPassDuration;
	}

	public void setDefaultUserPassDuration(Integer defaultUserPassDuration) {
		this.defaultUserPassDuration = defaultUserPassDuration;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	
	
   
}
