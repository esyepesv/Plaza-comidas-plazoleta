package com.reto.plazoleta.infrastructure.out.feign;

import com.reto.plazoleta.application.dto.AuthenticationRequest;
import com.reto.plazoleta.application.dto.AuthenticationResponse;
import com.reto.plazoleta.application.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "users", url = "localhost:8081")
public interface UserFeignClient {
    @GetMapping(value = "/usuarios/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<UserDto> getUser(@PathVariable Long userId);

    @PostMapping(value = "/auth/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request);

}
