package com.ros.administration.model.subscription;

import java.math.BigInteger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class RazorpayOrderRequest {
    String customerName;
    String email;
    String phoneNumber;
    BigInteger amount;
}
