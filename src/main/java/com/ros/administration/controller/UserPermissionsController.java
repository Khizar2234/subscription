package com.ros.administration.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ros.administration.controller.dto.account.user.AllUserPermissionsDto;
import com.ros.administration.controller.dto.account.user.UserDto;
import com.ros.administration.controller.dto.account.user.UserPermissionDto;
import com.ros.administration.exceptions.PermissionUpdateException;
import com.ros.administration.exceptions.UserNotFoundException;
import com.ros.administration.service.UserPermissionsService;
import com.ros.administration.util.Properties;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/userpermission")
@CrossOrigin("*")
@Slf4j
public class UserPermissionsController {

	@Autowired
	private UserPermissionsService userPermissionsService;
	
	@Operation(summary = "get all user permissions")
	@GetMapping("/{userId}")
	public ResponseEntity<?> getUserPermissions(@PathVariable(value = "userId") UUID userId){
		ResponseEntity<?> response;
//		try {
			response = new ResponseEntity<AllUserPermissionsDto>(new AllUserPermissionsDto(), HttpStatus.OK);
//		} catch (UserNotFoundException e) {
//			e.printStackTrace();
//			response = new ResponseEntity<UserNotFoundException>(new UserNotFoundException(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
//		}		
		return response;
	}
	
	
	@Operation(summary = "save all user permissions")
	@PostMapping
	public ResponseEntity<?> saveUserPermissions(@RequestBody List<UserPermissionDto> userPermissionDtos,@RequestParam UUID userId){
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<AllUserPermissionsDto>(userPermissionsService.saveUserPermissions(userPermissionDtos,userId), HttpStatus.OK);
		} catch (PermissionUpdateException e) {
			e.printStackTrace();
			response = new ResponseEntity<PermissionUpdateException>(new PermissionUpdateException(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		return response;
	}
	
	
	@Operation(summary = "save user permissions by username")
	@PostMapping("/saveByUsername")
	public ResponseEntity<?> saveUserPermissions(@RequestBody List<UserPermissionDto> userPermissionDtos,@RequestParam String username){
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<AllUserPermissionsDto>(userPermissionsService.saveUserPermissions(userPermissionDtos,username), HttpStatus.OK);
		} catch (PermissionUpdateException e) {
			e.printStackTrace();
			response = new ResponseEntity<PermissionUpdateException>(new PermissionUpdateException(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}		
		return response;
	}

	
	@Operation(summary = "configure user permissions")
	@PutMapping("/configure")
	public ResponseEntity<?> configureUserPermissions(@RequestBody List<UserPermissionDto> userPermissionDtos,@RequestParam UUID userUUId){
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<AllUserPermissionsDto>(userPermissionsService.configureUserPermissions(userPermissionDtos,userUUId), HttpStatus.OK);
		} catch (PermissionUpdateException e) {
			e.printStackTrace();
			response = new ResponseEntity<PermissionUpdateException>(new PermissionUpdateException(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (UserNotFoundException e) {
			response = new ResponseEntity<String>(Properties.userNotFound, HttpStatus.INTERNAL_SERVER_ERROR);		
		}		
		return response;
	}
	
	
//	@Operation(summary = "set all user permissions")
//	@PutMapping("/setAllPermissions")
//	public ResponseEntity<?> setAllUserPermissions(@RequestParam String username, @RequestParam String subscriptionCode){
//		ResponseEntity<?> response;
//		try {
//			response = new ResponseEntity<String>(userPermissionsService.setAllUserPermissions(username,subscriptionCode), HttpStatus.OK);
//		}  catch (UserNotFoundException e) {
//			response = new ResponseEntity<String>(Properties.userNotFound, HttpStatus.INTERNAL_SERVER_ERROR);		
//		}		
//		return response;
//	}
	
}
