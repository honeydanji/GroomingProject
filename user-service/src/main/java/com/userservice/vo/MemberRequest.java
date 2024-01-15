package com.userservice.vo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class MemberRequest {
    @NotNull(message = "Name can not be null.")
    private String name;

    @NotNull(message = "Nickname can not be null.")
    @Size(min = 2, max = 20, message = "Nicknames are between 2 and 20 characters")
    private String nickname;

    @NotNull(message = "Email can not be null.")
    @Email(message = "Please keep the email format")
    private String email;

    @NotNull(message = "Password can not be null.")
    private String password;
}
