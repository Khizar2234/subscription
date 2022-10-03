package com.ros.administration.model.subscription;

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
import javax.persistence.OneToOne;

import com.ros.administration.model.BaseEntity;
import com.ros.administration.model.Feature;
import com.ros.administration.model.account.User;
import com.ros.administration.model.account.UserPermission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(exclude="subscription")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionFeature extends BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "subscription_feature_id", length = 16)
	private UUID id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="subscription_code",referencedColumnName = "subscription_code")
	private Subscription subscription;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="feature_code",referencedColumnName = "feature_code")
	private Feature feature;


	private boolean subscriptionFeatureActive;

//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name = "subscription_feature_id")
//	private List<SubscriptionFeatureLimit> subscriptionFeaturelimits;
}
