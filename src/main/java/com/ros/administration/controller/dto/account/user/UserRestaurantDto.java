package com.ros.administration.controller.dto.account.user;

import java.util.List;
import java.util.UUID;

import com.ros.administration.controller.dto.RestaurantDto;
import com.ros.administration.model.Restaurant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRestaurantDto {

	private UUID id;
	private String username;
	private UUID AzureUUID;
	private String azureUPN;
	private List<RestaurantDto> restaurants;
}
