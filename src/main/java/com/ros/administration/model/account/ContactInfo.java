package com.ros.administration.model.account;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

import com.ros.administration.model.BaseEntity;
import com.ros.administration.model.enums.ERole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "client_contact_info")
public class ContactInfo extends BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "client_contact_info_id",length = 16)
	private UUID id;

	
	private String contactEmail;
	
	private long contactPhoneNo;
	
	private long contactTelephoneNo;
	
	private long faxNo;
	
	@Column(name = "website_url")
	private String websiteURL;
	
}
