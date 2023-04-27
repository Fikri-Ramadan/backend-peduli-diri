package com.fikritech.JwtSecurity.auth;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {

    private String token;
    private String firstname;

}
