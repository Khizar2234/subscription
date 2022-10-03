package com.ros.administration.controller.dto.account;

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
public class UserAccountDto {
	
	private UUID id;
	private EStatus accountStatus;
	
}
