package com.ros.administration.model.subscription;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.ros.administration.model.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(exclude={"subscriptionFeatures","pricings"})
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Subscription extends BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "subscription_id", length = 16)
	private UUID id;

	@Column(name = "subscription_name")
	private String name;

	@Column(name = "display_name")
	private String displayName;

	@Column(name = "subscription_duration")
	private String subscriptionDuration;

	@Column(name = "product_code")
	private String productCode;

//	@Column(name = "subscription_status")
//	private boolean status;

	private boolean subscriptionActive;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "subscription_code", referencedColumnName = "subscription_code")
	private List<SubscriptionFeature> subscriptionFeatures;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "subscription_package_specification_id")
	private SubscriptionPackageSpecification subscriptionPackageSpecification;

	private String description;


	@Column(name = "subscription_code",unique = true)
	private String subscriptionCode;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "subscription_id")
	private List<Pricing> pricings;

}
