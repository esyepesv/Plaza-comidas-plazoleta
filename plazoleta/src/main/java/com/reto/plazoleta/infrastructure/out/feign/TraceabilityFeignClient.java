package com.reto.plazoleta.infrastructure.out.feign;

import com.reto.plazoleta.application.dto.request.TraceabilityRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "traceability", url = "localhost:8084")
public interface TraceabilityFeignClient {

    @PostMapping(value = "/traceability/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> save(@RequestBody TraceabilityRequest traceabilityRequest);
}
