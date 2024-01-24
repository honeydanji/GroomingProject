package com.albumservice.service;

import com.albumservice.component.ObjectConversion;
import com.albumservice.dto.AlbumDto;
import com.albumservice.entity.Album;
import com.albumservice.exception.UniqueTitleException;
import com.albumservice.repository.AlbumRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;

public class AlbumServiceTest {
    @InjectMocks
    private AlbumService albumService;
    @Mock
    private AlbumRepository albumRepository;
    @Mock
    private ObjectConversion objectConversion;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateAlbumSuccess() {
        AlbumDto albumDto = new AlbumDto();
        albumDto.setTitle("Test title");

        Album album = new Album();

        Mockito.when(objectConversion.albumDtoToEntity(any(AlbumDto.class))).thenReturn(album);
        Mockito.when(albumRepository.save(any(Album.class))).thenReturn(album);

        albumService.creatAlbum(albumDto);
        Mockito.verify(albumRepository, Mockito.times(1)).save(any(Album.class));
    }

    @Test
    void testCreateAlbumFailure() {
        AlbumDto albumDto = new AlbumDto();
        albumDto.setTitle("Test Title");

        Album album = new Album();

        Mockito.when(objectConversion.albumDtoToEntity(any(AlbumDto.class))).thenReturn(album);
        doThrow(DataIntegrityViolationException.class).when(albumRepository).save(album);

        assertThrows(UniqueTitleException.class, () -> {
            albumService.creatAlbum(albumDto);
        });
    }
}
