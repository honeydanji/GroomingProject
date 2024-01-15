package com.userservice.dto;

import lombok.Data;

@Data
public class MemberDto {
    private String name;
    private String nickname;
    private String email;
    private String password;
}
