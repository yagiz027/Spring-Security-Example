package com.yagiz.SpringSecurityExample.business.abstracts;

import com.yagiz.SpringSecurityExample.business.dto.requests.AuthenticationRequest;
import com.yagiz.SpringSecurityExample.business.dto.requests.RegisterRequest;
import com.yagiz.SpringSecurityExample.business.dto.responses.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
