package com.example.gm.controller;

import com.example.gm.GmApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes = {GmApplication.class})
@WebAppConfiguration
@SpringBootTest
class CSVControllerTest {

    MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;


    @BeforeEach
    void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    void uploadCSVFile() throws Exception {
        MockMultipartFile file =
                new MockMultipartFile("file", "file", MediaType.TEXT_PLAIN_VALUE, "mockFile".getBytes());


         mockMvc.perform(multipart("/api/csv/items").file(file))
                .andExpect(status().isCreated());
    }

    @Test
    void fetchAllItems() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/csv/items")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent());
    }

    @Test
    void fetchByCode_missing_item() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/api/csv/items/1")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteAllItems() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/api/csv/items")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isNoContent());
    }
}