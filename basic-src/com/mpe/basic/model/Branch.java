package com.mpe.basic.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotBlank;


/**
 * Entity implementation class for Entity: Branch
 *
 */
@Entity
@Table(name="branch",
	indexes=@Index(columnList="code,name,organization_id,parent_id,branch_type_id,branch_class_id",name="IDX_BRANCH"),
	uniqueConstraints=@UniqueConstraint(columnNames={"code","name","organization_id","branch_type_id"})
)
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@BatchSize(size=100)

public class Branch implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(allocationSize=1,initialValue=1,name="branch_seq",sequenceName="branch_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="branch_seq")
	@Column(name="branch_id")
	long branchId;
	
	// still need parent-ID for relation with branch/korwil
	@Column(name="parent_id")
	Long parentId;
	
	// munculkan branch-type => ALL!
	@Column(name="branch_type_id")
	Long branchTypeId;
	
	@Column(name="branch_class_id")
	Long branchClassId;
	
	@NotBlank(message="Code of branch can't blank")
	@Column(name="code",length=10,nullable=false)
	String code;
	
	@NotBlank(message="Name of branch can't blank")
	@Column(name="name",length=100,nullable=false)
	String name;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY,orphanRemoval=true)
	@JoinColumn(name="address_id")
	Address address = new Address();
	
	@Column(name="organization_id",nullable=false)
	long organizationId;
	
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

	public Branch() {
		super();
	}

	public long getBranchId() {
		return branchId;
	}

	public void setBranchId(long branchId) {
		this.branchId = branchId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(long organizationId) {
		this.organizationId = organizationId;
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

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getBranchClassId() {
		return branchClassId;
	}

	public void setBranchClassId(Long branchClassId) {
		this.branchClassId = branchClassId;
	}

	public Long getBranchTypeId() {
		return branchTypeId;
	}

	public void setBranchTypeId(Long branchTypeId) {
		this.branchTypeId = branchTypeId;
	}
	
	
   
}
