package com.albumservice.vo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RequestAlbum {
    @NotNull(message = "Title can not be null")
    @Size(min = 1, max = 20, message = "제목은 1자 이상 20자 이하로 적어주세요.")
    private String title;
}
