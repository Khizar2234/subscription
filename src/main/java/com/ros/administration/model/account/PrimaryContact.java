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
@Table( name = "primary_contact")
public class PrimaryContact extends BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "primary_contact_id",length = 16)
	private UUID id;

	private String firstName;
	private String middleName;
	private String lastName;
	
	private String primaryContactEmail;
	
	private long primaryContactPhoneNo;
	
	private long primaryContactTelephoneNo;
	
}
