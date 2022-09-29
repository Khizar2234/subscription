package com.ros.administration.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.ros.administration.model.account.Client;
import com.ros.administration.model.account.ContactInfo;
import com.ros.administration.model.account.ClientVatInfo;
import com.ros.administration.model.account.PrimaryContact;
import com.ros.administration.model.account.User;
import com.ros.administration.model.configuration.RestaurantConfiguration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(exclude = {"users", "client"})
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant extends BaseEntity implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "restaurant_id", length = 16)
	private UUID id;

	@Column(name = "business_name")
	private String name;

	@Column(name = "owner_name")
	private String ownerName;

	@Column(name = "owner_email")
	private String ownerEmail;

	@Column(name = "restaurant_legal_name")
	private String legalName;

	private String clientLegalName;
	
	@Column(name = "restaurant_pin_hash")
	private String restaurantPinHash;
	
	private Boolean tabletValidation;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "client_id")
	private Client client;

	@Column
	private String postalId;

	@Column
	private String physicalId;

	@Column
	private String postalAddressCountry;

	@Column
	private String physicalAddressCountry;

	@Column
	private String postalTaxCode;

	@Column
	private String vatEditableCountryId;

	@Column
	private String physicalTaxCode;

	@Column
	private String vatCountryCode;

	@Column
	private String vatCountryName;

//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "restaurant_id")
//	private List<Address> addresses;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "postal_address_id")
	private Address postalAddress;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "physical_address_id")
	private Address physicalAddress;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "primary_contact_id")
	private PrimaryContact primaryContact;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "contact_info_id")
	private ContactInfo contactInformation;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "vat_info_id")
	private ClientVatInfo vatInfo;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "restaurant_id")
	private List<Department> departments;

	private String country;

	private String taxCountryCode;


	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "restaurant_id")
	private List<RestaurantIntegration> restaurantIntegrations;

	@ManyToMany(mappedBy = "restaurants", cascade = CascadeType.ALL)
	private Set<User> users;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "restaurant_config_id")
	private RestaurantConfiguration restaurantConfiguration;

}
