package com.example.demo.controller;

import com.example.demo.entities.Chat;
import com.example.demo.entities.Student;
import com.example.demo.requests.ChatRequest;
import com.example.demo.responses.ChatResponse;
import com.example.demo.services.ChatService;
import com.example.demo.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * The type Chat controller.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/chat")
public class ChatController {

    /**
     * The Chat service.
     */
    @Autowired
    ChatService chatService;

    /**
     * The Student service.
     */
    @Autowired
    StudentService studentService;


    /**
     * Gets all chat by course id.
     *
     * @param courseId the course id
     * @return the all chat by course id
     */
    @GetMapping("/get/{courseId}")
    public List<ChatResponse> getAllChatByCourseId(@PathVariable("courseId")Long courseId){
        List<ChatResponse> allChatResponses = getAllChats(courseId);
        return allChatResponses;
    }


    /**
     * Post chat list.
     *
     * @param chatRequest the chat request
     * @return the list
     */
    @PostMapping("/post")
    public List<ChatResponse> postChat(@RequestBody ChatRequest chatRequest){
        chatService.saveChat(chatRequest);
        List<ChatResponse> allChatResponses = getAllChats(chatRequest.getCourseId());
        return allChatResponses;
    }

    /**
     * Get all chats list.
     *
     * @param courseId the course id
     * @return the list
     */
    public List<ChatResponse> getAllChats(Long courseId){
        List<Chat> allChat = chatService.getAllChat(courseId);
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
