package com.mpe.basic.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.BatchSize;

/**
 * Entity implementation class for Entity: Address
 *
 */
@Entity
@Table(name="address")
@BatchSize(size=100)

public class Address implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(allocationSize=1,initialValue=1,name="address_seq",sequenceName="address_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="address_seq")
	@Column(name="address_id")
	long addressId;
	
	@Column(name="address_1")
	String address1;
	
	@Column(name="address_2")
	String address2;
	
	@Column(name="address_3")
	String address3;
	
	@Column(name="address_4")
	String address4;
	
	@ManyToOne
	@JoinColumn(name="city_id")
	Location city;
	
	@ManyToOne
	@JoinColumn(name="postal_code_id")
	Location postalCode;	
	
	public Address() {
		// default value
		this.address1 = "";
		this.address2 = "";
		this.address3 = "";
		this.address4 = "";
		
	}

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}

	public String getAddress4() {
		return address4;
	}

	public void setAddress4(String address4) {
		this.address4 = address4;
	}

	public Location getCity() {
		return city;
	}

	public void setCity(Location city) {
		this.city = city;
	}

	public Location getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(Location postalCode) {
		this.postalCode = postalCode;
	}
	
	
   
}
