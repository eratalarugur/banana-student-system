package com.example.demo.services;

import com.example.demo.entities.Chat;
import com.example.demo.repositories.ChatRepository;
import com.example.demo.repositories.CourseRepository;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.requests.ChatRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ChatService {

    @Autowired
    ChatRepository chatRepository;

    @Autowired
    CourseRepository courseRepository;

    public List<Chat> getAllChat(Long courseId) {
        List<Chat> chats = chatRepository.findAllById(courseId);
        return chats;
    }

    public void saveChat(ChatRequest chatRequest){

        Chat chat = new Chat();
        chat.setCourseId(chatRequest.getCourseId());
        chat.setMessage(chatRequest.getMessage());
        chat.setPostDate(new Date());
        chat.setStudentId(chatRequest.getStudentId());

        System.out.println("serviceLayer =======>>>>>>>>>>>> " + chatRequest.toString());
        chatRepository.save(chat);
    }
}
