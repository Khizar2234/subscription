package com.ros.administration.model;

import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Data
@MappedSuperclass
public abstract class BaseEntity{
	

	@Temporal(value = TemporalType.DATE)
	private Date createdDate;

	private String createdBy;

	@Temporal(value = TemporalType.DATE)
	private Date lastModifiedDate;

	private String updatedBy;

	public void addMetaData() {

		this.setCreatedDate(new Date());
		this.setCreatedBy("user");
	}
	
	public void addMetaData(String username) {

		this.setCreatedDate(new Date());
		this.setCreatedBy(username);
	}
	
	
	public void editMetaData() {

		this.setUpdatedBy("user");
		this.setLastModifiedDate(new Date());
	}
	
	public void editMetaData(String username) {

		this.setUpdatedBy(username);
		this.setLastModifiedDate(new Date());
	}
}
