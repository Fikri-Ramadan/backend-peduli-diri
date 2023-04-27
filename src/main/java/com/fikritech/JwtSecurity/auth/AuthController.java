package com.fikritech.JwtSecurity.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.fikritech.JwtSecurity.entity.User;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authentication(@RequestBody AuthRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.authenticate(request));
    }

    @GetMapping("/firstname")
    public ResponseEntity<String> getFirstname(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(user.getFirstname());
    }

    @GetMapping("/test")
    public ResponseEntity<String> getTest() {
        return ResponseEntity.status(200).body("Test success");
    }

}
