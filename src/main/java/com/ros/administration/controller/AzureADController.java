package com.ros.administration.controller;

import javax.json.JsonObject;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.ros.administration.log.RosLogDebug;
import com.ros.administration.util.Properties;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
//import net.minidev.json.JSONObject;

@RestController
@RequestMapping(value = "/azure")
@CrossOrigin("*")
@Slf4j
public class AzureADController {

	@Hidden
	@PostMapping("/generateToken")
	@ResponseBody
	@RosLogDebug
	@Operation(summary = "generate token")
	public ResponseEntity<?> generateTokenResponse() {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://login.microsoftonline.com/23f10882-c8e3-4d2f-afe7-976fe6873232/oauth2/v2.0/token";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("client_id", "9748f684-8b91-4d64-8c76-02f1b43607c3");
		map.add("client_secret", "~.8T0.0IB7SX47BX3m85d1jdl1PO~6~~_n");
		map.add("grant_type", "client_credentials");
		map.add("scope", "https://graph.microsoft.com/.default");
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

		return response;
	}

	@Hidden
	@PostMapping("/signUp")
	@ResponseBody
	@RosLogDebug
	@Operation(summary = "userSignUp")
	public ResponseEntity<?> userSignUp(@RequestBody JsonObject object, @RequestParam String token) {
		ResponseEntity<?> response;
		try {
			RestTemplate restTemplate = new RestTemplate();
			String url = "https://graph.microsoft.com/v1.0/users";

			String TOKEN = "Bearer " + token;

			System.out.println("Token is: " + TOKEN);

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", TOKEN);
//		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//		map.add("Authorization",
//				"eyJ0eXAiOiJKV1QiLCJub25jZSI6IlFCemRyRjJhTFZDdjhaX05wUmlRRTRnMk5LemljTmdfcnZCcnc1Qk5TS3ciLCJhbGciOiJSUzI1NiIsIng1dCI6Im5PbzNaRHJPRFhFSzFqS1doWHNsSFJfS1hFZyIsImtpZCI6Im5PbzNaRHJPRFhFSzFqS1doWHNsSFJfS1hFZyJ9");
//		map.add("Content-Type", "application/json");
//		map.add("client_secret", "~.8T0.0IB7SX47BX3m85d1jdl1PO~6~~_n");
//		map.add("grant_type", "client_credentials");
//		map.add("scope", "https://graph.microsoft.com/.default");

//		JSONObject issuer = new JSONObject();
//		JSONObject password = new JSONObject();
//		JSONArray identities = new JSONArray();
//		JSONObject signupData = new JSONObject();
//
//		issuer.put("signInType", "federated");
//		issuer.put("issuer", "issuer@gmail.com");
//		issuer.put("issuerAssignedId", "debaduttapradhan@gmail.com");
//
//		identities.add(issuer);
//		signupData.put("identities", identities);
//
//		password.put("password", "Password@123");
//		password.put("forceChangePasswordNextSignIn", true);
//		object.put("passwordProfile", password);
//		signupData.put("passwordPolicies", "DisablePasswordExpiration");
//		signupData.put("userPrincipalName", "debadutta@restaurantonesolutionapps.onmicrosoft.com");
//		signupData.put("displayName", "Debadutta");
//		signupData.put("mailNickName", "Deba");
			System.out.println("Object is: " + object.toString());
			HttpEntity<String> request = new HttpEntity<String>(object.toString(), headers);

			response = restTemplate.postForEntity(url, request, String.class);

//		obj.put("displayName", "String");
			System.out.println("Response is: " + response);

		} catch (HttpClientErrorException e) {
			log.error(e.getMessage());
			System.out.println(e.getClass());
			response = new ResponseEntity<String>(e.getResponseBodyAsString(), HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@Hidden
	@DeleteMapping("/deleteUser/{email}")
	@ResponseBody
	@RosLogDebug
	@Operation(summary = "delete user")
	public ResponseEntity<?> deleteUser(@PathVariable(value = "email") String userName, @RequestParam String token) {
		ResponseEntity<?> response = null;

		try {
			RestTemplate restTemplate = new RestTemplate();

			String url = "https://graph.microsoft.com/v1.0/users/" + userName;

			System.out.println("User Name is: " + url);

			String TOKEN = "Bearer " + token;

			HttpHeaders headers = new HttpHeaders();

//		headers.setContentType(MediaType.APPLICATION_JSON);

			headers.set("Authorization", TOKEN);

			System.out.println("Token is: " + TOKEN);

			HttpEntity<String> header = new HttpEntity<>(headers);

			System.out.println("Request for Delete: " + header.getBody());

			response = restTemplate.exchange(url, HttpMethod.DELETE, header, String.class);

//		ResponseEntity<String> response = restTemplate.postForEntity(url, header, String.class);
			return response;
		} catch (Exception e) {
			log.error(e.getMessage());
			response = new ResponseEntity<String>(Properties.userDeleted, HttpStatus.OK);
		}

		return response;

	}

	

}
