package com.ros.administration.controller;

import java.math.BigInteger;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.ros.administration.model.subscription.RazorpayOrderRequest;
import com.ros.administration.model.subscription.RazorpayOrderResponse;

import net.minidev.json.JSONObject;

@RestController
@RequestMapping("/pg")
public class RazorpayController {
    private RazorpayClient client;
    private static final String SECRET_ID1 = "rzp_test_MlDHdL9HTyE2I5";
    private static final String SECRET_KEY1 = "eS6FYcK29GqSMKT0nQuKv1Fb";

    @RequestMapping(path = "/createOrder", method = RequestMethod.POST)
    public RazorpayOrderResponse createOrder(@RequestBody RazorpayOrderRequest orderRequest) {
        RazorpayOrderResponse response = new RazorpayOrderResponse();
        try {
            client = new RazorpayClient(SECRET_ID1, SECRET_KEY1);
            Order order = createRazorPayOrder(orderRequest.getAmount());
            System.out.println("---------------------------");
            System.out.println("amnt: "+orderRequest.getAmount());
            System.out.println("name: "+orderRequest.getCustomerName());
            String orderId = (String) order.get("id");
            System.out.println("Order ID: " + orderId);
            System.out.println("---------------------------");
            response.setRazorpayOrderId(orderId);
            response.setApplicationFee("" + orderRequest.getAmount());
            response.setSecretKey(SECRET_KEY1);
            response.setSecretId(SECRET_ID1);
            response.setPgName("razor1");

            return response;
        } catch (RazorpayException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return response;

    }
    
    
    private Order createRazorPayOrder(BigInteger amount) throws RazorpayException {

        org.json.JSONObject options = new org.json.JSONObject();
        System.out.println(amount);
        options.put("amount",  amount.multiply(new BigInteger("100")));
        options.put("currency", "INR");
        options.put("receipt", "subscription_payment");
        options.put("payment_capture", 1); // You can enable this if you want to do Auto Capture.
        return client.orders.create(options);
    }
}
