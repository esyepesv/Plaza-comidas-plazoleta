package com.reto.plazoleta.infrastructure.out.feign;

import com.reto.plazoleta.application.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "users", url = "localhost:8081")
public interface UserFeignClient {
    @GetMapping(value = "/usuarios/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDto> getUser(@PathVariable Long userId);

}
