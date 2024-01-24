package com.albumservice.service;

import com.albumservice.component.ObjectConversion;
import com.albumservice.dto.AlbumDto;
import com.albumservice.entity.Album;
import com.albumservice.exception.UniqueTitleException;
import com.albumservice.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final ObjectConversion objectConversion;

    public void creatAlbum(AlbumDto albumDto) {
        try {
             Album albumEntity = objectConversion.albumDtoToEntity(albumDto);
             albumRepository.save(albumEntity);
        } catch (DataIntegrityViolationException e) {
            throw new UniqueTitleException("이미 존재하는 제목입니다.");
        }
    }
}
