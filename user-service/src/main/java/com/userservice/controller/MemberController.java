package com.userservice.controller;

import com.userservice.components.ObjectConversion;
import com.userservice.service.MemberService;
import com.userservice.vo.MemberRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-service")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final ObjectConversion conversion;

    @PostMapping("/register")
    public ResponseEntity<String> registerController(@Valid @RequestBody MemberRequest memberRequest) {
        try {
            memberService.registerService(conversion.memberRequestToDto(memberRequest));
            return ResponseEntity.status(HttpStatus.CREATED).body("회원가입을 축하드립니다.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Validation error");
        }
    }
}
