package com.ros.administration;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.ros.administration.controller.dto.subscription.SubscriptionDto;
import com.ros.administration.service.SubscriptionService;

@SpringBootTest
@Transactional
class AdministrationServiceApplicationTests {

	@Autowired
	SubscriptionService service;

	@Test
	void contextLoads() {
	}


}
