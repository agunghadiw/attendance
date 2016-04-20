package com.mpe.basic.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.mpe.basic.model.other.WorkOffDayType;

/**
 * Entity implementation class for Entity: WorkOffDay
 *
 */
@Entity
@Table(name="work_off_day",
	//uniqueConstraints={@UniqueConstraint(columnNames={"event_date"})},
	indexes={@Index(columnList="event_date", unique=true)}
)
/*@org.hibernate.annotations.Table(
	appliesTo="work_off_day", 
	indexes=@org.hibernate.annotations.Index(name="IDX_WORK_OFF_DAY",columnNames={"event_date","event_name"})
)*/

public class WorkOffDay implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(allocationSize=1,initialValue=1,name="work_off_day_seq",sequenceName="work_off_day_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="work_off_day_seq")
	@Column(name="work_off_day_id")
	long workOffDayId;
	
	@Enumerated(EnumType.STRING)
	@Column(name="work_off_day_type",length=5)
	WorkOffDayType workOffDayType;
	
	@NotNull(message="Event date can't blank")
	@Temporal(TemporalType.DATE)
	@Column(name="event_date",nullable=false)
	Date eventDate;
	
	@NotBlank(message="Event name can't blank")
	@Column(name="event_name",length=32, nullable=false)
	String eventName;
	
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

	public WorkOffDay() {
		super();
	}

	public long getWorkOffDayId() {
		return workOffDayId;
	}

	public void setWorkOffDayId(long workOffDayId) {
		this.workOffDayId = workOffDayId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
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

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public WorkOffDayType getWorkOffDayType() {
		return workOffDayType;
	}

	public void setWorkOffDayType(WorkOffDayType workOffDayType) {
		this.workOffDayType = workOffDayType;
	}
	
	
   
}
