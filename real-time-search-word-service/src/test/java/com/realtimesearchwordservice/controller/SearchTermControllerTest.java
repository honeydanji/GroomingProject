package com.realtimesearchwordservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.realtimesearchwordservice.document.SearchTermDocument;
import com.realtimesearchwordservice.service.SearchTermService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(SearchTermController.class)
public class SearchTermControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper; //

    @MockBean
    private SearchTermService searchTermService;

    // 검색어 저장 컨트롤러에 대한 유효한 요청 테스트
    @Test
    public void saveDocumentController_ValidRequest_ReturnsOk() throws Exception {
        SearchTermDocument searchTermDocument = new SearchTermDocument();
        searchTermDocument.setSearchTerm("TestTerm");

        mockMvc.perform(MockMvcRequestBuilders.post("/document")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(searchTermDocument))) // searchTermDocument 객체를 Json 으로 변환
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    // 검색어 저장 컨트롤러에 대한 유효하지 않은 요청 테스트
    @Test
    public void saveDocumentController_InvalidRequest_ReturnsInternalServerError() throws Exception {

        SearchTermDocument searchTermDocument = new SearchTermDocument();

        mockMvc.perform(MockMvcRequestBuilders.post("/document")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(searchTermDocument)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
