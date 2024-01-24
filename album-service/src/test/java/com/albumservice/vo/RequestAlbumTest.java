package com.albumservice.vo;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ConstraintViolation;

import org.junit.jupiter.api.*;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class RequestAlbumTest {

    private static Validator validator;

    @BeforeAll
    public static void setup() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void titleFieldShouldNotBeNull() {
        RequestAlbum requestAlbum = new RequestAlbum();

        Set<ConstraintViolation<RequestAlbum>> violations = validator.validate(requestAlbum);

        assertThat(violations.isEmpty()).isFalse();
        for (ConstraintViolation<RequestAlbum> violation : violations) {
            assertThat(violation.getMessage()).isEqualTo("Title can not be null");
        }
    }

    @Test
    void titleFieldShouldNotBeEmpty() {
        RequestAlbum requestAlbum = new RequestAlbum();
        requestAlbum.setTitle("");

        Set<ConstraintViolation<RequestAlbum>> violations = validator.validate(requestAlbum);

        assertThat(violations.isEmpty()).isFalse();
        for (ConstraintViolation<RequestAlbum> violation : violations) {
            assertThat(violation.getMessage()).isEqualTo("제목은 1자 이상 20자 이하로 적어주세요.");
        }
    }

    @Test
    void titleFieldShouldNotExceedMaxLength() {
        RequestAlbum requestAlbum = new RequestAlbum();
        requestAlbum.setTitle("this title is definitely more than the maximum length");

        Set<ConstraintViolation<RequestAlbum>> violations = validator.validate(requestAlbum);

        assertThat(violations.isEmpty()).isFalse();
        for (ConstraintViolation<RequestAlbum> violation : violations) {
            assertThat(violation.getMessage()).isEqualTo("제목은 1자 이상 20자 이하로 적어주세요.");
        }
    }
}