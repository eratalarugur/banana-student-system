package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.demo.entities.Doc;
import com.example.demo.responses.DocumentResponse;
import com.example.demo.services.DocStorageService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

@ContextConfiguration(classes = {DocController.class})
@ExtendWith(SpringExtension.class)
public class DocControllerTest {
    @Autowired
    private DocController docController;

    @MockBean
    private DocStorageService docStorageService;

    @Test
    public void testDownloadFiles() throws Exception {
        when(this.docStorageService.getResponseFiles()).thenReturn(new ArrayList<DocumentResponse>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/file/downloadFiles");
        MockMvcBuilders.standaloneSetup(this.docController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testGet() throws Exception {
        when(this.docStorageService.getFiles()).thenReturn(new ArrayList<Doc>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/file/");
        MockMvcBuilders.standaloneSetup(this.docController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("doc"));
    }

    @Test
    public void testGet2() throws Exception {
        when(this.docStorageService.getFiles()).thenReturn(new ArrayList<Doc>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/file/");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.docController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("doc"));
    }

    @Test
    public void testUploadMultipleFiles() {
        assertEquals("redirect:/", (new DocController()).uploadMultipleFiles(new MultipartFile[]{}));
    }

    @Test
    public void testDownloadFiles2() throws Exception {
        when(this.docStorageService.getResponseFiles()).thenReturn(new ArrayList<DocumentResponse>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/file/downloadFiles");
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.docController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

