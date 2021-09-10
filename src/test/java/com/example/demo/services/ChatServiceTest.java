package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.entities.Chat;
import com.example.demo.entities.Course;
import com.example.demo.entities.Student;
import com.example.demo.repositories.ChatRepository;
import com.example.demo.repositories.CourseRepository;
import com.example.demo.requests.ChatRequest;
import com.example.demo.responses.ChatResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ChatService.class})
@ExtendWith(SpringExtension.class)
public class ChatServiceTest {
    @MockBean
    private ChatRepository chatRepository;

    @Autowired
    private ChatService chatService;

    @MockBean
    private CourseRepository courseRepository;

    @MockBean
    private StudentService studentService;

    @Test
    public void testGetAllChat() {
        ArrayList<Chat> chatList = new ArrayList<Chat>();
        when(this.chatRepository.findAllById((Long) any())).thenReturn(chatList);
        List<Chat> actualAllChat = this.chatService.getAllChat(123L);
        assertSame(chatList, actualAllChat);
        assertTrue(actualAllChat.isEmpty());
        verify(this.chatRepository).findAllById((Long) any());
    }

    @Test
    public void testSaveChat() {
        Chat chat = new Chat();
        chat.setMessage("Not all who wander are lost");
        chat.setCourseId(123L);
        chat.setStudentId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        chat.setPostDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));
        when(this.chatRepository.save((Chat) any())).thenReturn(chat);
        this.chatService.saveChat(new ChatRequest());
        verify(this.chatRepository).save((Chat) any());
    }

    @Test
    public void testGetAllChats() {
        when(this.chatRepository.findAllById((Long) any())).thenReturn(new ArrayList<Chat>());
        assertTrue(this.chatService.getAllChats(123L).isEmpty());
        verify(this.chatRepository).findAllById((Long) any());
    }

    @Test
    public void testGetAllChats2() {
        Student student = new Student();
        student.setEmail("jane.doe@example.org");
        student.setPassword("iloveyou");
        student.setBirthday("Birthday");
        student.setPicture("Picture");
        student.setRegisteredCourses(new ArrayList<Course>());
        student.setCity("Oxford");
        student.setName("Name");
        student.setSurname("Doe");
        Optional<Student> ofResult = Optional.<Student>of(student);
        when(this.studentService.getStudentById((Long) any())).thenReturn(ofResult);

        Chat chat = new Chat();
        chat.setMessage("Not all who wander are lost");
        chat.setCourseId(123L);
        chat.setStudentId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        chat.setPostDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));

        ArrayList<Chat> chatList = new ArrayList<Chat>();
        chatList.add(chat);
        when(this.chatRepository.findAllById((Long) any())).thenReturn(chatList);
        List<ChatResponse> actualAllChats = this.chatService.getAllChats(123L);
        assertEquals(1, actualAllChats.size());
        ChatResponse getResult = actualAllChats.get(0);
        assertEquals("Name", getResult.getSenderName());
        assertEquals("Not all who wander are lost", getResult.getMessage());
        verify(this.studentService).getStudentById((Long) any());
        verify(this.chatRepository).findAllById((Long) any());
    }

    @Test
    public void testGetAllChats3() {
        Student student = new Student();
        student.setEmail("jane.doe@example.org");
        student.setPassword("iloveyou");
        student.setBirthday("dd-MM-yyyy HH:mm");
        student.setPicture("dd-MM-yyyy HH:mm");
        student.setRegisteredCourses(new ArrayList<Course>());
        student.setCity("Oxford");
        student.setName("dd-MM-yyyy HH:mm");
        student.setSurname("Doe");
        Optional<Student> ofResult = Optional.<Student>of(student);
        when(this.studentService.getStudentById((Long) any())).thenReturn(ofResult);

        Chat chat = new Chat();
        chat.setMessage("Not all who wander are lost");
        chat.setCourseId(123L);
        chat.setStudentId(123L);
        LocalDateTime atStartOfDayResult = LocalDate.of(1970, 1, 1).atStartOfDay();
        chat.setPostDate(Date.from(atStartOfDayResult.atZone(ZoneId.of("UTC")).toInstant()));

        Chat chat1 = new Chat();
        chat1.setMessage("Not all who wander are lost");
        chat1.setCourseId(123L);
        chat1.setStudentId(123L);
        LocalDateTime atStartOfDayResult1 = LocalDate.of(1970, 1, 1).atStartOfDay();
        chat1.setPostDate(Date.from(atStartOfDayResult1.atZone(ZoneId.of("UTC")).toInstant()));

        ArrayList<Chat> chatList = new ArrayList<Chat>();
        chatList.add(chat1);
        chatList.add(chat);
        when(this.chatRepository.findAllById((Long) any())).thenReturn(chatList);
        List<ChatResponse> actualAllChats = this.chatService.getAllChats(123L);
        assertEquals(2, actualAllChats.size());
        ChatResponse getResult = actualAllChats.get(0);
        assertEquals("dd-MM-yyyy HH:mm", getResult.getSenderName());
        ChatResponse getResult1 = actualAllChats.get(1);
        assertEquals("dd-MM-yyyy HH:mm", getResult1.getSenderName());
        assertEquals("Not all who wander are lost", getResult1.getMessage());
        assertEquals("Not all who wander are lost", getResult.getMessage());
        verify(this.studentService, atLeast(1)).getStudentById((Long) any());
        verify(this.chatRepository).findAllById((Long) any());
    }
}

