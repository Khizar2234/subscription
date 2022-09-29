package com.ros.administration.model.account;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ros.administration.model.BaseEntity;
import com.ros.administration.model.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserProfile extends BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "profile_id", length = 16)
	private UUID id;

	private String firstName;
	private String lastName;
	private String email;
	private Long phoneNo;
	private String personalEmail;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Temporal(value = TemporalType.DATE)
	private Date dob;

	@Column(name = "image_url")
	private String imageURL;

}
