package com.mpe.basic.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.NotBlank;

import com.mpe.basic.model.other.StatusType;


/**
 * The persistent class for the status database table.
 * 
 */
@Entity
@Table(name="status",
	uniqueConstraints={@UniqueConstraint(columnNames={"code","name","type"})}
)
/*
@org.hibernate.annotations.Table(
		appliesTo="status",
		indexes={@Index(name="IDX_STATUS",columnNames={"code","name","type"})}
)*/
@BatchSize(size=100)
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)

public class Status implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(allocationSize=1,initialValue=1,name="status_seq",sequenceName="status_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="status_seq")
	@Column(name="status_id", nullable=false)
	private long statusId;

	@NotBlank(message="Code can't blank")
	@Column(nullable=false, length=16)
	private String code;

	@NotBlank(message="Name can't blank")
	@Column(nullable=false, length=64)
	private String name;
	
	@NotNull(message="Status type can't blank")
	@Enumerated(EnumType.STRING)
	@Column(nullable=false, length=128)
	private StatusType type;

    public Status() {
    }

	public long getStatusId() {
		return this.statusId;
	}

	public void setStatusId(long statusId) {
		this.statusId = statusId;
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

	public StatusType getType() {
		return type;
	}

	public void setType(StatusType type) {
		this.type = type;
	}
	
	

}