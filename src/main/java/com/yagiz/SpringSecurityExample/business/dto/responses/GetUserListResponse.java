package com.yagiz.SpringSecurityExample.business.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GetUserListResponse {
    private int id;
    private String username;
    private String password;    
}
