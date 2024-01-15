package com.userservice.components;

import com.userservice.dto.MemberDto;
import com.userservice.entity.MemberEntity;
import com.userservice.vo.MemberRequest;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class ObjectConversion {

    public MemberDto memberRequestToDto(MemberRequest memberRequest) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper.map(memberRequest, MemberDto.class);
    }

    public MemberEntity memberDtoToEntity(MemberDto memberDto) {
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper.map(memberDto, MemberEntity.class);
    }
}
