package com.mpe.basic.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Type;

import com.mpe.basic.model.other.RunningNumberPeriode;

/**
 * Entity implementation class for Entity: RunningNumber
 *
 */
@Entity
@Table(name="running_number")
@BatchSize(size=100)

public class RunningNumber implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/*
	 * AUTO-NUMBER data's on unique-number
	 * related with running_number_id FK
	 * 
	 */
	
	@Id
	@SequenceGenerator(allocationSize=1,initialValue=1,name="running_number_seq",sequenceName="running_number_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="running_number_seq")
	@Column(name="running_number_id")
	long runningNumberId;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="running_number_type_id",nullable=false)
	Lookup runningNumberType;
	
	/*
	 * prefix/suffix code accepted :
	 * <DD><MM><MMM><YYYY>
	 * <string>
	 * <branch.code>
	 * <organization.code>
	 */
	
	@Column(length=10)
	String prefix;
	
	@Column(length=10)
	String suffix;
	
	@Column(name="start_number",length=30,nullable=false)
	String startNumber;
	
	@ManyToOne
	@JoinColumn(name="organization_id",nullable=false)
	Organization organization;
	
	@Type(type="true_false")
	@Column(length=1,name="is_branch_separated")
	boolean branchSeparated = false;
	
	/*
	 * reset auto number periode!
	 */
	@Enumerated(EnumType.STRING)
	@Column(name="running_number_periode",length=10)
	RunningNumberPeriode runningNumberPeriode;
	
	@Column(name="current_number",length=30,insertable=false,updatable=true)
	String currentNumber;
	

	public RunningNumber() {
		super();
	}
	
	public RunningNumber(Lookup runningNumberType, String prefix,
			String suffix, String startNumber, Organization organization, 
			boolean branchSeparated, RunningNumberPeriode runningNumberPeriode) {
		super();
		this.runningNumberType = runningNumberType;
		this.prefix = prefix;
		this.suffix = suffix;
		this.startNumber = startNumber;
		this.organization = organization;
		this.branchSeparated = branchSeparated;
		this.runningNumberPeriode = runningNumberPeriode;
	}


	public long getRunningNumberId() {
		return runningNumberId;
	}


	public void setRunningNumberId(long runningNumberId) {
		this.runningNumberId = runningNumberId;
	}


	public Lookup getRunningNumberType() {
		return runningNumberType;
	}


	public void setRunningNumberType(Lookup runningNumberType) {
		this.runningNumberType = runningNumberType;
	}

	public String getPrefix() {
		return prefix;
	}


	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}


	public String getSuffix() {
		return suffix;
	}


	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}


	public Organization getOrganization() {
		return organization;
	}


	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public String getStartNumber() {
		return startNumber;
	}

	public void setStartNumber(String startNumber) {
		this.startNumber = startNumber;
	}

	public String getCurrentNumber() {
		return currentNumber;
	}

	public void setCurrentNumber(String currentNumber) {
		this.currentNumber = currentNumber;
	}

	public boolean isBranchSeparated() {
		return branchSeparated;
	}

	public void setBranchSeparated(boolean branchSeparated) {
		this.branchSeparated = branchSeparated;
	}

	public RunningNumberPeriode getRunningNumberPeriode() {
		return runningNumberPeriode;
	}

	public void setRunningNumberPeriode(RunningNumberPeriode runningNumberPeriode) {
		this.runningNumberPeriode = runningNumberPeriode;
	}
	
	
	
	
   
}
