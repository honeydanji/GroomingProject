package com.userservice.service;

import com.userservice.components.ObjectConversion;
import com.userservice.dto.MemberDto;
import com.userservice.entity.MemberEntity;
import com.userservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final ObjectConversion conversion;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String registerService(MemberDto memberDto) throws Exception {
        MemberEntity memberEntity = conversion.memberDtoToEntity(memberDto);
        memberEntity.setPassword(bCryptPasswordEncoder.encode(memberDto.getPassword()));
        memberEntity.setRole("ROLE_MEMBER");

        try {
            memberRepository.save(memberEntity);
            return null;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

}
