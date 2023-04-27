package com.fikritech.JwtSecurity.demo;

import com.fikritech.JwtSecurity.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/demo-controller")
@RequiredArgsConstructor
public class DemoController {

    private final JwtService jwtService;

    @GetMapping
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello from secured endpoint !!!");
    }

}
