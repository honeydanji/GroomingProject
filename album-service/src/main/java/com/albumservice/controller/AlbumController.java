package com.albumservice.controller;

import com.albumservice.vo.RequestAlbum;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class AlbumController {

    @PostMapping("/creat")
    public ResponseEntity<?> creatAlbum(@Valid @RequestBody RequestAlbum requestAlbum) {
        return null;
    }

}
