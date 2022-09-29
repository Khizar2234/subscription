package com.ros.administration.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ros.administration.model.account.Account;
import com.ros.administration.model.account.AccountSubscription;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
	@Query(value ="Select count(*) from account_subscription where subscription_code = :subscriptionCode",nativeQuery = true)
	int checkAccountSubscriptionExists(@Param("subscriptionCode") String subscriptionCode);
	
	@Query(value="select * from account_subscription where account_id = :accountId and subscription_id= :subscriptionId", nativeQuery = true)
	AccountSubscription findAccountSubscription(@Param("accountId") UUID accountId,@Param("subscriptionId") UUID subscriptionId);

	@Query(value = "select * from account where account_email=\'SuperAdmin@Ros.com\'", nativeQuery = true)
	Optional<Account> getROSTeamAccount();
}
