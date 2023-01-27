package com.shadow.produto.repositories.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "authFeign", url = "http://localhost:8081") // Porta microservice
public interface AuthFeign {

    @GetMapping(value = "/auth/validatetoken")
    boolean validateToken(@RequestHeader(HttpHeaders.AUTHORIZATION)String token);

    @GetMapping(value = "/auth/tipousuario")
    String getTypeUser(@RequestHeader(HttpHeaders.AUTHORIZATION)String token);


}
