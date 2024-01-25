package com.albumservice.service;

import com.albumservice.component.ObjectConversion;
import com.albumservice.dto.AlbumDto;
import com.albumservice.dto.PicturesDto;
import com.albumservice.dto.S3BucketDto;
import com.albumservice.entity.Album;
import com.albumservice.entity.Pictures;
import com.albumservice.exception.UniqueTitleException;
import com.albumservice.repository.AlbumRepository;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final ObjectConversion objectConversion;
    private final AmazonS3 s3Client;
    private final S3BucketDto s3BucketDto;

    public void creatAlbum(AlbumDto albumDto) {
        try {
             Album albumEntity = objectConversion.albumDtoToEntity(albumDto);
             albumRepository.save(albumEntity);
        } catch (DataIntegrityViolationException e) {
            throw new UniqueTitleException("이미 존재하는 제목입니다.");
        }
    }

    @Transactional
    public void uploadService(String title, MultipartFile image, String comment) throws Exception {
        try {
            // 1. member id에 맞는 album 불러오기 (생략)
//            String memberId = authorization.getUserId;
//            Album albumEntity = albumRepository.findByMemberId(memberId);
            Album albumEntity = albumRepository.findByTitle(title);

            if (albumEntity == null) {
                throw new Exception("Album not found");
            }

            // 2. image s3로 전송
            s3Upload(image);

            // 3. s3로 부터 받은 image url, comment pictures 저장
            String imageUrl = s3DownloadImageUrl();

            Pictures picture = new Pictures();  // 새 Pictures 객체를 생성합니다.
            picture.setAlbumId(albumEntity);  // 사진 객체가 앨범 객체를 참조하도록 설정
            picture.setPicture(imageUrl);  // 이미지 URL을 설정합니다.
            picture.setComment(comment);  // 코멘트를 설정합니다.

            // 앨범에 Pictures 객체를 추가합니다.
            albumEntity.getPictures().add(picture);

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private void s3Upload(MultipartFile image) throws IOException {
        s3BucketDto.setKey(image.getOriginalFilename());

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(image.getContentType());
        objectMetadata.setContentLength(image.getSize());

        s3Client.putObject(s3BucketDto.getBucketName(), s3BucketDto.getKey(), image.getInputStream(), objectMetadata);
    }

    private String s3DownloadImageUrl() {
        return s3Client.getUrl(s3BucketDto.getBucketName(), s3BucketDto.getKey()).toExternalForm();
    }
}
