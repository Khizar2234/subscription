package com.ros.administration.model.account;

import com.ros.administration.model.Address;
import com.ros.administration.model.BaseEntity;
import com.ros.administration.model.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.net.URI;
import java.util.List;
import java.util.UUID;

@Data
@EqualsAndHashCode(exclude =  "restaurants")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Client extends BaseEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "client_id",length = 16)
	private UUID id;
	
	@Column(name = "business_name")
	private String name;

	@Column(name = "legal_name")
	private String legalName;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "client_id")
	private List<Restaurant> restaurants;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_id")
	private Account account;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "address_id")
	private Address address;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "primary_contact_id")
	private PrimaryContact primaryContact;

}
