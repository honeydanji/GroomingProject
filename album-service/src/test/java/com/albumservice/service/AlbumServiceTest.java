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

/*
블로그 작성 내용

        // 아래 코드를 보면 Mockito에서 제공하는 @Mock을 사용했기 때문에
        // 기존에 정의한 Bean들을 커스텀해서 사용할 수 있다.
        // 이게 가능한 이유는 SpringBootContext에 등록한 빈이랑 전혀 상관없기 때문이다.
        // Controller에서 @MockBean을 사용하면 기존에 등록한 Bean 그대로의 기능을 사용해야하는데
        // 여기는 커스텀이 가능한다는 말이다.



* @Mock와 @MockBean 모두 비슷한 목적으로 사용되지만 쓰임새와 동작 방식에 차이가 있습니다.
@Mock은 Mockito에서 제공하는 주석으로, Spring에 종속되지 않습니다. 이 주석을 사용하면 모의 객체(mock object)를 만들어
* 단위 테스트에서 사용할 수 있습니다. 이는 Spring 컨텍스트 내부의 다른 빈에 영향을 미치지 않기 때문에 자유롭게 커스텀화하여
*  사용할 수 있습니다.
반면에 @MockBean은 Spring Boot 테스트에서 사용하는 주석으로, Spring 컨텍스트 내부의 실제 빈을 대체(mock)하는 데 사용됩니다.
*  따라서 기존에 등록된 빈의 동작을 대체하거나 변경할 필요가 있을 때 사용됩니다.
따라서, @Mock을 사용하면 Spring 컨텍스트와 독립적으로 테스트를 수행할 수 있고, 원하는대로 모의 객체를 생성하고 구성할 수 있습니다.
*  한편, @MockBean을 사용하면 Spring 컨텍스트 내부의 빈을 대체하거나 수정하여 통합 테스트를 수행할 수 있습니다.
* */
