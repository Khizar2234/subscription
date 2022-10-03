package com.ros.administration.controller.dto.account.user;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ros.administration.controller.dto.RestaurantDto;
import com.ros.administration.model.enums.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto {
	private UUID id;

	private String firstName;
	private String lastName;
	private String email;
	private Long phoneNo;
	private String personalEmail;

	private Gender gender;

	private Date dob;

	private String imageURL;

}
