package com.albumservice.controller;

import com.albumservice.component.ObjectConversion;
import com.albumservice.dto.AlbumDto;
import com.albumservice.service.AlbumService;
import com.albumservice.vo.RequestAlbum;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
@Slf4j
public class AlbumController {

    private final AlbumService albumService;
    private final ObjectConversion objectConversion;

    @PostMapping("/creat")
    public ResponseEntity<?> creatAlbum(@Valid @RequestBody RequestAlbum requestAlbum) {
        try {
            AlbumDto albumDto = objectConversion.albumRequestToDto(requestAlbum);
            albumService.creatAlbum(albumDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("Successful album create");
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while creating album");
            // 수정
        }
    }

    @PostMapping("/upload/{title}")
    public ResponseEntity<?> uploadController(@PathVariable("title") String title,
                                              @RequestParam("image") MultipartFile image,
                                              @RequestParam("comment") String comment) {
        try {
            albumService.uploadService(title, image, comment);
            return ResponseEntity.status(HttpStatus.OK).body("Successful upload");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while creating album");
            // 수정
        }
    }
}
