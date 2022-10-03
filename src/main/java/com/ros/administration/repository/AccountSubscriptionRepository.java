package com.ros.administration.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ros.administration.model.account.AccountSubscription;

@Repository
public interface AccountSubscriptionRepository extends JpaRepository<AccountSubscription, UUID> {
	@Query(value="select * from account_subscription where account_id = :accountId and subscription_id= :subscriptionId", nativeQuery = true)
	AccountSubscription findAccountSubscription(@Param("accountId") UUID accountId,@Param("subscriptionId") UUID subscriptionId);

	@Query(value="select * from account_subscription where account_id = :accountId limit 1", nativeQuery = true)
	AccountSubscription getMasterAccountSubscription(@Param("accountId") UUID accountId);
	
    @Query(value = "update account_subscription set status = 'INACTIVE' where account_subscription_id = :accountSubscriptionId limit 1", nativeQuery = true)
    AccountSubscription deactivateSubscription(@Param("accountSubscriptionId") UUID accountId);
}
