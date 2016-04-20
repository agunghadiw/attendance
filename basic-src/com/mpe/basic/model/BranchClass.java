package com.mpe.basic.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Entity implementation class for Entity: BranchClass
 *
 */
@Entity
@Table(name="branch_class",uniqueConstraints={@UniqueConstraint(columnNames={"code"})})
/*@org.hibernate.annotations.Table(
	appliesTo="branch_class", 
	indexes=@org.hibernate.annotations.Index(name="IDX_BRANCH_CLASS",columnNames={"code"})
)*/
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

public class BranchClass implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	/*
	 * tujuan :
	 * klasifikasi tingkatan cabang/outlet
	 * 
	 */
	
	@Id
	@SequenceGenerator(allocationSize=1,initialValue=1,name="branch_class_seq",sequenceName="branch_class_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="branch_class_seq")
	@Column(name="branch_class_id")
	long branchClassId;
	
	@NotBlank(message="Code can't blank")
	@Column(length=10)
	String code;
	
	@Column(name="approval_otr")
	double approvalOtr;
	
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

	public BranchClass() {
		super();
	}

	public long getBranchClassId() {
		return branchClassId;
	}

	public void setBranchClassId(long branchClassId) {
		this.branchClassId = branchClassId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getApprovalOtr() {
		return approvalOtr;
	}

	public void setApprovalOtr(double approvalOtr) {
		this.approvalOtr = approvalOtr;
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
