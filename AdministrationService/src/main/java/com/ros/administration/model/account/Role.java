package com.ros.administration.model.account;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.ros.administration.model.BaseEntity;
import com.ros.administration.model.enums.ERole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "role_id",length = 16)
	private UUID id;

	@Enumerated(EnumType.STRING)
	@Column(name = "role_name")
	private ERole name;
	
	@Column(name = "role_code")
	private String code;
	
//	
//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name="role_id")
//	private List<Permission> permissions;
}
