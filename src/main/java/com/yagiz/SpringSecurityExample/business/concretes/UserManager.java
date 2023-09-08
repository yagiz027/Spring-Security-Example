package com.yagiz.SpringSecurityExample.business.concretes;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yagiz.SpringSecurityExample.Entity.User;
import com.yagiz.SpringSecurityExample.Repository.UserRepository;
import com.yagiz.SpringSecurityExample.business.UserBussinessRules.UserBusinessRules;
import com.yagiz.SpringSecurityExample.business.abstracts.UserService;
import com.yagiz.SpringSecurityExample.business.dto.requests.UpdateUserRequest;
import com.yagiz.SpringSecurityExample.business.dto.responses.GetUserListResponse;
import com.yagiz.SpringSecurityExample.business.dto.responses.UpdateUserResponse;
import com.yagiz.SpringSecurityExample.commons.constans.mapper.mapperAbstracts.ModelMapperService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserManager implements UserService{
    private UserRepository userRepository;
    private ModelMapperService mapperService;
    private UserBusinessRules businessRules;

    @Override
    public UpdateUserResponse updateUser(int id,UpdateUserRequest request) {
        businessRules.checkIfUserExists(id);
        User user=mapperService.forRequest().map(request, User.class);
        user.setId(id);   
        userRepository.save(user);
        UpdateUserResponse response = mapperService.forResponse().map(user, UpdateUserResponse.class);

        return response;
    }

    @Override
    public void deleteUserById(int id) {
        businessRules.checkIfUserExists(id);
        userRepository.deleteById(id);
    }

    @Override
    public List<GetUserListResponse> getUserList() {
        List<User> users= userRepository.findAll();
        List<GetUserListResponse> responses = users.stream().map(user->
            mapperService.forResponse().map(user, GetUserListResponse.class)
        ).toList();

        return responses;
    }
}
