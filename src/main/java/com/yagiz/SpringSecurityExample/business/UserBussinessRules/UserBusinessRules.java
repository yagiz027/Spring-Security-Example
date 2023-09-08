package com.yagiz.SpringSecurityExample.business.UserBussinessRules;

import org.springframework.stereotype.Service;

import com.yagiz.SpringSecurityExample.Repository.UserRepository;
import com.yagiz.SpringSecurityExample.commons.RestException.utils.constants.Messages;
import com.yagiz.SpringSecurityExample.commons.RestException.utils.exceptions.BusinessException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserBusinessRules {
    private UserRepository repository;

    public void checkIfUserExists(int id){
        if(!repository.existsById(id)){
            throw new BusinessException(Messages.User.UserNotFound);
        }
    }

    public void checkIfUserNameAlreadyExists(String userName){
        if(repository.existsByUsername(userName)){
            throw new BusinessException(Messages.User.UserNameAlreadyExists);
        }
    }

    public void checkIfUserEmailAlreadyExists(String userEmail){
        if(repository.existsByUserEmail(userEmail)){
            throw new BusinessException(Messages.User.UserEmailAlreadyExists);
        }
    }
}
