//package com.ros.administration.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//import java.util.UUID;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.ros.administration.controller.dto.subscription.SubscriptionDto;
//import com.ros.administration.service.impl.SubscriptionServiceImpl;
//
//@SpringBootTest
//@Transactional
//public class SubscriptionServiceTest {
//    
//    @Autowired
//    SubscriptionServiceImpl service;
//
//    @Test
//    void testSubscriptionServicegetDtoById()
//    {
//        UUID uuid = UUID.fromString("d24959fd-06ee-4a63-9a1d-51e0556a345f");
//        SubscriptionDto dto = service.getSubscriptionById(uuid);
//        assertTrue(dto.getId()==uuid);
//    }
//    
//    @Test
//    void testSubscriptionServiceGetIfSubscriptionCodeExists()
//    {
//       String existing_subscription_code="ERP_TEST";
//       String existing_code = service.getCodeIfSubscriptionExists(existing_subscription_code);
//       assertEquals(existing_subscription_code, existing_code);
//    }
//    
//    @Test
//    void testSubscriptionServiceGetIfSubscriptionCodeDoesnotExists()
//    {
//      String sub_code="ZZZ";
//      Object code = service.getCodeIfSubscriptionExists(sub_code);
//      assertEquals(code, null);
//    }
//}
