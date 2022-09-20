package com.ros.administration.model.account;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

import com.ros.administration.model.BaseEntity;
import com.ros.administration.model.Country;
import com.ros.administration.model.enums.ERole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table( name = "client_vat_info")
public class ClientVatInfo extends BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "vat_id",length = 16)
	private UUID id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(referencedColumnName = "country_id")
	private Country country;

	@Column(name = "tax_registration_no",length = 16)
	private String taxRegistrationNo;
	
}
