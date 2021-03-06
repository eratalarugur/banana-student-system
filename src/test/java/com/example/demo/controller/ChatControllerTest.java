package com.example.demo.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.demo.requests.ChatRequest;
import com.example.demo.responses.ChatResponse;
import com.example.demo.services.ChatService;
import com.example.demo.services.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {ChatController.class})
@ExtendWith(SpringExtension.class)
public class ChatControllerTest {
    @Autowired
    private ChatController chatController;

    @MockBean
    private ChatService chatService;

    @MockBean
    private StudentService studentService;

    @Test
    public void testGetAllChatByCourseId() throws Exception {
        when(this.chatService.getAllChats((Long) any())).thenReturn(new ArrayList<ChatResponse>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/chat/get/{courseId}", 123L);
        MockMvcBuilders.standaloneSetup(this.chatController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testGetAllChatByCourseId2() throws Exception {
        when(this.chatService.getAllChats((Long) any())).thenReturn(new ArrayList<ChatResponse>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/chat/get/{courseId}", 123L);
        getResult.contentType("Not all who wander are lost");
        MockMvcBuilders.standaloneSetup(this.chatController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    public void testPostChat() throws Exception {
        when(this.chatService.getAllChats((Long) any())).thenReturn(new ArrayList<ChatResponse>());
        doNothing().when(this.chatService).saveChat((ChatRequest) any());

        ChatRequest chatRequest = new ChatRequest();
        chatRequest.setMessage("Not all who wander are lost");
        chatRequest.setCourseId(123L);
        chatRequest.setStudentId(123L);
        String content = (new ObjectMapper()).writeValueAsString(chatRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/chat/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(this.chatController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}

