package com.ros.administration.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.ros.administration.controller.dto.account.user.AllUserPermissionsDto;
import com.ros.administration.controller.dto.account.user.UserDto;
import com.ros.administration.controller.dto.account.user.UserPermissionDto;
import com.ros.administration.exceptions.PermissionUpdateException;
import com.ros.administration.exceptions.UserNotFoundException;

@Service
public interface UserPermissionsService {

	AllUserPermissionsDto getUserPermissionsById(UUID userId)throws UserNotFoundException;
	
	Boolean saveUserPermissions(UserDto userPermissionDTO) throws PermissionUpdateException;

	AllUserPermissionsDto saveUserPermissions(List<UserPermissionDto> userPermissionDtos, UUID userId)
			throws PermissionUpdateException;

	AllUserPermissionsDto saveUserPermissions(List<UserPermissionDto> userPermissionDtos, String username)
			throws PermissionUpdateException;


	AllUserPermissionsDto configureUserPermissions(List<UserPermissionDto> userPermissionDtos, UUID userUUId)throws UserNotFoundException, PermissionUpdateException;

	String setAllUserPermissions(String username, String subscriptionCode)throws UserNotFoundException;
}
