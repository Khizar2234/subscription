package com.ros.administration.model.account;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;

import com.ros.administration.model.BaseEntity;
import com.ros.administration.model.Restaurant;
import com.ros.administration.model.enums.EStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(exclude = {"restaurants","account"})
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "\"User\"")
public class User extends BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id", length = 16)
	private UUID id;

	@Column(unique = true)
	private String username;

	private String code;
	private UUID AzureUUID;

	private String azureUPN;

	private String recordCreatedBy;

	private String modifiedBy;

	private String email;
	
	private String userPinHash;

	@Enumerated(EnumType.STRING)
	@Column(name = "user_status")
	private EStatus userStatus;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "profile_id")
	private UserProfile userProfile;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "role_id")
	private Role role;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id")
	private List<UserPermission> userPermissions;

//	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//	@JoinTable(name = "user_account_subscription", joinColumns = {
//			@JoinColumn(name = "user_id") }, inverseJoinColumns = {
//					@JoinColumn(name = "account_subscription_id") })
//	private Set<AccountSubscription> accountSubscriptions;

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "account_id")
	private Account account;

	@OneToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH })
	@JoinColumn(name = "account_subscription_id")
	private AccountSubscription accountSubscription;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_restaurant", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
			@JoinColumn(name = "restaurant_id") })
	private Set<Restaurant> restaurants;

}