package com.reto.plazoleta.infrastructure.out.feign;

import com.reto.plazoleta.application.dto.request.SMSSendRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "sms", url = "localhost:8083")
public interface MessageFeignClient {
    @PostMapping(value = "/sms/process", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void process(@RequestBody SMSSendRequest sendRequest);
}
