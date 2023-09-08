package com.yagiz.SpringSecurityExample.business.concretes;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.yagiz.SpringSecurityExample.Entity.User;
import com.yagiz.SpringSecurityExample.Repository.UserRepository;
import com.yagiz.SpringSecurityExample.business.UserBussinessRules.UserBusinessRules;
import com.yagiz.SpringSecurityExample.business.abstracts.AuthenticationService;
import com.yagiz.SpringSecurityExample.business.dto.requests.AuthenticationRequest;
import com.yagiz.SpringSecurityExample.business.dto.requests.RegisterRequest;
import com.yagiz.SpringSecurityExample.business.dto.responses.AuthenticationResponse;
import com.yagiz.SpringSecurityExample.commons.RestException.utils.constants.Messages;
import com.yagiz.SpringSecurityExample.commons.RestException.utils.exceptions.BusinessException;
import com.yagiz.SpringSecurityExample.commons.constans.Security.JwtUtil.JwtManager;
import com.yagiz.SpringSecurityExample.commons.constans.mapper.mapperAbstracts.ModelMapperService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationManager implements AuthenticationService {

    private final UserRepository repository;
    private final ModelMapperService mapperService;
    private final UserBusinessRules userBusinessRules;
    private final JwtManager jwtManager;
    private final org.springframework.security.authentication.AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        userBusinessRules.checkIfUserNameAlreadyExists(request.getUsername());
        userBusinessRules.checkIfUserEmailAlreadyExists(request.getEmail());
        User user=mapperService.forRequest().map(request, User.class);
        user.setId(0);
        repository.save(user);
        AuthenticationResponse authResponse=mapperService.forResponse().map(user, AuthenticationResponse.class);
        var jwtToken = jwtManager.generateToken(user);
        authResponse=AuthenticationResponse.builder().token(jwtToken).build();
        return authResponse;
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword())
        );

        User user = repository.findByUserEmail(request.getEmail())
        .orElseThrow(()-> new BusinessException(Messages.User.InvalidUserEmail));

        AuthenticationResponse authResponse=mapperService.forResponse().map(user, AuthenticationResponse.class);
        var jwtToken = jwtManager.generateToken(user);
        authResponse=AuthenticationResponse.builder().token(jwtToken).build();
        return authResponse;
    }
}
