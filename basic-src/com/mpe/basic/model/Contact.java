package com.mpe.basic.model;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.BatchSize;

/**
 * Entity implementation class for Entity: Contact
 *
 */
@Entity
@Table(name="contact")
@BatchSize(size=100)

public class Contact implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator(allocationSize=1,initialValue=1,name="contact_seq",sequenceName="contact_seq")
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="contact_seq")
	@Column(name="contact_id")
	long contactId;
	
	@Column(name="telephone_1",length=20)
	String telephone1;
	
	@Column(name="telephone_2",length=20)
	String telephone2;
	
	@Column(name="ext_1",length=5)
	String ext1;
	
	@Column(name="ext_2",length=5)
	String ext2;
	
	@Column(name="fax_1",length=20)
	String fax1;
	
	@Column(name="fax_2",length=20)
	String fax2;
	
	@Column(name="mobile_1",length=20)
	String mobile1;
	
	@Column(name="mobile_2",length=20)
	String mobile2;
	
	@Column(name="email_1",length=128)
	String email1;
	
	@Column(name="email_2",length=128)
	String email2;
	
	public Contact() {
		super();
	}

	public long getContactId() {
		return contactId;
	}

	public void setContactId(long contactId) {
		this.contactId = contactId;
	}

	public String getTelephone1() {
		return telephone1;
	}

	public void setTelephone1(String telephone1) {
		this.telephone1 = telephone1;
	}

	public String getTelephone2() {
		return telephone2;
	}

	public void setTelephone2(String telephone2) {
		this.telephone2 = telephone2;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	public String getFax1() {
		return fax1;
	}

	public void setFax1(String fax1) {
		this.fax1 = fax1;
	}

	public String getFax2() {
		return fax2;
	}

	public void setFax2(String fax2) {
		this.fax2 = fax2;
	}

	public String getMobile1() {
		return mobile1;
	}

	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}

	public String getMobile2() {
		return mobile2;
	}

	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}

	public String getEmail1() {
		return email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getEmail2() {
		return email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	
	
	
   
}
