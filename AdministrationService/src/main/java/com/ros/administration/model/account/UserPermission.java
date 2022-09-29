package com.ros.administration.model.account;

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
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.ros.administration.model.BaseEntity;
import com.ros.administration.model.Feature;
import com.ros.administration.model.subscription.SubscriptionFeature;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class UserPermission implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_permission_id", length = 16)
	private UUID id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumns({
		@JoinColumn(name="feature_code", referencedColumnName = "feature_code"),
		@JoinColumn(name="subscription_code", referencedColumnName = "subscription_code")
	})
	private SubscriptionFeature subscriptionFeature;
	
	private boolean permitted;
}
