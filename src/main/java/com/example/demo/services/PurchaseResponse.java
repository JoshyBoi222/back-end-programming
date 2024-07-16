package com.example.demo.services;

import com.example.demo.entities.StatusType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PurchaseResponse {
    private final String orderTrackingNumber;
    private StatusType status;
    public PurchaseResponse(String orderTrackingNumber) {
        this.orderTrackingNumber = orderTrackingNumber;
    }
}
