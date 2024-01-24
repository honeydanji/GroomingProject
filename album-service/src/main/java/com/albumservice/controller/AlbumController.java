package com.albumservice.controller;

import com.albumservice.component.ObjectConversion;
import com.albumservice.dto.AlbumDto;
import com.albumservice.service.AlbumService;
import com.albumservice.vo.RequestAlbum;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class AlbumController {

    private final AlbumService albumService;
    private final ObjectConversion objectConversion;

    @PostMapping("/creat")
    public ResponseEntity<?> creatAlbum(@Valid @RequestBody RequestAlbum requestAlbum) {
        try {
            AlbumDto albumDto = objectConversion.albumRequestToDto(requestAlbum);
            albumService.creatAlbum(albumDto);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while creating album");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Successful album create");
    }
}
