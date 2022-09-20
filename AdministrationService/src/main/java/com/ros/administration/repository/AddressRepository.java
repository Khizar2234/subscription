package com.ros.administration.repository;

import com.ros.administration.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {

//	@Query("select u from Restaurant u where u.name = :restaurantname")
//	Restaurant findByRestaurantname(String restaurantname);

//	@Query(value="select * from permission p where p.role_id=:roleId or p.restaurant_permission_id in (select u=:restaurantPermissionId",nativeQuery = true)
//	List<Permission>findAllPermissionsForRestaurant(@Param("roleId")UUID roleId,@Param("restaurantPermissionId")UUID restaurantPermissionId);

    @Query("select u from Address u where u.id = :id")
    Address findByUUID(@Param("id") UUID id);


}
