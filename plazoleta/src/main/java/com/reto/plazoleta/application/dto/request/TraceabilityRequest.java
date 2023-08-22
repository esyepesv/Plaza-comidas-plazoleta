package com.reto.plazoleta.application.dto.request;

import com.reto.plazoleta.domain.model.State;
import lombok.Data;

import java.util.Date;

@Data
public class TraceabilityRequest {
    private Long idOrder;
    private Long idClient;
    private String emailClient;
    private Date date;
    private State previousState;
    private State newState;
    private Long idEmployee;
    private String emailEmployee;
}
