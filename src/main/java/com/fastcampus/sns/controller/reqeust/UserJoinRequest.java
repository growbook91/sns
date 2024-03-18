package com.fastcampus.sns.controller.reqeust;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class UserJoinRequest {
    private String userName;
    private String password;
}
