package com.yagiz.SpringSecurityExample.commons.constans.Security.JwtUtil;

import java.io.IOException;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.micrometer.common.lang.NonNull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor  //Class içerisinde final olarak tanımlanan propertyleri consturctor'a ekler.
public class JwtTokenFilter extends OncePerRequestFilter {
    private final JwtManager jwtManager;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest request,
        @NonNull  HttpServletResponse response,
        @NonNull  FilterChain filterChain)
            throws ServletException, IOException {

        //Get authorization header and validate is not empty or equal "Bearer"
        final String header=request.getHeader(HttpHeaders.AUTHORIZATION);
        final String jwt;
        final String username;
        if(header==null || header.isEmpty() || !header.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }

        //Get JWT token and validate
        jwt=header.substring(7);
        username= jwtManager.extractUsername(jwt);
        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if(!jwtManager.validateToken(jwt,userDetails)){
                UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
    
}
