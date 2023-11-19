package com.example.selfcoding.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//일반 컨트롤러는 @Controller 어노테이션을 사용하였으나, REST API 구현할 때는 @RestController 어노테이션을 사용한다.
@RestController
public class FirstApiController {
    @GetMapping("/api/hello")
    public String hello() {
        return "hello, API world";
    }
}
