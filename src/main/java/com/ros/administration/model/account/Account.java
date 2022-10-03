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
import javax.persistence.OneToOne;

import com.ros.administration.model.BaseEntity;
import com.ros.administration.model.Product;
import com.ros.administration.model.enums.EStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(exclude = {"users","defaultUser"})
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Account extends BaseEntity implements Serializable {

	public Account(UUID id, String accountEmail, EStatus accountStatus) {
		super();
		this.id = id;
		this.accountEmail = accountEmail;
		this.accountStatus = accountStatus;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "account_id", length = 16)
	private UUID id;

	@Column(unique = true)
	private String accountEmail;

	@Enumerated(EnumType.STRING)
	private EStatus accountStatus;

	private String clientName;

	@OneToMany(cascade = { CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.DETACH })
	@JoinColumn(name = "account_id")
	private List<AccountSubscription> accountSubscriptions;

//	@OneToMany(cascade = CascadeType.ALL)
//	@JoinColumn(name="account_id")
//	private List<Product> products;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "default_user_id")
	private User defaultUser;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_id")
	private List<User> users;

}
