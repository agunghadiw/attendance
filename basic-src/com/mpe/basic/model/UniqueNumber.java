package com.mpe.basic.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: UniqueNumber
 *
 */
@Entity
@Table(name="unique_number",
	indexes=@Index(columnList="running_number_id,start_number,organization_id,branch_id,number_date",name="IDX_UNIQUE_NUMBER",unique=true)
)

public class UniqueNumber implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(allocationSize=1,initialValue=1,name="unique_number_seq",sequenceName="unique_number_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="unique_number_seq")
	@Column(name="unique_number_id")
	long uniqueNumberId;
	
	@Column(name="running_number_id")
	Long runningNumberId;
	
	@Column(name="start_number", length=30)
	String startNumber;
	
	@Column(name="organization_id")
	Long organizationId;
	
	@Column(name="branch_id")
	Long branchId;
	
	@Temporal(TemporalType.DATE)
	@Column(name="number_date")
	Date numberDate = new Date();

	public UniqueNumber() {
		super();
	}

	public long getUniqueNumberId() {
		return uniqueNumberId;
	}

	public void setUniqueNumberId(long uniqueNumberId) {
		this.uniqueNumberId = uniqueNumberId;
	}

	public Long getRunningNumberId() {
		return runningNumberId;
	}

	public void setRunningNumberId(Long runningNumberId) {
		this.runningNumberId = runningNumberId;
	}

	public String getStartNumber() {
		return startNumber;
	}

	public void setStartNumber(String startNumber) {
		this.startNumber = startNumber;
	}

	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public Long getBranchId() {
		return branchId;
	}

	public void setBranchId(Long branchId) {
		this.branchId = branchId;
	}

	public Date getNumberDate() {
		return numberDate;
	}

	public void setNumberDate(Date numberDate) {
		this.numberDate = numberDate;
	}
	
	
   
}
