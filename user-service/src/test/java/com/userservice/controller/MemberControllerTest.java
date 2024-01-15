package com.userservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.userservice.components.ObjectConversion;
import com.userservice.service.MemberService;
import com.userservice.vo.MemberRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest(MemberController.class)
public class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ObjectConversion conversion;
    @MockBean
    private MemberService memberService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("[정상]회원등록 테스트")
    @WithMockUser
    void 회원가입_http_test() throws Exception {
        MemberRequest memberRequest = new MemberRequest();
        memberRequest.setName("하성진");
        memberRequest.setNickname("단지는멋쟁이");
        memberRequest.setEmail("tjdwls3680@naver.com");
        memberRequest.setPassword("ekswl5011*");

        mockMvc.perform(MockMvcRequestBuilders.post("/user-service/register")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(memberRequest)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string("회원가입을 축하드립니다."));
    }
}
