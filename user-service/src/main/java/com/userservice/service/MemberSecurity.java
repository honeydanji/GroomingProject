package com.userservice.service;

import com.userservice.dto.MemberDto;
import com.userservice.entity.MemberEntity;
import com.userservice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class MemberSecurity implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MemberEntity memberEntity = memberRepository.findByEmail(email);

        if (memberEntity == null) {
            throw new UsernameNotFoundException(email);
        }

        return new User(memberEntity.getEmail(), memberEntity.getPassword(),
                true, true, true, true,
                new ArrayList<>()
        );
    }

    public MemberDto getUserDetailsByEmail(String email) {
        MemberEntity memberEntity = memberRepository.findByEmail(email);

        if (memberEntity == null) {
            throw new UsernameNotFoundException(email);
        }

        MemberDto memberDto = new ModelMapper().map(memberEntity, MemberDto.class);
        return memberDto;
    }

}
