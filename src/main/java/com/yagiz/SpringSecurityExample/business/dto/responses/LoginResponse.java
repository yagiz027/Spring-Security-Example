package com.yagiz.SpringSecurityExample.business.dto.responses;

public class LoginResponse {
    private final String jwt;
    
    public LoginResponse(String jwt){
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
