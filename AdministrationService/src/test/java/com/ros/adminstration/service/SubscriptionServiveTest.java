package com.ros.adminstration.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.ros.administration.AdministrationServiceApplication;
import com.ros.administration.service.impl.SubscriptionServiceImpl;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdministrationServiceApplication.class)
@WebAppConfiguration
class SubscriptionServiveTest {

	@Autowired
    SubscriptionServiceImpl service=Mockito.mock(SubscriptionServiceImpl.class);

    @Test
    void testSubscriptionServiceGetIfSubscriptionCodeDoesnotExists()
    {
      String sub_code="ZZZ";
      Object code = service.getCodeIfSubscriptionExists(sub_code);
      assertEquals(code, null);
    }
    
}
