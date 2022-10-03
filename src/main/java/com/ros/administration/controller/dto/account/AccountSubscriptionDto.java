package com.ros.administration.controller.dto.account;

import java.util.Date;
import java.util.UUID;

import com.ros.administration.controller.dto.subscription.SubscriptionDto;
import com.ros.administration.model.enums.EStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountSubscriptionDto {
	private UUID accountSubId;
	private SubscriptionDto subscription;
	private String activatedBy;
	private Date activatedDate;
	private Date expiryDate;
	private EStatus status;


}
