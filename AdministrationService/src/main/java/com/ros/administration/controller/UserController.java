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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ros.administration.controller.dto.account.ClientDetailedInfoDto;
import com.ros.administration.controller.dto.account.user.AllUserPermissionsDto;
import com.ros.administration.controller.dto.account.user.DetailedUserInfoDto;
import com.ros.administration.controller.dto.account.user.UserAddDto;
import com.ros.administration.controller.dto.account.user.UserDetailsDto;
import com.ros.administration.controller.dto.account.user.UserDto;
import com.ros.administration.controller.dto.account.user.UserInfoDto;
import com.ros.administration.controller.dto.account.user.UserRestaurantDto;
import com.ros.administration.controller.dto.account.user.UserStatusDto;
import com.ros.administration.exceptions.ClientAccountStatusNotFoundException;
import com.ros.administration.exceptions.ErrorHandler;
import com.ros.administration.exceptions.UserAlreadyExistsException;
import com.ros.administration.exceptions.UserNotFoundException;
import com.ros.administration.model.enums.EStatus;
import com.ros.administration.service.UserService;
import com.ros.administration.util.ExceptionHandler;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
@Slf4j
public class UserController {
	@Autowired
	private UserService userService;

	// Returns UserDto when successful
	@Operation(summary = "add user")
	@PostMapping
	public ResponseEntity<?> addUser(@RequestBody UserDto user) {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<UserDto>(userService.addUser(user), HttpStatus.OK);
		} catch (UserAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = new ResponseEntity<UserNotFoundException>(new UserNotFoundException(e.getMessage()),
					HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	//Returns UserDto when successful
		@Operation(summary = "add user")
		@PostMapping("/add")
		public ResponseEntity<?> addUser(@RequestBody UserAddDto user, @RequestParam String SubscriptionCode, @RequestParam UUID accountId){
			ResponseEntity<?> response;
			try {
				response = new ResponseEntity<UserDto>(userService.addUser(user,SubscriptionCode,accountId ), HttpStatus.OK);
			} catch (UserAlreadyExistsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				response = new ResponseEntity<UserNotFoundException>(new UserNotFoundException(e.getMessage()), HttpStatus.BAD_REQUEST);
			}
			return response;
	}
		
			//Returns UserDto when successful
				@Operation(summary = "add SuperAdmin/Account officer in ROS Team")
				@PostMapping("/addRosTeam")
				public ResponseEntity<?> addROSTeam(@RequestBody UserAddDto userDto){
					ResponseEntity<?> response;
					try {
						response = new ResponseEntity<UserDto>(userService.addROSTeam(userDto), HttpStatus.OK);
					} catch (UserAlreadyExistsException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						response = new ResponseEntity<UserNotFoundException>(new UserNotFoundException(e.getMessage()), HttpStatus.BAD_REQUEST);
					}
					return response;
			}

	// Returns UserInfoDto when successful
	@Operation(summary = "update user details")
	@PutMapping
	public ResponseEntity<?> editUser(@RequestBody UserDto user) {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<UserDto>(userService.editUser(user), HttpStatus.OK);

		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("user not found");
			response = new ResponseEntity<UserNotFoundException>(new UserNotFoundException(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@Operation(summary = "delete user")
	@DeleteMapping
	public ResponseEntity<?> deleteUser(@RequestParam String username) {

		ResponseEntity<?> response;

		try {

			response = new ResponseEntity<String>(userService.deleteUser(username), HttpStatus.OK);

		} catch (UserNotFoundException e) {
			response = new ResponseEntity<UserNotFoundException>(new UserNotFoundException(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	// Returns UserDto when successful
	@Operation(summary = "get user")
	@GetMapping
	public ResponseEntity<?> getUser(@RequestParam String username) {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<UserInfoDto>(userService.getUserInfo(username), HttpStatus.OK);

		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("user not found");
			response = new ResponseEntity<UserNotFoundException>(new UserNotFoundException(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	// Returns UserInfoDto when successful
	@Operation(summary = "get user info")
	@GetMapping("/userInfo")
	public ResponseEntity<?> getUserInfo(@RequestParam String username) {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<UserInfoDto>(userService.getUserInfo(username), HttpStatus.OK);

		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("user not found");
			response = new ResponseEntity<UserNotFoundException>(new UserNotFoundException(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return response;
	}

	// Returns UserInfoDto when successful
	@Operation(summary = "get Detailed user info")
	@GetMapping("/detailedUserInfo")
	public ResponseEntity<?> getDetailedUserInfo(@RequestParam String username) {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<DetailedUserInfoDto>(userService.getDetailedUserInfo(username),
					HttpStatus.OK);

		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("user not found");
			response = new ResponseEntity<UserNotFoundException>(new UserNotFoundException(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return response;
	}

	// Returns AllUserPermissionsDto when successful
	@Operation(summary = "get user permissions")
	@GetMapping("/permission")
	public ResponseEntity<?> getUserPermisssions(@RequestParam String username) {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<AllUserPermissionsDto>(userService.getUserPermissions(username),
					HttpStatus.OK);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = new ResponseEntity<UserNotFoundException>(new UserNotFoundException(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	// Returns AllUserPermissionsDto when successful
	@Operation(summary = "get user permissions using UUID")
	@GetMapping("/permissions")
	public ResponseEntity<?> getUserPermisssions(@RequestParam UUID userUUID) {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<AllUserPermissionsDto>(userService.getUserPermissions(userUUID),
					HttpStatus.OK);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = new ResponseEntity<UserNotFoundException>(new UserNotFoundException(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	// Returns UserStatusDto when successful
	@Operation(summary = "get user account status")
	@GetMapping("/userStatus")
	public ResponseEntity<?> getUserStatus(@RequestParam String username) {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<UserStatusDto>(userService.getUserStatus(username), HttpStatus.OK);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = new ResponseEntity<UserNotFoundException>(new UserNotFoundException(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	// Returns UserRestaurantDto when successful
	@Operation(summary = "get restaurants mapped to a user")
	@GetMapping("/userRestaurants")
	public ResponseEntity<?> getRestaurantsForUser(@RequestParam String username) {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<UserRestaurantDto>(userService.getRestaurantsForUser(username),
					HttpStatus.OK);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response = new ResponseEntity<UserNotFoundException>(new UserNotFoundException(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return response;
	}

	// Returns UserInfoDto when successful
	@Operation(summary = "get all user info")
	@GetMapping("/allUserInfo")
	public ResponseEntity<?> getAllUserInfo() {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<List<UserDto>>(userService.getAllUserInfo(), HttpStatus.OK);

		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("user not found");
			response = new ResponseEntity<UserNotFoundException>(new UserNotFoundException(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return response;
	}
	
	
	// Returns UserInfoDto when successful
		@Operation(summary = "get all user info")
		@GetMapping("/allUsers")
		public ResponseEntity<?> getAllUserInfo(int limit, int pageNo) {
			ResponseEntity<?> response;
			try {
				response = new ResponseEntity<List<UserDto>>(userService.getAllUserInfo(limit, pageNo), HttpStatus.OK);

			} catch (UserNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("user not found");
				response = new ResponseEntity<UserNotFoundException>(new UserNotFoundException(e.getMessage()),
						HttpStatus.INTERNAL_SERVER_ERROR);

			}

			return response;
		}

	// Returns UserInfoDto List when successful
	@Operation(summary = "get user info from restaurant selection")
	@GetMapping("/userInfoByRestaurantId")
	public ResponseEntity<?> getUserInfoByRestaurantId(@RequestParam UUID restaurantId) {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<List<UserDto>>(userService.getUserInfoByRestaurantId(restaurantId),
					HttpStatus.OK);

		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("user not found");
			response = new ResponseEntity<UserNotFoundException>(new UserNotFoundException(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return response;
	}
	
	@Operation(summary = "edit user status")
	@PutMapping("/editUserStatus")
	public ResponseEntity<?> editUserStatus(@RequestParam UUID userId, @RequestParam EStatus status) {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<UserDto>(
					userService.editUserStatus(userId, status), HttpStatus.OK);

		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			
			response = new ResponseEntity<UserNotFoundException>(
					new UserNotFoundException(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}
	
	@Operation(summary = "get client details for user ")
	@GetMapping("/userClient")
	public ResponseEntity<?> getUserDetails(@RequestParam String username) {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<UserDetailsDto>(userService.getUserDetails(username),
					HttpStatus.OK);

		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("user not found");
			response = new ResponseEntity<UserNotFoundException>(new UserNotFoundException(e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}

		return response;
	}
	
	@Operation(summary = "add ROS Team Pin")
	@PatchMapping("/addROSTeamPin")
	@ResponseBody
	public ResponseEntity<?> addROSTeamPin(@RequestParam String username, @RequestParam String pin) {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<Object>(userService.addROSTeamPin(username,pin), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>( new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return response;
	}
	
	@Operation(summary = "edit Tablet Pin for SuperAdmin, ClientAdmin and Account Officer")
	@PatchMapping("/editTabletPin")
	@ResponseBody
	public ResponseEntity<?> editTabletPin(@RequestParam String username,@RequestParam String oldPin,@RequestParam String newPin) {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<Object>(userService.editTabletPin(username, oldPin, newPin), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>( new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return response;
	}
	
	@Operation(summary = "Validate Entry Pin")
	@GetMapping("/validateEntryPin")
	@ResponseBody
	public ResponseEntity<?> validateEntryPin(@RequestParam String pin) {
		ResponseEntity<?> response;
		try {
			response = new ResponseEntity<Object>(userService.validateEntryPin(pin), HttpStatus.OK);
		} catch (Exception e) {
			response = new ResponseEntity<ErrorHandler>( new ErrorHandler(HttpStatus.BAD_REQUEST.value(), e.getMessage()), HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return response;
	}
	
}
