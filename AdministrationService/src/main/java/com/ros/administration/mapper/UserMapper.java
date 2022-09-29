package com.ros.administration.mapper;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ros.administration.controller.dto.account.user.AllUserPermissionsDto;
import com.ros.administration.controller.dto.account.user.DetailedUserInfoDto;
import com.ros.administration.controller.dto.account.user.UserAddDto;
import com.ros.administration.controller.dto.account.user.UserDetailsDto;
import com.ros.administration.controller.dto.account.user.UserDto;
import com.ros.administration.controller.dto.account.user.UserInfoDto;
import com.ros.administration.controller.dto.account.user.UserPermissionDto;
import com.ros.administration.controller.dto.account.user.UserRestaurantDto;
import com.ros.administration.controller.dto.account.user.UserStatusDto;
import com.ros.administration.model.Country;
import com.ros.administration.model.Feature;
import com.ros.administration.model.account.Role;
import com.ros.administration.model.account.User;
import com.ros.administration.model.account.UserPermission;
import com.ros.administration.model.enums.ERole;
import com.ros.administration.model.subscription.SubscriptionFeature;
import com.ros.administration.repository.FeatureRepository;
import com.ros.administration.repository.RoleRepository;
import com.ros.administration.repository.SubscriptionFeatureRepository;


/**
 * 
 * This interface converts user entities to dtos
 *
 */
@Mapper
//(componentModel="spring")
@Component
public abstract class UserMapper {
	
	
	@Autowired
	protected FeatureRepository featureRepository;
	
	@Autowired
	protected RoleRepository roleRepository;
	
	@Autowired
	protected SubscriptionFeatureRepository subscriptionFeatureRepository;
	
//	UserMapper mapper = Mappers.getMapper(UserMapper.class);

	public abstract AllUserPermissionsDto convertToAllUserPermissionsDto(User user);

//	@Mapping(target = "account.accountStatus",source = "account.accountStatus.status")
	public abstract UserDto convertToUserDto(User user);
	
//	@Mapping(target = "account.accountStatus.status",source = "account.accountStatus")
	public abstract User convertToUser(UserDto userDto);

//	DetailedUserInfoDto convertToDetailedtUserInfoDto(User user);

	public abstract UserInfoDto convertToUserInfoDto(User user);

	public abstract User convertUserInfoToUser(UserInfoDto userInfoDto);
	
	public abstract DetailedUserInfoDto convertToDetailedUserInfoDto(User user);

	public abstract User convertDetailedUserInfoToUser(DetailedUserInfoDto detailedUserInfoDto);
	
	@Mapping(target ="accountStatus", source="account.accountStatus")
	@Mapping(target ="accountSubscriptionStatus", source="accountSubscription.status")
	public abstract UserStatusDto convertToUserStatusDto(User user);
	
	
	public abstract UserRestaurantDto convertToUserRestaurantDto(User user);
	
	public abstract UserDetailsDto convertToUserDetailsDto(User user);

	public abstract User convertUserRestaurantDtoToUser(UserRestaurantDto userRestaurantDto);
	
//	@Mapping(target ="account.accountStatus.status", source="account.accountStatus")
	public abstract void updateUser(UserDto userDto,@MappingTarget User user);

	String toString(Country country) {
		if(country!=null) {
		return country.getCountryName();
		}
		return null;
	}
	
	String toString(URI uri) {
		if(uri!=null) {
			return uri.toString();
		}
		return null;
		
	}
	
	URI toURI(String string) {
		URI uri = null;
		try {
			uri = new URI(string);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return uri;
		
	}
	public abstract List<UserDto> convertToUserListDto(List<User> user);

	
	String toString(Feature feature) {
		if (feature!=null && feature.getFeatureCode()!=null) {
		return feature.getFeatureCode();
		}
		else return "";
	}
	
	@Mapping(target ="featureCode",source="subscriptionFeature.feature.featureCode")
	@Mapping(target ="subscriptionCode",source="subscriptionFeature.subscription.subscriptionCode")
	public abstract UserPermissionDto convertToUserPermissionDto(UserPermission userPermission);
	
	@Mapping(target ="subscriptionFeature",expression = "java(toSubscriptionFeature(userPermissionDto.getSubscriptionCode(),userPermissionDto.getFeatureCode()))")
	public abstract UserPermission convertToUserPermission(UserPermissionDto userPermissionDto);
	
	public abstract List<UserPermissionDto> convertToUserPermissionDtoList(List<UserPermission> userPermission);
	
	public abstract List<UserPermission> convertToUserPermissionList(List<UserPermissionDto> userPermissionDto);
	
	public abstract User convertUserAddDtoToUser(UserAddDto userAddDto);
	
	
	SubscriptionFeature toSubscriptionFeature(String subscriptionCode, String featureCode){
		Optional<SubscriptionFeature> subscriptionFeature = subscriptionFeatureRepository.findSubscriptionFeatureForUserPermission(subscriptionCode, featureCode);
		if(subscriptionFeature.isPresent()) {
			return subscriptionFeature.get();
		}else {
			return null;
		}
		
	}
	
	Feature toFeature(String featureCode) {
		Optional<Feature> feature=featureRepository.findByFeatureCode(featureCode);
		if(feature.isPresent()) {
			return feature.get();
		}else {
			return null;
		}
	}

	public Role ToRole(ERole name) {
	Optional<Role> role = roleRepository.findByName(name);
	if(role.isPresent()) {
		return role.get();
	}else {
		 Role newRole = new Role(UUID.randomUUID(),name,"");
		 newRole = roleRepository.save(newRole);
		 System.out.println(newRole);
		 role=roleRepository.findByName(newRole.getName());
		 return role.get();
	}
	}

	
}
