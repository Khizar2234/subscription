package com.ros.administration.repository;


import com.ros.administration.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {

//	@Query("select u from Restaurant u where u.name = :restaurantname")
//	Restaurant findByRestaurantname(String restaurantname);

//	@Query(value="select * from permission p where p.role_id=:roleId or p.restaurant_permission_id in (select u=:restaurantPermissionId",nativeQuery = true)
//	List<Permission>findAllPermissionsForRestaurant(@Param("roleId")UUID roleId,@Param("restaurantPermissionId")UUID restaurantPermissionId);

	@Query("select u from Restaurant u where u.id = :id")
	Restaurant findByUUID(@Param("id") UUID id);

	@Query("select u from Restaurant u where u.client.id = :id")
	List<Restaurant> findByClientUUID(@Param("id") UUID id);

	@Query("select u from Restaurant u ")
	List<Restaurant> getAllRestaurants();

    @Transactional
    @Modifying
    @Query(value="insert into user_restaurant values(:user_id, :restaurant_id)",nativeQuery = true)
    void addRestaurantToUser(@Param("user_id") UUID userId, @Param("restaurant_id") UUID restaurantId);

    @Query(value="SELECT EXISTS(SELECT * FROM user_restaurant WHERE user_id = :userId and restaurant_id =:restaurantId)",nativeQuery=true)
	boolean checkIfUserRestaurantExists(@Param("userId")UUID userId,@Param("restaurantId") UUID restaurantId);
    
    @Transactional
    @Modifying
    @Query(value="update restaurant_config set currency_id= :currencyId where restaurant_config_id =:restaurantConfigId",nativeQuery = true)
	void saveCurrency(@Param("currencyId")UUID currencyId,@Param("restaurantConfigId")UUID restaurantConfigId);
    
    @Transactional
    @Modifying
    @Query(value="delete from user_restaurant where user_id=:user_id and restaurant_id=:restaurant_id",nativeQuery = true)
	void deleteUserRestaurant(@Param("user_id") UUID userId, @Param("restaurant_id") UUID restaurantId);

    @Query(value = "select * from restaurant limit :limit offset :offset", nativeQuery = true)
	List<Restaurant> findPagewiseRestaurants(@Param("limit")int limit, @Param("offset") int offset);
    
    @Transactional
    @Modifying
    @Query(value="delete from department where department_id=:departmentId",nativeQuery = true)
	void deleteDepartmentById(@Param("departmentId") UUID departmentId);

    @Transactional
    @Modifying
    @Query(value="delete from restaurant where restaurant_id=:restaurantId",nativeQuery = true)
	void deleteRestaurant( @Param("restaurantId") UUID restaurantId);

   

    @Query(value="select * from restaurant where restaurant.client_id=:clientId",nativeQuery=true)
    List<Restaurant> ListOfRestaurentsByClientId(@Param("clientId")UUID clientId);
}
