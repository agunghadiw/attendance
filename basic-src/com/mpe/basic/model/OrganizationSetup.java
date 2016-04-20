package com.mpe.basic.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Entity implementation class for Entity: OrganizationSetup
 *
 */
@Entity
@Table(name="organization_setup")
@BatchSize(size=100)
@Cache(usage=CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)

public class OrganizationSetup implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(allocationSize=1,initialValue=1,name="organization_setup_seq",sequenceName="organization_setup_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="organization_setup_seq")
	@Column(name="organization_setup_id",nullable=false)
	long organizationSetupId;
	
	//@Column(length=2,name="number_of_digit",nullable=false)
	//int numberOfDigit;
	
	@Column(name="default_currency_id")
	Long defaultCurrencyId;
	
	@Temporal(TemporalType.DATE)
	@Column(name="sod_date")
	Date sodDate;

	public OrganizationSetup() {
		super();
	}

	public long getOrganizationSetupId() {
		return organizationSetupId;
	}

	public void setOrganizationSetupId(long organizationSetupId) {
		this.organizationSetupId = organizationSetupId;
	}

	public Long getDefaultCurrencyId() {
		return defaultCurrencyId;
	}

	public void setDefaultCurrencyId(Long defaultCurrencyId) {
		this.defaultCurrencyId = defaultCurrencyId;
	}

	public Date getSodDate() {
		return sodDate;
	}

	public void setSodDate(Date sodDate) {
		this.sodDate = sodDate;
	}

	/*public int getNumberOfDigit() {
		return numberOfDigit;
	}

	public void setNumberOfDigit(int numberOfDigit) {
		this.numberOfDigit = numberOfDigit;
	}*/

	
	
   
}
