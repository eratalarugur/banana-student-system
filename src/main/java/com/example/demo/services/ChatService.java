package com.example.demo.services;

import com.example.demo.entities.Chat;
import com.example.demo.entities.Student;
import com.example.demo.repositories.ChatRepository;
import com.example.demo.repositories.CourseRepository;
import com.example.demo.repositories.StudentRepository;
import com.example.demo.requests.ChatRequest;
import com.example.demo.responses.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
     * The Student service.
     */
    @Autowired
    StudentService studentService;

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
        chatRepository.save(chat);
    }

    /**
     * Get all chats list.
     *
     * @param courseId the course id
     * @return the list
     */
    public List<ChatResponse> getAllChats(Long courseId){
        List<Chat> allChat = getAllChat(courseId);
        List<ChatResponse> allChatResponses = new ArrayList<>();
        for (Chat chat: allChat) {
            Optional<Student> student = studentService.getStudentById(chat.getStudentId());
            ChatResponse chatResponse = new ChatResponse();
            Date oldDate = chat.getPostDate();
            String newFormattedDate = new SimpleDateFormat("dd-MM-yyyy HH:mm").format(oldDate);
            chatResponse.setDate(newFormattedDate);
            chatResponse.setMessage(chat.getMessage());
            chatResponse.setSenderName(student.get().getName());
            allChatResponses.add(chatResponse);
        }
        return allChatResponses;
    }
}
