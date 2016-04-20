package com.mpe.basic.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotBlank;

import com.mpe.basic.model.other.LookupCategory;


/**
 * The persistent class for the lookup database table.
 * 
 */
@Entity
@Table(name="lookup",
	uniqueConstraints={@UniqueConstraint(columnNames={"category","name"})}
)
/*
@org.hibernate.annotations.Table(
		appliesTo="lookup",
		indexes={@Index(name="IDX_LOOKUP",columnNames={"category","name"})}
)*/
@BatchSize(size=100)
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)

/*@NamedNativeQuery(name="findAllSimple",query="select lookup_id, name from lookup",resultSetMapping="x")
@SqlResultSetMapping(name="x",
	entities={@EntityResult(entityClass=com.mpe.common.model.ComboBoxModel.class, 
		fields={
			@FieldResult(name="key", column="lookup_id"),
			@FieldResult(name="name", column="name")
	})})*/

public class Lookup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(allocationSize=1,initialValue=1,name="lookup_seq",sequenceName="lookup_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="lookup_seq")
	@Column(name="lookup_id", nullable=false)
	private long lookupId;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(nullable=false, length=128)
	private LookupCategory category;

	@NotBlank
	@Column(nullable=false, length=128)
	private String code;

	@NotBlank
	@Column(nullable=false, length=255)
	private String name;

	/*@NotBlank(message="{label.shortNameCantBlank}")
	@Column(name="short_name", nullable=false, length=128)
	private String shortName;*/
	
	@Column
	String description;

    public Lookup() {
    }

	public long getLookupId() {
		return this.lookupId;
	}

	public void setLookupId(long lookupId) {
		this.lookupId = lookupId;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LookupCategory getCategory() {
		return category;
	}

	public void setCategory(LookupCategory category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
}