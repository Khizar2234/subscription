package com.ros.administration.model.account;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ros.administration.model.BaseEntity;
import com.ros.administration.model.Product;
import com.ros.administration.model.enums.EStatus;
import com.ros.administration.model.subscription.Subscription;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(exclude = "users")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AccountSubscription extends BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "account_subscription_id", length = 16)
	private UUID id;

//	@ManyToMany(mappedBy = "accountSubscriptions", cascade = CascadeType.ALL)
//	private Set<User> users;

	@OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "subscription_id")
	private Subscription subscription;

	private String activatedBy;

	@Temporal(value = TemporalType.DATE)
	private Date activatedDate;

	@Temporal(value = TemporalType.DATE)
	private Date expiryDate;
	
	@Enumerated(EnumType.STRING)
	private EStatus status;
}
