package com.yagiz.SpringSecurityExample.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.yagiz.SpringSecurityExample.business.abstracts.AuthenticationService;
import com.yagiz.SpringSecurityExample.business.abstracts.UserService;
import com.yagiz.SpringSecurityExample.business.dto.requests.AuthenticationRequest;
import com.yagiz.SpringSecurityExample.business.dto.requests.RegisterRequest;
import com.yagiz.SpringSecurityExample.business.dto.requests.UpdateUserRequest;
import com.yagiz.SpringSecurityExample.business.dto.responses.AuthenticationResponse;
import com.yagiz.SpringSecurityExample.business.dto.responses.GetUserListResponse;
import com.yagiz.SpringSecurityExample.business.dto.responses.UpdateUserResponse;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {

    private AuthenticationService authenticationService;
    private UserService userService;
 
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthenticationResponse> 
    createAuthenticationToken(@RequestBody RegisterRequest request) throws Exception{
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<AuthenticationResponse> 
    authenticate(@RequestBody AuthenticationRequest request) throws Exception{
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<GetUserListResponse> getUsers(@RequestBody AuthenticationRequest request) throws Exception{
        return userService.getUserList();
    }

    @DeleteMapping("/deleteById/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable int id){
        userService.deleteUserById(id);
    }

    @PutMapping("updateUser/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdateUserResponse updateUser(@PathVariable int id,@RequestBody UpdateUserRequest request){
        return userService.updateUser(id, request);
    }
}
