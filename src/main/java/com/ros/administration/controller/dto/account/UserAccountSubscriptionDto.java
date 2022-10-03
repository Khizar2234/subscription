package com.ros.administration.controller.dto.account;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import com.ros.administration.controller.dto.ProductDto;
import com.ros.administration.controller.dto.account.user.UserSubscriptionDto;
import com.ros.administration.controller.dto.subscription.SubscriptionDto;
import com.ros.administration.model.Product;
import com.ros.administration.model.account.Account;
import com.ros.administration.model.account.Role;
import com.ros.administration.model.account.User;
import com.ros.administration.model.account.UserPermission;
import com.ros.administration.model.enums.EStatus;
import com.ros.administration.model.subscription.Subscription;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountSubscriptionDto {
	private UUID id;
	private UserSubscriptionDto subscription;
	private EStatus status;
}
