package com.reto.plazoleta.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SMSSendRequest {
    private String destinationSMSNumber;
    private String smsMessage;
}
