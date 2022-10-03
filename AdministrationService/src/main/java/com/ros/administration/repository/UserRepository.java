package com.ros.administration.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ros.administration.model.account.Account;
import com.ros.administration.model.account.AccountSubscription;
import com.ros.administration.model.account.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

	@Query("select u from User u where u.username = :username")
	User findByUsername(@Param("username") String username);

	User findByEmail(String email);
	
	@Query("select u from User u  where u.role.name = com.ros.administration.model.enums.ERole.ROLE_SUPERADMIN or u.role.name = com.ros.administration.model.enums.ERole.ROLE_ACCOUNTOFFICER or u.role.name = com.ros.administration.model.enums.ERole.ROLE_CLIENTADMIN")
	List<User> findUsersForTablet();

	Boolean existsByUsername(String username);

//	@Query(value="select * from permission p where p.role_id=:roleId or p.user_permission_id in (select u=:userPermissionId",nativeQuery = true)
//	List<Permission>findAllPermissionsForUser(@Param("roleId")UUID roleId,@Param("userPermissionId")UUID userPermissionId);

	Boolean existsByEmail(String email);

	@Query("select u from User u where u.id = :id")
	User findByUUID(@Param("id") UUID id);

	@Modifying
	@Transactional
	@Query(value = "update \"user\" set account_id =:accountId where username = :username", nativeQuery = true)
	void updateUserAccount(@Param("accountId") UUID accountId, @Param("username") String username);

	@Modifying
	@Transactional
	@Query(value = "update \"user\" set account_subscription_id =:accountSubscriptionId where username = :username", nativeQuery = true)
	void updateUserAccountSubscription(@Param("accountSubscriptionId") UUID accountSubscriptionId,
			@Param("username") String username);

//	List<User> getUserByClientID(UUID id);

	@Modifying
	@Transactional
	@Query(value = "delete from \"user\" u using user_profile p where u.profile_id = p.profile_id and u.username=:username ", nativeQuery = true)
	void deleteByUserName(@Param("username") String username);

	@Modifying
	@Transactional
	@Query(value = "delete from user_restaurant where user_id=:userId", nativeQuery = true)
	void deleteUserRestaurantForUser(@Param("userId") UUID userId);

	@Query(value = "select * from \"user\" limit :limit offset :offset", nativeQuery = true)
	List<User> findPagewiseUsers(@Param("limit") int limit, @Param("offset") int offset);

	@Query("select u from User u where u.role.name= \'ROLE_SUPERADMIN\'")
	List<User> findSuperAdmins();
	
	@Modifying
	@Transactional
	@Query(value = "update \"user\"  set account_id =:accountId where user_id=:userId", nativeQuery = true)
	void saveUserAccountData(@Param("userId") UUID userId,@Param("accountId")  UUID accountId);

	@Modifying
	@Transactional
	@Query(value = "update \"user\"  set account_subscription_id =:accounSubscriptiontId where user_id=:userId", nativeQuery = true)
	void saveUserAccountSubscriptionData(@Param("userId") UUID userId,@Param("accounSubscriptiontId")  UUID accountSubscriptionId);


    @Query(value = "select Cast(user_id as varchar) user_id from user_restaurant where restaurant_id=:restaurantId", nativeQuery = true)
    List<UUID> getuserIdsFromRestaurantId(@Param("restaurantId") UUID restaurantId);

}
