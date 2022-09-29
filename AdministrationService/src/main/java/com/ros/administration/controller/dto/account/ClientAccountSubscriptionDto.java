package com.ros.administration.controller.dto.account;

import java.util.List;
import java.util.UUID;

import com.ros.administration.model.enums.EStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClientAccountSubscriptionDto {
	private UUID id;
	
	private String clientName;
	
	private EStatus accountStatus;
	
	private List<AccountSubscriptionDto> accountSubscriptions;


}
