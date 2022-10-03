package com.ros.administration.controller.dto.account;

import java.util.List;
import java.util.UUID;

import com.ros.administration.controller.dto.ProductDto;
import com.ros.administration.controller.dto.account.user.UserDto;
import com.ros.administration.model.enums.EStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientAccountDto {
	private UUID id;
	
	private String clientName;

	private String accountEmail;

	private EStatus accountStatus;

//	private List<AccountSubscriptionDto> accountSubscriptions;

//	private List<ProductDto> products;
}
