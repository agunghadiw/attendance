package com.mpe.basic.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Entity implementation class for Entity: BranchType
 *
 */
@Entity
@Table(name="branch_type",
	indexes=@Index(columnList="name,parent_id",name="IDX_BRANCH_TYPE"),
	uniqueConstraints={@UniqueConstraint(columnNames={"name"})}
)
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

public class BranchType implements Serializable {
	
	/*
	 * tujuan :
	 * untuk struktur cabang perusahaan
	 * wilayah => cabang => outlet
	 */

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(allocationSize=1,initialValue=1,name="branch_type_seq",sequenceName="branch_type_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="branch_type_seq")
	@Column(name="branch_type_id")
	long branchTypeId;
	
	@Column(name="parent_id")
	Long parentId;
	
	@NotBlank(message="Name of Branch Type can't blank")
	@Column(length=30, nullable=false)
	String name;
	
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

	public BranchType() {
		super();
	}

	public long getBranchTypeId() {
		return branchTypeId;
	}

	public void setBranchTypeId(long branchTypeId) {
		this.branchTypeId = branchTypeId;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
	
	
   
}
