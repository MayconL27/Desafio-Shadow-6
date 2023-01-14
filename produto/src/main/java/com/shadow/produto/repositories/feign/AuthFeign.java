package com.shadow.produto.repositories.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "authFeign", url = "http://localhost:8081")
public interface AuthFeign {

    /*
    @GetMapping(value = "/auth/validatetoken")
    boolean validateToken(@RequestHeader(HttpHeaders.AUTHORIZATION)String token);

    @GetMapping(value = "/auth/typeuser")
    String getTypeUser(@RequestHeader(HttpHeaders.AUTHORIZATION)String token);

    */

}
