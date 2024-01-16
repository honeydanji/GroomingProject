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

public class RequestLoginTest {
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
    void 로그인_email_null_테스트() {
        RequestLogin requestLogin = new RequestLogin();
        requestLogin.setPassword("ekswl5011*");

        Set<ConstraintViolation<RequestLogin>> violations = validator.validate(requestLogin);

        violations.forEach(error -> {
            assertThat(error.getMessage()).isEqualTo("Email cannot be null");
        });
    }

    @Test
    void 로그인_password_null_테스트() {
        RequestLogin requestLogin = new RequestLogin();
        requestLogin.setEmail("tjdwls3680@naver.com");

         Set<ConstraintViolation<RequestLogin>> violations = validator.validate(requestLogin);

         violations.forEach(error -> {
             assertThat(error.getMessage()).isEqualTo("Password cannot be null");
         });
    }
}
