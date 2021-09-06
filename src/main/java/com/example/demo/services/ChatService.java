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

/**
 * The type Chat service.
 */
@Service
public class ChatService {

    /**
     * The Chat repository.
     */
    @Autowired
    ChatRepository chatRepository;

    /**
     * The Course repository.
     */
    @Autowired
    CourseRepository courseRepository;

    /**
     * Gets all chat.
     *
     * @param courseId the course id
     * @return the all chat
     */
    public List<Chat> getAllChat(Long courseId) {
        List<Chat> chats = chatRepository.findAllById(courseId);
        return chats;
    }

    /**
     * Save chat.
     *
     * @param chatRequest the chat request
     */
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
