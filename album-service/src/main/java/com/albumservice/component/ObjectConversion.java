package com.albumservice.component;

import com.albumservice.dto.AlbumDto;
import com.albumservice.entity.Album;
import com.albumservice.vo.RequestAlbum;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class ObjectConversion {
    public AlbumDto albumRequestToDto(RequestAlbum requestAlbum) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper.map(requestAlbum, AlbumDto.class);
    }

    public Album albumDtoToEntity(AlbumDto albumDto) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper.map(albumDto, Album.class);
    }
}
