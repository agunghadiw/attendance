package com.mpe.basic.model;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: LocationOther
 *
 */
@Entity
@Table(name="location_other")

public class LocationOther implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	/*
	 * FOR OTHER INFO RELATED WITH :
	 * AREA - PHONECODE
	 * BII DATI-II CODE
	 * 
	 */
	
	@Id
	@SequenceGenerator(allocationSize=1,initialValue=1,name="location_other_seq",sequenceName="location_other_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="location_other_seq")
	@Column(name="location_other_id")
	long locationOtherId;
	
	@ManyToOne
	@JoinColumn(name="location_other_type_id",nullable=false)
	Lookup locationOtherType;
	
	@ManyToOne
	@JoinColumn(name="location_id")
	Location location;
	
	@Column(length=10)
	String code;
	

	public LocationOther() {
		super();
	}


	public long getLocationOtherId() {
		return locationOtherId;
	}


	public void setLocationOtherId(long locationOtherId) {
		this.locationOtherId = locationOtherId;
	}


	public Lookup getLocationOtherType() {
		return locationOtherType;
	}


	public void setLocationOtherType(Lookup locationOtherType) {
		this.locationOtherType = locationOtherType;
	}


	public Location getLocation() {
		return location;
	}


	public void setLocation(Location location) {
		this.location = location;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}
	
	
   
}
