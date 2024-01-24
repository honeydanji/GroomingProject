package com.albumservice.dto;

import com.albumservice.entity.Pictures;
import lombok.Data;

import java.util.List;

@Data
public class AlbumDto {
    private String title;
    private List<Pictures> pictures;
}
