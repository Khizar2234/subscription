package com.ros.administration.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ros.administration.controller.dto.account.user.AllUserPermissionsDto;
import com.ros.administration.controller.dto.account.user.UserDto;
import com.ros.administration.controller.dto.account.user.UserPermissionDto;
import com.ros.administration.exceptions.PermissionUpdateException;
import com.ros.administration.exceptions.UserNotFoundException;
import com.ros.administration.mapper.UserMapper;
import com.ros.administration.model.account.User;
import com.ros.administration.model.account.UserPermission;
import com.ros.administration.model.subscription.SubscriptionFeature;
import com.ros.administration.repository.SubscriptionFeatureRepository;
import com.ros.administration.repository.UserPermissionsRepository;
import com.ros.administration.repository.UserRepository;
import com.ros.administration.service.SubscriptionService;
import com.ros.administration.service.UserPermissionsService;
import com.ros.administration.util.Properties;

@Service
public class UserPermissionsServiceImpl implements UserPermissionsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserPermissionsRepository userPermissionRepository;
	
	@Autowired
	private SubscriptionService subscriptionService;

	@Autowired
	private SubscriptionFeatureRepository subscriptionFeatureRepository;
	@Override
	public AllUserPermissionsDto getUserPermissionsById(UUID userUUID) throws UserNotFoundException {
		User user = userRepository.findByUUID(userUUID);
		AllUserPermissionsDto  userDTO = userMapper.convertToAllUserPermissionsDto(user);
		return userDTO;
	}

	@Override
	public Boolean saveUserPermissions(UserDto userDTO) throws PermissionUpdateException {
		if(userDTO!=null) {
			/*
			 * if(userDTO.getUserPermissions()!=null &&
			 * !userDTO.getUserPermissions().isEmpty()) { for(UserPermissionDto
			 * userPermissionDto:userDTO.getUserPermissions()) { UserPermission
			 * userPermission=userPermissionRepository.findByUIIDId(userPermissionDto.getId(
			 * )); userPermission.setIspermitted(userPermissionDto.isIspermitted());
			 * userPermission=userPermissionRepository.save(userPermission); return true; }
			 * }
			 */
		}
		return false;
	}

	@Override
	public AllUserPermissionsDto saveUserPermissions(List<UserPermissionDto> userPermissionDtos, UUID userId)
			throws PermissionUpdateException {
		User user = userRepository.getById(userId);
		userPermissionRepository.removeNullPermissions();
		List<UserPermission> userPermissions = userMapper.convertToUserPermissionList(userPermissionDtos);
		user.setUserPermissions(userPermissions);
		user = userRepository.save(user);
		userPermissionRepository.removeNullPermissions();
		user = userRepository.getById(user.getId());
		return userMapper.convertToAllUserPermissionsDto(user);
	}

	@Override
	public AllUserPermissionsDto saveUserPermissions(List<UserPermissionDto> userPermissionDtos, String username)
			throws PermissionUpdateException {
		userPermissionRepository.removeNullPermissions();
		User user = userRepository.findByUsername(username);
		List<UserPermission> userPermissions = userMapper.convertToUserPermissionList(userPermissionDtos);
		user.setUserPermissions(userPermissions);
		userRepository.save(user);
		userPermissionRepository.removeNullPermissions();
		user = userRepository.getById(user.getId());
		return userMapper.convertToAllUserPermissionsDto(user);
	}

	@Override
	public AllUserPermissionsDto configureUserPermissions(List<UserPermissionDto> userPermissionDtos, UUID userUUId) throws UserNotFoundException,PermissionUpdateException{
		// TODO Auto-generated method stub
		userPermissionRepository.removeNullPermissions();
		User user = userRepository.getById(userUUId);
		List<UserPermission> userPermissions = new ArrayList<>();
		if(user!=null) {
			if (user.getUserPermissions()!=null &&user.getUserPermissions().size()!=0) {
				userPermissions = user.getUserPermissions();	
				
			}else {
				userPermissions = userMapper.convertToUserPermissionList(userPermissionDtos);
			}
			user.setUserPermissions(userPermissions);
			user = userRepository.save(user);
			userPermissionRepository.removeNullPermissions();
			user = userRepository.getById(userUUId);
		}
		return userMapper.convertToAllUserPermissionsDto(user);
	}

	@Override
	public String setAllUserPermissions(String username, String subscriptionCode) throws UserNotFoundException {
		User user = userRepository.findByUsername(username);
		List<SubscriptionFeature> subscriptionFeatures;
		List<UserPermission> userPermissions;
		if (user!=null) {
			if(user.getUserPermissions()!=null) {
				userPermissions =user.getUserPermissions() ;
			}else {
				userPermissions	 = new ArrayList<>();
			}
			subscriptionFeatures = subscriptionFeatureRepository.findSubscriptionFeaturesBySubscriptionCode(subscriptionCode);
			if(subscriptionFeatures !=null ) {
				subscriptionFeatures.forEach(subscriptionFeature -> 
				userPermissions.add(new UserPermission(UUID.randomUUID(), subscriptionFeature, true)));
			}
		user.setUserPermissions(userPermissions);
		user = userRepository.save(user);
		return "added permissions successfully";
		}
		else {
			throw new UserNotFoundException(Properties.userNotFound);
		}
	}
}
