package com.yagiz.SpringSecurityExample.business.abstracts;

import java.util.List;

import com.yagiz.SpringSecurityExample.business.dto.requests.UpdateUserRequest;
import com.yagiz.SpringSecurityExample.business.dto.responses.GetUserListResponse;
import com.yagiz.SpringSecurityExample.business.dto.responses.UpdateUserResponse;

public interface UserService {
    UpdateUserResponse updateUser(int id,UpdateUserRequest request);
    List<GetUserListResponse> getUserList();

    void deleteUserById(int id);
}
