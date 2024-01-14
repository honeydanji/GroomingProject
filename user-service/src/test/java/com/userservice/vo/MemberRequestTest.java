package com.userservice.vo;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberRequestTest {

    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeAll
    public static void init() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterAll
    public static void close() {
        factory.close();
    }

    @Test
    void 이름_유효성_테스트() {
        MemberRequest memberRequest = new MemberRequest();
        memberRequest.setNickname("단지귀여워");
        memberRequest.setEmail("tjdwls3680@naver.com");
        memberRequest.setPassword("ekswl5011*");

        Set<ConstraintViolation<MemberRequest>> violations = validator.validate(memberRequest);

        violations
                .forEach(error -> {
                    assertThat(error.getMessage()).isEqualTo("Name can not be null.");
                });
    }

    @Test
    void 닉네임_null_테스트() {
        MemberRequest memberRequest = new MemberRequest();
        memberRequest.setName("하성진");
        memberRequest.setEmail("tjdwls3680@naver.com");
        memberRequest.setPassword("ekswl5011*");

        Set<ConstraintViolation<MemberRequest>> violations = validator.validate(memberRequest);

        violations
                .forEach(error -> {
                    assertThat(error.getMessage()).isEqualTo("Nickname can not be null.");
                });
    }

    @Test
    void 닉네임_size_테스트() {
        MemberRequest memberRequest = new MemberRequest();
        memberRequest.setName("하성진");
        memberRequest.setNickname("a");
        memberRequest.setEmail("tjdwls3680@naver.com");
        memberRequest.setPassword("ekswl5011*");

        Set<ConstraintViolation<MemberRequest>> violations = validator.validate(memberRequest);

        violations
                .forEach(error -> {
                    assertThat(error.getMessage()).isEqualTo("Nicknames are between 2 and 20 characters");
                });
    }

    @Test
    void 이메일_null_테스트() {
        MemberRequest memberRequest = new MemberRequest();
        memberRequest.setName("하성진");
        memberRequest.setNickname("단지는귀여워");
        memberRequest.setPassword("ekswl5011*");

        Set<ConstraintViolation<MemberRequest>> violations = validator.validate(memberRequest);

        violations
                .forEach(error -> {
                    assertThat(error.getMessage()).isEqualTo("Email can not be null.");
                });
    }

    @Test
    void 이메일_형식_테스트() {
        MemberRequest memberRequest = new MemberRequest();
        memberRequest.setName("하성진");
        memberRequest.setNickname("단지는귀여워");
        memberRequest.setEmail("tjdwls3680naver.com");
        memberRequest.setPassword("ekswl5011*");

        Set<ConstraintViolation<MemberRequest>> violations = validator.validate(memberRequest);

        violations
                .forEach(error -> {
                    assertThat(error.getMessage()).isEqualTo("Please keep the email format");
                });
    }

    @Test
    void 비밀번호_null_테스트() {
        MemberRequest memberRequest = new MemberRequest();
        memberRequest.setName("하성진");
        memberRequest.setNickname("단지는귀여워");
        memberRequest.setEmail("tjdwls3680@naver.com");

        Set<ConstraintViolation<MemberRequest>> violations = validator.validate(memberRequest);

        violations
                .forEach(error -> {
                    assertThat(error.getMessage()).isEqualTo("Password can not be null.");
                });
    }
}
