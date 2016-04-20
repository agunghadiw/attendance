package com.mpe.basic.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.BatchSize;

import com.mpe.basic.model.other.LocationType;

/**
 * Entity implementation class for Entity: Location
 *
 */
@Entity
@Table(name="location",
	indexes=@Index(columnList="location_type,parent_id,code,name,postal_code",name="IDX_LOCATION")
)

public class Location implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(allocationSize=1,initialValue=1,name="location_seq",sequenceName="location_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="location_seq")
	@Column(name="location_id")
	long locationId;
	
	@Enumerated(EnumType.STRING)
	@Column(name="location_type",length=20)
	LocationType locationType;
	
	@Column(name="parent_id")
	Long parentId;
	
	@Column(length=10)
	String code;
	
	@Column(length=100)
	String name;
	
	@Column(name="postal_code",length=10)
	String postalCode;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="location",orphanRemoval=true)
	@BatchSize(size=100)
	Set<LocationOther> locationOthers = new HashSet<LocationOther>();

	public Location() {
		super();
	}

	public long getLocationId() {
		return locationId;
	}

	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}

	public LocationType getLocationType() {
		return locationType;
	}

	public void setLocationType(LocationType locationType) {
		this.locationType = locationType;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
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

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public Set<LocationOther> getLocationOthers() {
		return locationOthers;
	}

	public void setLocationOthers(Set<LocationOther> locationOthers) {
		this.locationOthers = locationOthers;
	}
	
	
   
}
