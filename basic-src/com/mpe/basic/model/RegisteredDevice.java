package com.mpe.basic.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Entity implementation class for Entity: RegisteredDevice
 *
 */
@Entity
@Table(name="registered_device",
	uniqueConstraints={@UniqueConstraint(columnNames={"type","user_id","device_id"})}
)
/*@org.hibernate.annotations.Table(
		appliesTo="registered_device",
		indexes={@Index(name="IDX_REG_DEVICE",columnNames={"type","user_id","device_id"})}
)*/

public class RegisteredDevice implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(allocationSize=1,initialValue=1,name="registered_device_seq",sequenceName="registered_device_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="registered_device_seq")
	@Column(name="registered_device_id")
	long registeredDeviceId;
	
	@Column(name="type",length=30)
	String type;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="registered_date")
	Date registeredDate = new Date();
	
	@NotNull(message="User can't null")
	@Column(name="user_id")
	long userId;
	
	@NotBlank(message="Device ID can't blank")
	@Column(name="device_id",length=32)
	String deviceId;
	
	@Column(name="pass_code",length=20)
	String passCode;
	
	// if pass-code valid => active = true
	@Type(type="true_false")
	@Column(name="is_active",length=1)
	boolean active = false;

	public RegisteredDevice() {
		super();
	}

	public long getRegisteredDeviceId() {
		return registeredDeviceId;
	}

	public void setRegisteredDeviceId(long registeredDeviceId) {
		this.registeredDeviceId = registeredDeviceId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getPassCode() {
		return passCode;
	}

	public void setPassCode(String passCode) {
		this.passCode = passCode;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
   
}
