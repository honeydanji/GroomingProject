package com.albumservice.controller;

import com.albumservice.component.ObjectConversion;
import com.albumservice.service.AlbumService;
import com.albumservice.vo.RequestAlbum;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest
public class AlbumControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlbumService albumService;

    @MockBean
    private ObjectConversion objectConversion;

    @Autowired
    private ObjectMapper objectMapper;

    private RequestAlbum requestAlbum;

    @BeforeEach
    public void setUp() {
        requestAlbum = new RequestAlbum();
        requestAlbum.setTitle("Test Album");
    }

    @Test
    public void testCreatAlbum() throws Exception {
        Mockito.doNothing().when(albumService).creatAlbum(Mockito.any());

        mockMvc.perform(post("/creat")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestAlbum)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Successful album create"));
    }
}